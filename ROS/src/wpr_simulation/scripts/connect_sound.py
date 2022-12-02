import numpy as np
import math
from scipy import signal
import random
import matplotlib.pyplot as ppp

class Tdoa(object):

    def __init__(self) -> None:
        self.mic_pose_1 = [500,500]
        self.mic_pose_2 = [500,-500]
        self.mic_pose_3 = [-500,-500]
        self.mic_pose_4 = [-500,500]

    def get_dist(self, p1, p2):
        t_x = (p1[0] - p2[0])**2
        t_y = (p1[1] - p2[1])**2
        return math.sqrt(t_x+t_y)

    def get_sound_coor(self, diff_12, diff_13, diff_14):
        """
        diff_12 = t2 - t1
        ...
        """
        if diff_14 == 0:
            if diff_12 == 0:
                diff_x_12 = self.mic_pose_1[0] - self.mic_pose_2[0]
                diff_y_12 = self.mic_pose_1[1] - self.mic_pose_2[1]
                diff_x_14 = self.mic_pose_1[0] - self.mic_pose_4[0]
                diff_y_14 = self.mic_pose_1[1] - self.mic_pose_4[1]
                n_1 = [diff_y_14, - diff_x_14]
                n_2 = [ - diff_y_12, diff_x_12]
                a = np.array([ 
                    n_1, n_2
                ]).transpose()
                k = np.array([ 
                    self.mic_pose_2[0]/2 - self.mic_pose_4[0]/2,
                    self.mic_pose_2[1]/2 - self.mic_pose_4[1]/2
                ])
                alphaBeta = np.matmul(np.linalg.inv(a),k)
                coor  = alphaBeta[0]*np.array(n_1)+np.array([self.mic_pose_1[0]/2+self.mic_pose_4[0]/2,self.mic_pose_1[1]/2+self.mic_pose_4[1]/2])
                return coor
            s = 343
            diff_x_12 = self.mic_pose_1[0] - self.mic_pose_2[0]
            diff_y_12 = self.mic_pose_1[1] - self.mic_pose_2[1]
            diff_x_13 = self.mic_pose_1[0] - self.mic_pose_3[0]
            diff_y_13 = self.mic_pose_1[1] - self.mic_pose_3[1]
            diff_x_14 = self.mic_pose_1[0] - self.mic_pose_4[0]
            diff_y_14 = self.mic_pose_1[1] - self.mic_pose_4[1]

            # magic time
            b = diff_y_14
            c = (-self.mic_pose_1[0]+self.mic_pose_4[0])/2
            d = - diff_x_14
            e = (-self.mic_pose_1[1]+self.mic_pose_4[1])/2
            f = diff_x_12
            g = diff_y_12
            h = s * diff_12
            a = (-math.sqrt((-8*b*c*f**2+8*b*c*h**2-8*b*e*f*g-4*b*f**3-4*b*f*g**2+4*b*f*h**2-8*c*d*f*g-8*d*e*g**2+8*d*e*h**2-4*d*f**2*g-4*d*g**3+4*d*g*h**2)**2-4*(-4*b**2*f**2+4*b**2*h**2-8*b*d*f*g-4*d**2*g**2+4*d**2*h**2)*(-4*c**2*f**2+4*c**2*h**2-8*c*e*f*g-4*c*f**3-4*c*f*g**2+4*c*f*h**2-4*e**2*g**2+4*e**2*h**2-4*e*f**2*g-4*e*g**3+4*e*g*h**2-f**4-2*f**2*g**2+2*f**2*h**2-g**4+2*g**2*h**2-h**4))+8*b*c*f**2-8*b*c*h**2+8*b*e*f*g+4*b*f**3+4*b*f*g**2-4*b*f*h**2+8*c*d*f*g+8*d*e*g**2-8*d*e*h**2+4*d*f**2*g+4*d*g**3-4*d*g*h**2)/(2*(-4*b**2*f**2+4*b**2*h**2-8*b*d*f*g-4*d**2*g**2+4*d**2*h**2))
            tem1 = a * np.array([ 
                b, d
            ]) + np.array([ 
                (self.mic_pose_1[0]+self.mic_pose_4[0])/2,
                (self.mic_pose_1[1]+self.mic_pose_4[1])/2
            ])
            
            if diff_12 < 0 and self.get_dist(tem1, self.mic_pose_1) > self.get_dist(tem1, self.mic_pose_2):
                return tem1
            # elif diff_12 > 0 and self.get_dist(tem1, self.mic_pose_1) > self.get_dist(tem1, self.mic_pose_2):
            #     return None
            # elif diff_12 < 0 and self.get_dist(tem1, self.mic_pose_1) < self.get_dist(tem1, self.mic_pose_2):
            #     print('11')
            #     return None
            else:
                aa = (math.sqrt((-8*b*c*f**2+8*b*c*h**2-8*b*e*f*g-4*b*f**3-4*b*f*g**2+4*b*f*h**2-8*c*d*f*g-8*d*e*g**2+8*d*e*h**2-4*d*f**2*g-4*d*g**3+4*d*g*h**2)**2-4*(-4*b**2*f**2+4*b**2*h**2-8*b*d*f*g-4*d**2*g**2+4*d**2*h**2)*(-4*c**2*f**2+4*c**2*h**2-8*c*e*f*g-4*c*f**3-4*c*f*g**2+4*c*f*h**2-4*e**2*g**2+4*e**2*h**2-4*e*f**2*g-4*e*g**3+4*e*g*h**2-f**4-2*f**2*g**2+2*f**2*h**2-g**4+2*g**2*h**2-h**4))+8*b*c*f**2-8*b*c*h**2+8*b*e*f*g+4*b*f**3+4*b*f*g**2-4*b*f*h**2+8*c*d*f*g+8*d*e*g**2-8*d*e*h**2+4*d*f**2*g+4*d*g**3-4*d*g*h**2)/(2*(-4*b**2*f**2+4*b**2*h**2-8*b*d*f*g-4*d**2*g**2+4*d**2*h**2))
                tem2 = aa * np.array([ 
                                b, d
                            ]) + np.array([ 
                                (self.mic_pose_1[0]+self.mic_pose_4[0])/2,
                                (self.mic_pose_1[1]+self.mic_pose_4[1])/2
                            ])
                return tem2
        
        if diff_12 == 0:
            rec = Tdoa()
            rec.set_mic_coor(self.mic_pose_2[0],self.mic_pose_2[1],self.mic_pose_3[0],self.mic_pose_3[1],self.mic_pose_4[0],self.mic_pose_4[1],self.mic_pose_1[0],self.mic_pose_1[1])
            return rec.get_sound_coor(diff_13-diff_12,diff_14-diff_12,-diff_12)


        s = 343
        diff_x_12 = self.mic_pose_1[0] - self.mic_pose_2[0]
        diff_y_12 = self.mic_pose_1[1] - self.mic_pose_2[1]
        diff_x_13 = self.mic_pose_1[0] - self.mic_pose_3[0]
        diff_y_13 = self.mic_pose_1[1] - self.mic_pose_3[1]
        diff_x_14 = self.mic_pose_1[0] - self.mic_pose_4[0]
        diff_y_14 = self.mic_pose_1[1] - self.mic_pose_4[1]

        # fys  = diff_12*((s*diff_14)**2) - diff_14*((s*diff_12)**2) 
        # fys += - diff_12*(diff_x_14**2) - diff_12*(diff_y_14**2) 
        # fys += + diff_14*(diff_x_12**2) + diff_14*(diff_y_12**2)

        # nmsl  = diff_12*((s*diff_13)**2) - diff_13*((s*diff_12)**2) 
        # nmsl += - diff_12*(diff_x_13**2) - diff_12*(diff_y_13**2) 
        # nmsl += + diff_13*(diff_x_12**2) + diff_13*(diff_y_12**2)

        # cnm = 2*(diff_12*diff_x_14-diff_14*diff_x_12)
        # smd = 2*(diff_12*diff_y_14-diff_14*diff_y_12)

        # wkwk = 2*(diff_12*diff_x_13-diff_13*diff_x_12)
        # wpgg = 2*(diff_12*diff_y_13-diff_13*diff_y_12)

        # ddly = np.array([ 
        #     [cnm, smd],
        #     [wkwk, wpgg]
        # ])
        # ff = np.array([ 
        #     fys, nmsl
        # ])
        # xy = np.matmul(np.linalg.inv(ddly),ff)
        # xy += np.array(self.mic_pose_1)
        # return xy

        a11 = 2*diff_x_14*diff_12 - 2*diff_x_12*diff_14
        a12 = 2*diff_y_14*diff_12 - 2*diff_y_12*diff_14
        a21 = 2*diff_x_13*diff_12 - 2*diff_x_12*diff_13
        a22 = 2*diff_y_13*diff_12 - 2*diff_y_12*diff_13

        c1 = diff_12*(s*diff_14)**2 - (diff_x_14**2 + diff_y_14**2)*diff_12 - diff_14*(s*diff_12)**2 + (diff_x_12**2 + diff_y_12**2)*diff_14
        c2 = diff_12*(s*diff_13)**2 - (diff_x_13**2 + diff_y_13**2)*diff_12 - diff_13*(s*diff_12)**2 + (diff_x_12**2 + diff_y_12**2)*diff_13

        a = np.array([ 
            [a11,a12],
            [a21,a22]
        ])

        c = np.array([ 
            c1,c2
        ])

        xy = np.array(self.mic_pose_1) + np.matmul(np.linalg.inv(a),c)
        return xy

    def set_mic_coor(self, x_1, y_1, x_2, y_2, x_3, y_3, x_4, y_4):
        self.mic_pose_1 = [x_1,y_1]
        self.mic_pose_2 = [x_2,y_2]
        self.mic_pose_3 = [x_3,y_3]
        self.mic_pose_4 = [x_4,y_4]

