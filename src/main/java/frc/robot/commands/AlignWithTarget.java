/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class AlignWithTarget extends Command {
  private Robot robot;

  public AlignWithTarget(Robot robot) {
    // Use requires() here to declare subsystem dependencies
    this.robot = robot;
    requires(this.robot.getDriveSubsystem());
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    double angle = this.robot.getNavXSubsystem().findNearestQuadrant();
    this.robot.getDriveSubsystem().enableHoldYaw(angle);
    this.robot.getDriveSubsystem().alignWithTarget(true);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
