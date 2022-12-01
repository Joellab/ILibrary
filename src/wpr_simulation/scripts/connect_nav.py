#!/usr/bin/env python3
# coding=utf-8

import rospy
import actionlib
from move_base_msgs.msg import MoveBaseAction, MoveBaseGoal
import base64
import requests
import json
import time
import threading
import sys
import numpy as np
from sensor_msgs.msg import Image
from cv_bridge import CvBridge, CvBridgeError
import copy

SERVER_URL = "http://192.168.1.120:8090"
TOTAL_THREAD = 0
CMD = "INIT"
STATUS = "INIT"

def cmdQuery():
    #TODO: Use WebSocket instead of query
    while True:
        try:
            url = SERVER_URL + '/api/comm/cmdQuery'
            r = requests.get(url, timeout=3)
            cmdLock.acquire()
            if CMD != r:
                r = CMD
                rospy.loginfo("[ILibrary] New command received: " + CMD)
            CMD = r
            cmdLock.release()
        except:
            rospy.loginfo("[ILibrary] Command query failed")
        time.sleep(3)


def waitForJob(currentJob):
    global STATUS
    while STATUS == "STANDBY":
        try:
            url = SERVER_URL + '/api/comm/nextJob'
            params = {"current": currentJob}
            r = requests.get(url, timeout=3)
            r = json.dumps(r)
            if r.data.status != "STANDBY":
                STATUS = r.data.status
                statusProcess(r)
        except:
            rospy.loginfo("[ILibrary] Job query failed")
    time.sleep(3)

def getNextJob(currentJob):
    global STATUS
    try:
        url = SERVER_URL + '/api/comm/nextJob'
        params = {"current": currentJob}
        r = requests.get(url, timeout=3)
        r = json.dumps(r)
        STATUS = r.data.status
        statusProcess(r)

    except:
        rospy.loginfo("[ILibrary] Job query failed")

def navPoint(posiX, posiY, posiZ, oriX, oriY, oriZ, oriW):
    navGoal = MoveBaseGoal()
    navGoal.target_pose.header.frame_id="map"
    navGoal.target_pose.header.frame_id="map"
    navGoal.target_pose.pose.position.x = posiX
    navGoal.target_pose.pose.position.y = posiY
    navGoal.target_pose.pose.position.z = posiZ
    navGoal.target_pose.pose.orientation.x = oriX
    navGoal.target_pose.pose.orientation.y = oriY
    navGoal.target_pose.pose.orientation.z = oriZ
    navGoal.target_pose.pose.orientation.w = oriW
    actionClient.send_goal(navGoal)
    rospy.loginfo("[ILibrary] Nav Start")
    actionClient.wait_for_result()
    rospy.loginfo("[ILibrary] Nav Done")
    fakeSer()


def statusProcess(r):
    global STATUS
    if STATUS == "STANDBY":
        waitForJob()
    elif STATUS == "NAVPOINT":
        navPoint(r.data.posi_x, r.data.posi_y, r.data.posi_z, r.data.ori_x, r.data.ori_y, r.data.ori_z, r.data.ori_w)
    else:
        rospy.loginfo("[ILibrary] Unknow status")

def init():
    #cmdLock = threading.Lock()
    #cmdQueryThread = threading.Thread(target=cmdQuery)
    #cmdQueryThread.start()
    print("Init")

def fakeSer():
    navPoint(-5.0, 2.0, 0.0, 0.0, 0.0, 0.0, 1.0)

if __name__ == "__main__":
    rospy.loginfo("[ILibrary] Service Start")
    init()
    rospy.init_node("simple_goal")
    actionClient = actionlib.SimpleActionClient('move_base', MoveBaseAction)
    actionClient.wait_for_server()
    navPoint(-3.0, 2.0, 0.0, 0.0, 0.0, 0.0, 1.0)
    rospy.loginfo("[ILibrary] Service Exit")