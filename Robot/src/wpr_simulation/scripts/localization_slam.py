#! /usr/bin/env python3

import rospy
import numpy as np
from sensor_msgs.msg import LaserScan
from nav_msgs.msg import Odometry
import math
import random
from threading import Lock
from geometry_msgs.msg import PoseStamped

class EKF_slam(object):
    def __init__(self) -> None:
        self.corrspondences = [
            # (-0.63, -0.52),
            # (-0.63, 0.80),
            # (1.18, 0.81),
            # (1.18, -0.52),
            # (1.95, -0.52),
            # (1.95, 0.81),
            # (3.75, 0.81),
            # (3.75, -0.52),
            # (2, -5.42),
            # (2, -4.13),
            # (3.8, -4.13),
            # (3.8, -5.42),
            # (-0.61, -5.42),
            # (-0.61, -4.13),
            # (1.19, -4.13),
            # (1.19, -5.42),
            (11.316400, -1.903230),
            (11.316400, -2.655370),
            (9.441120, -2.655370),
            (9.441120, -1.903230),
            (6.980620, -1.798760),
            (6.980620, -2.619720),
            (5.147820, -2.619720),
            (5.147820, -1.798760),
            (8.941295, 1.099575),
            (8.118780, 0.767312),
            (8.089400, 0.028383),
            (8.847700, -0.466531),
            (9.552580, -0.156678),
            (9.514340, 0.762196),
            (8.695950, -5.387860),
            (9.370130, -5.028810),
            (9.356880, -4.102360),
            (8.703530, -3.684100),
            (7.963630, -4.149850),
            (7.912100, -4.882450),
            (-4.449890, -0.633250),
            (-3.767120, -0.332181),
            (-3.811810, -3.811810),
            (-4.313070, 0.936035),
            (-5.141770, 0.596055),
            (-5.215030, -0.133957),
            (-9.525460, -0.197345),
            (-8.749260, -0.739746),
            (-8.077460, -0.372177),
            (-8.099210, 0.546480),
            (-8.646240, 0.887381),
            (-9.486510, 0.553418),
            (-9.1, 2.95),
            (-9.1, 3.32),
            (-8.18, 2.95),
            (-8.18, 3.33),
            (-5.81, 2.88),
            (-5.822770, 3.321670),
            (-4.406280, 3.319820),
            (-4.406070, 2.870070),
            (5.136010, 2.868870),
            (5.126480, 3.310130),
            (6.970890, 3.320040),
            (6.964270, 2.851880),
            (9.562250, 2.951770),
            (9.567320, 3.301910),
            (11.439300, 3.311430),
            (11.415900, 2.928480),
            (13.481600, 2.882320),
            (13.462900, 3.285040),
            (14.429400, 2.916540),
            (14.429400, 3.285040),
            (14.562800, -7.183610),
            (14.564700, -7.574870),
            (13.629600, -7.575850),
            (13.656700, -7.190550),
            (11.303400, -7.162300),
            (11.303400, -7.584410),
            (9.469130, -7.584410),
            (9.469130, -7.162300),
            (6.928310, -7.259450),
            (6.928310, -7.591560),
            (5.066840, -7.591560),
            (5.066840, -7.259450),
            (-4.422030, -7.230510),
            (-4.422030, -7.271170),
            (-5.815440, -7.271170),
            (-5.815440, -7.230510),
            (-8.310550, -7.224340),
            (-8.310550, -7.565460),
            (-9.218040, -7.565460),
            (-9.218040, -7.224340)
            ]
        self.no_correspendences = len(self.corrspondences)
        ww = 3*self.no_correspendences
        self.f_x = np.c_[np.identity(3),np.zeros([3,ww])]
        self.f_x_t = self.f_x.transpose()
        self.iden = np.identity(3+ww)
        self._lock = Lock()
        self.odom_init = False
        self.system_state = np.array((3+ww)*[0])
        self.cov_matrix = np.zeros([3+ww,3+ww])
        self.last_state = [0,0,0]
        rospy.init_node('elio_t_slam', anonymous = True)
        rospy.Subscriber('/odom', Odometry, self.predict, queue_size=1)
        #rospy.Subscriber('/scan', LaserScan, self.update, queue_size=1)
        self.pose_publisher = rospy.Publisher("/estimatedpose", PoseStamped, queue_size=1)

    def get_delta(self, orian):
        return math.atan2(2 * (orian.x * orian.y + orian.w * orian.z),
                        orian.w * orian.w + orian.x * orian.x - orian.y * orian.y - orian.z * orian.z)

    def get_contol_sig(self, odom : Odometry):
        if not self.odom_init:
            self.last_odom_pose_x = odom.pose.pose.position.x
            self.last_odom_pose_y = odom.pose.pose.position.y
            self.last_odom_pose_t = self.get_delta(odom.pose.pose.orientation)
            self.odom_init = True
        diff_x = odom.pose.pose.position.x - self.last_odom_pose_x 
        diff_y = odom.pose.pose.position.y - self.last_odom_pose_y
        diff_t = self.get_delta(odom.pose.pose.orientation) - self.last_odom_pose_t

        self.last_odom_pose_x = odom.pose.pose.position.x
        self.last_odom_pose_y = odom.pose.pose.position.y
        self.last_odom_pose_t = self.get_delta(odom.pose.pose.orientation)
        return [diff_x,diff_y,diff_t]

    def three_matmul(self, m1, m2, m3):
        return(np.matmul(np.matmul(m1,m2), m3))

    def predict(self, control_sig : Odometry):
        with self._lock:
            ei = self.get_contol_sig(control_sig)
            wuw = np.array(ei)
            piu = np.c_[np.zeros([3,2]),[-ei[0],ei[1],0]]
            self.system_state = self.system_state + np.matmul(self.f_x_t, wuw)
            g_t = self.iden + self.three_matmul(self.f_x_t, piu, self.f_x)
            rand_t = np.diag([random.gauss(0,0.01),random.gauss(0,0.01),random.gauss(0,0.01)])
            self.cov_matrix = self.three_matmul(g_t,self.cov_matrix,g_t.transpose())+self.three_matmul(self.f_x_t,rand_t,self.f_x)
            self.pose_publisher.publish(self.get_pose())
            print(self.system_state)

    def global_coor(self, r, i):
        a = i*0.017501922324299812 + self.system_state[2]
        return [self.system_state[0]+r*math.cos(a),self.system_state[1]+math.sin(a)]

    def eu_dist(self, p1, p2):
        x = (p1[0] - p2[0])**2
        y = (p1[1] - p2[1])**2
        return math.sqrt(x+y)

    def extract_landmark(self, sc : LaserScan):
        ljs = []
        nice = []
        rs = sc.ranges
        l = len(rs)
        for i in range(l):
            a = rs[i-2]
            b = rs[i-1]
            c = rs[i-0]
            if np.isinf(a):
                break
            if (b-a)*(c-b) < 0:
                ljs.append(i-1)
            elif a - b > 0.5:
                ljs.append(i-1)
            elif c - b > 0.5:
                ljs.append(i-1)
        for lj in ljs:
            coor = self.global_coor(rs[lj], lj)
            for ind, cor in enumerate(self.corrspondences):
                if self.eu_dist(coor, cor) < 0.03125:
                    n = [rs[lj], lj*0.017501922324299812, 0, coor[0], coor[1], ind]
                    nice.append(n)
                    break
        return nice
            
    def get_pose(self):
        p = PoseStamped()
        p.header.frame_id = "map"
        p.pose.position.x = self.system_state[0]
        p.pose.position.y = self.system_state[1]
        p.pose.position.z = 0
        p.pose.orientation.x = 0
        p.pose.orientation.y = 0
        p.pose.orientation.z = math.sin(self.system_state[2]/2)
        p.pose.orientation.w = math.cos(self.system_state[2]/2)
        return p

if __name__ == "__main__":
    w = EKF_slam()
    rospy.spin() 