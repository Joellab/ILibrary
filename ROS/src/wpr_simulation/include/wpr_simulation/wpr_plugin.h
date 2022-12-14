#ifndef _WPR_PLUGIN_H_
#define _WPR_PLUGIN_H_

#include <boost/bind.hpp>
#include <boost/thread.hpp>
#include <map>

#include <gazebo/common/common.hh>
#include <gazebo/physics/physics.hh>
#include <sdf/sdf.hh>

#include <geometry_msgs/Twist.h>
#include <nav_msgs/OccupancyGrid.h>
#include <nav_msgs/Odometry.h>
#include <ros/advertise_options.h>
#include <ros/callback_queue.h>
#include <ros/ros.h>
#include <tf/transform_broadcaster.h>
#include <tf/transform_listener.h>

namespace gazebo {

  class WPRPlugin : public ModelPlugin {

    public: 
      WPRPlugin();
      ~WPRPlugin();
      void Load(physics::ModelPtr parent, sdf::ElementPtr sdf);

    protected: 
      virtual void UpdateChild();
      virtual void FiniChild();

    private:
      void publishOdometry(double step_time);

      tf::Transform getTransformForMotion(double linear_vel_x, double linear_vel_y, double angular_vel, double timeSeconds) const;

      physics::ModelPtr parent_;
      event::ConnectionPtr update_connection_;

      physics::LinkPtr link_;

      private: std::string link_name_;

      boost::shared_ptr<ros::NodeHandle> rosnode_;
      ros::Publisher odometry_pub_;
      ros::Subscriber vel_sub_;
      boost::shared_ptr<tf::TransformBroadcaster> transform_broadcaster_;
      nav_msgs::Odometry odom_;
      std::string tf_prefix_;

      tf::Transform odom_transform_;

      boost::mutex lock;

      std::string robot_namespace_;
      std::string command_topic_;
      std::string odometry_topic_;
      std::string odometry_frame_;
      std::string robot_base_frame_;
      double odometry_rate_;
      bool publish_odometry_tf_;

      // Custom Callback Queue
      ros::CallbackQueue queue_;
      boost::thread callback_queue_thread_;
      void QueueThread();

      // command velocity callback
      void cmdVelCallback(const geometry_msgs::Twist::ConstPtr& cmd_msg);

      double x_;
      double y_;
      double rot_;
      bool alive_;
      common::Time last_odom_publish_time_;
      #if GAZEBO_MAJOR_VERSION >= 8
        ignition::math::Pose3d last_odom_pose_;
      #else
        math::Pose last_odom_pose_;
      #endif
      
      double torque_yaw_velocity_p_gain_;
      double force_x_velocity_p_gain_;
      double force_y_velocity_p_gain_;

  };

}
#endif  //_WPR_PLUGIN_HH_