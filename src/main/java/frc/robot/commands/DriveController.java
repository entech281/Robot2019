/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frc.robot.commands;

import frc.robot.navigation.RobotPose;
import frc.robot.oi.OperatorDriveInput;
import frc.robot.subsystems.DriveInstruction;

/**
 *
 * @author dcowden
 */
public interface DriveController {
    public DriveInstruction getDriveInstruction( RobotPose robotPose, OperatorDriveInput driverInput);
}
