/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.navigation;

import frc.robot.navigation.RobotPose;

/**
 * Add your docs here.
 */
public interface Navigation {
    frc.robot.navigation.RobotPose getEstimatedRobotPose();
}