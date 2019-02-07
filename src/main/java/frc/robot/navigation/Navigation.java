package frc.robot.navigation;

import frc.robot.navigation.RobotPose;

public interface Navigation {
    frc.robot.navigation.RobotPose getEstimatedRobotPose();
}