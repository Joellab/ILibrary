#include <ros/ros.h>
#include <tf/transform_broadcaster.h>
#include <tf/transform_listener.h>
#include <geometry_msgs/Pose2D.h>
#include <nav_msgs/Odometry.h>
#include <geometry_msgs/Twist.h>
#include <sensor_msgs/JointState.h>
#include <std_msgs/String.h>
#include <std_msgs/Float64.h>
#include <math.h>

static ros::Publisher mani_base_pub;
static ros::Publisher elbow_forearm_pub;
static ros::Publisher palm_left_finger_pub;
static ros::Publisher left_finger_tip_pub;
static ros::Publisher palm_right_finger_pub;
static ros::Publisher right_finger_tip_pub;
static geometry_msgs::Pose2D pose_reset;
static geometry_msgs::Pose2D pose_cur;
static  tf::Transform tf_odom_reset;
static tf::TransformBroadcaster* tf_broadcast;

void ManiGripper(float inGripperValue)
{
    float fGripperAngle = asin((inGripperValue - 0.025)*5);
    std_msgs::Float64 gripper_msg;
    gripper_msg.data = fGripperAngle;
    palm_left_finger_pub.publish(gripper_msg);
    right_finger_tip_pub.publish(gripper_msg);
     gripper_msg.data = -fGripperAngle;
    left_finger_tip_pub.publish(gripper_msg);
    palm_right_finger_pub.publish(gripper_msg);
}
static float nMinHeight = 0.493;
static float nMaxHeight = 1.036;
static float nBottomHeight = 0.32;
void ManiHeight(float inHeight)
{
    std_msgs::Float64 joint_pos_msg;
    if(inHeight >= nMinHeight)
    {
        joint_pos_msg.data  =  1.5707963;
        elbow_forearm_pub.publish(joint_pos_msg);
        float fLiftPos = inHeight - nBottomHeight;
        if(inHeight >nMaxHeight )
        {
            fLiftPos = nMaxHeight - nBottomHeight;
        }
        joint_pos_msg.data = fLiftPos;
        mani_base_pub.publish(joint_pos_msg);
    }
    else
    {
         joint_pos_msg.data  =  0;
        elbow_forearm_pub.publish(joint_pos_msg);
        joint_pos_msg.data = 0;
        mani_base_pub.publish(joint_pos_msg);
    }
}

void ManiCtrlCallback(const sensor_msgs::JointState::ConstPtr& msg)
{
    int nNumJoint = msg->position.size();
    for(int i=0;i<nNumJoint;i++)
    {
        //ROS_INFO("[wpb_home] %d - %s = %.2f  vel= %.2f", i, msg->name[i].c_str(),msg->position[i],msg->velocity[i]);
        if(msg->name[i] == "lift")
        {
            //高度升降
            ManiHeight(msg->position[i]);
        }
        if(msg->name[i] == "gripper")
        {
            //手爪
            ManiGripper(msg->position[i]);
        }
    }
}

static geometry_msgs::Pose2D pose_diff_msg;
void CtrlCallback(const std_msgs::String::ConstPtr &msg)
{
    int nFindIndex = 0;
    nFindIndex = msg->data.find("pose_diff reset");
    if( nFindIndex >= 0 )
    {
        pose_diff_msg.x = 0;
        pose_diff_msg.y = 0;
        pose_diff_msg.theta = 0;
        //ROS_WARN("[pose_diff reset]");
        pose_reset.x = pose_cur.x;
        pose_reset.y = pose_cur.y;
        pose_reset.theta = pose_cur.theta;
        tf_odom_reset.setOrigin( tf::Vector3(pose_reset.x, pose_reset.y, 0.0) );
        tf_odom_reset.setRotation( tf::createQuaternionFromRPY(0,0,pose_reset.theta) );
    }
}

void OdomCallback(const nav_msgs::Odometry::ConstPtr& msg)
{
    pose_cur.x = msg->pose.pose.position.x;
    pose_cur.y = msg->pose.pose.position.y;
    tf::Quaternion q(msg->pose.pose.orientation.x,msg->pose.pose.orientation.y,msg->pose.pose.orientation.z,msg->pose.pose.orientation.w);
    pose_cur.theta = tf::getYaw(q);
    //ROS_WARN("[Odom]  ( %.2f  %.2f )   %.2f",pose_cur.x,pose_cur.y,pose_cur.theta);
    tf_broadcast->sendTransform(tf::StampedTransform(tf_odom_reset, ros::Time::now(), "/odom", "/pose_reset"));
}

int main(int argc, char** argv)
{
    ros::init(argc,argv,"wpb_home_sim");
    ros::NodeHandle n;
    tf_broadcast = new tf::TransformBroadcaster;
    tf_odom_reset.setOrigin( tf::Vector3(0.0, 0.0, 0.0) );
    tf_odom_reset.setRotation( tf::createQuaternionFromRPY(0,0,0) );
    tf::TransformListener listener;
    tf::StampedTransform tf_diff;

    mani_base_pub = n.advertise<std_msgs::Float64>("/wpb_home/mani_base_position_controller/command",10);
    elbow_forearm_pub = n.advertise<std_msgs::Float64>("/wpb_home/elbow_forearm_position_controller/command",10);
    palm_left_finger_pub = n.advertise<std_msgs::Float64>("/wpb_home/palm_left_finger_position_controller/command",10);
    left_finger_tip_pub = n.advertise<std_msgs::Float64>("/wpb_home/left_finger_tip_position_controller/command",10);
    palm_right_finger_pub = n.advertise<std_msgs::Float64>("/wpb_home/palm_right_finger_position_controller/command",10);
    right_finger_tip_pub = n.advertise<std_msgs::Float64>("/wpb_home/right_finger_tip_position_controller/command",10);

    ros::Subscriber mani_ctrl_sub = n.subscribe("/wpb_home/mani_ctrl",30,&ManiCtrlCallback);
    ros::Subscriber ctrl_sub = n.subscribe("/wpb_home/ctrl",10,&CtrlCallback);
    ros::Subscriber odom_sub = n.subscribe("/odom", 1, OdomCallback);
    ros::Publisher pose_diff_pub = n.advertise<geometry_msgs::Pose2D>("/wpb_home/pose_diff",1);
    pose_diff_msg.x = 0;
    pose_diff_msg.y = 0;
    pose_diff_msg.theta = 0;


    ros::Rate r(20.0);

    while(n.ok())
    {
        bool res = listener.waitForTransform("/pose_reset","/base_footprint",ros::Time(0), ros::Duration(1.0));
        if(res == true)
        {
            listener.lookupTransform("/pose_reset","/base_footprint",ros::Time(0),tf_diff);

            pose_diff_msg.x = tf_diff.getOrigin().x()*0.9;
            pose_diff_msg.y = tf_diff.getOrigin().y()*0.9;
            tf::Quaternion pose_quat = tf_diff.getRotation ();
            float pose_yaw = tf::getYaw(pose_quat);
            pose_diff_msg.theta = pose_yaw - pose_reset.theta;
            pose_diff_pub.publish(pose_diff_msg);
            //ROS_INFO("[pose_diff_msg] x= %.2f  y=%.2f  th= %.2f", pose_diff_msg.x,pose_diff_msg.y,pose_diff_msg.theta);
        }

        ros::spinOnce();
        r.sleep();
    }
    delete tf_broadcast;
}