class CalTime(object):
    def __init__(self) -> None:
        self.mic_pose_1 = [500,500]
        self.mic_pose_2 = [500,-500]
        self.mic_pose_3 = [-500,-500]
        self.mic_pose_4 = [-500,500]


    def set_mic_coor(self, x_1, y_1, x_2, y_2, x_3, y_3, x_4, y_4):
        self.mic_pose_1 = [x_1,y_1]
        self.mic_pose_2 = [x_2,y_2]
        self.mic_pose_3 = [x_3,y_3]
        self.mic_pose_4 = [x_4,y_4]

    def get_dist(self, p1, p2):
        t_x = (p1[0] - p2[0])**2
        t_y = (p1[1] - p2[1])**2
        return math.sqrt(t_x+t_y)

    def get_time_diff(self, x, y):
        s = 343
        pp = [x,y]
        t_1 = self.get_dist(pp,self.mic_pose_1)/s
        t_2 = self.get_dist(pp,self.mic_pose_2)/s
        t_3 = self.get_dist(pp,self.mic_pose_3)/s
        t_4 = self.get_dist(pp,self.mic_pose_4)/s
        t_12 = t_2 - t_1
        t_13 = t_3 - t_1
        t_14 = t_4 - t_1
        return t_12, t_13, t_14

