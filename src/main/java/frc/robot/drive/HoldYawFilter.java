/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.drive;

import frc.robot.Robot;

/**
 * Add your docs here.
 */
public class HoldYawFilter extends DriveFilter {
  Robot robot;

  public HoldYawFilter(Robot robot) {
    super(false);
    this.robot = robot;
  }

  public void holdYawAngle() {
    this.robot.getNavXSubsystem().holdYawAngle(true);
  }

  @Override
  public DriveInput doFilter(DriveInput input) {
    return new DriveInput(input.getX(), input.getY(), this.robot.getNavXSubsystem().getTwist(), input.getFieldAngle(), input.getTargetX(), input.getTargetY());
  }
}