class Wave(object):
    def __init__(self) -> None:
        '''
        sample rate = no_samples / sec = 48kHz
        sine sound = 5s
        '''
        self.sample_rate = 48000

        self.no_samples = self.sample_rate * 10
        self.no_waves = 1
        self.t = np.linspace(0, self.no_waves, self.no_samples)

        self.mean = 0
        self.sigma = 0.01

    def set_no_wave(self, n):
        self.no_waves = n
        self.t = np.linspace(0, self.no_waves, self.no_samples)

    def set_noise_para(self, mean, sigma):
        self.mean = mean
        self.sigma = sigma

    def create_noise(self):
        return np.array([random.gauss(self.mean, self.sigma) for i in range(self.no_samples)])

    def delay_sig(self, delay):
        return np.sin(2.0*np.pi*(self.t-delay))

    def get_signal(self, delay):
        return self.delay_sig(0), self.delay_sig(delay)

    def add_noise(self, ww):
        return ww + self.create_noise()

class Correlation(object):
    def __init__(self) -> None:
        self.no_samples = 48000 * 10
        self.no_waves = 1

    def get_lag(self, sig1, sig2):
        # cor1 = signal.correlate(sig1, sig1, mode='full')
        cor2 = signal.correlate(sig1, sig2, mode='full')
        # max_cor1 = np.argmax(cor1)
        max_cor2 = np.argmax(cor2)
        # return self.no_waves*(max_cor1-max_cor2)/self.no_samples
        return self.no_waves*(self.no_samples-max_cor2)/self.no_samples

if __name__ == "__main__":
    cal = CalTime()
    wav = Wave()
    cor = Correlation()
    pos = Tdoa()
    def set_mic_coor_tgt(x1,y1,x2,y2,x3,y3,x4,y4):
        cal.set_mic_coor(x1,y1,x2,y2,x3,y3,x4,y4)
        pos.set_mic_coor(x1,y1,x2,y2,x3,y3,x4,y4)
    def set_no_wave(n):
        wav.set_no_wave(n)
        cor.no_waves = n

    # test here
    set_mic_coor_tgt(10,10,10,0,-10,0,-10,10)
    set_no_wave(15)
    wav.set_noise_para(0, 0.01)
    a, b, c = cal.get_time_diff(1, 3)

    # print(a, b, c)
    a1, a2 = wav.get_signal(a)
    b1, b2 = wav.get_signal(b)
    c1, c2 = wav.get_signal(c)
    a1 = wav.add_noise(a1)
    a2 = wav.add_noise(a2) 
    b1 = wav.add_noise(b1)
    b2 = wav.add_noise(b2) 
    c1 = wav.add_noise(c1)
    c2 = wav.add_noise(c2)
    aa = cor.get_lag(a1,a2)
    bb = cor.get_lag(b1,b2)
    cc = cor.get_lag(c1,c2)
    # if aa*a < 0:
    #     aa = 3 + aa
    # if bb*b < 0:
    #     bb = 3 + bb
    # if cc*c < 0:
    #     cc = 1 + cc
    # since sine has similar peak
    aa += round(a-aa)
    bb += round(b-bb)
    cc += round(c-cc)
    # print(aa, bb, cc)
    # aa = a
    # bb = b
    # cc = c
    print(pos.get_sound_coor(aa,bb,cc))
    ppp.figure()
    ppp.plot(a1,'r')
    ppp.savefig('h')