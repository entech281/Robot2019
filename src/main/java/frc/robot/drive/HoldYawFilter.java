/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.drive;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;

/**
 * Add your docs here.
 */
public class HoldYawFilter extends DriveFilter implements PIDOutput {
  Robot robot;
  PIDController yaw_pid;
  double pid_twist;
  double Kp = 0.1;
  double Ki = 0.0;
  double Kd = 0.0;

  public HoldYawFilter(Robot robot) {
    super(false);
    this.robot = robot;
    yaw_pid = new PIDController(Kp, Ki, Kd, this.robot.getNavXSubsystem(), this);
    yaw_pid.setInputRange(-180.0, 180.0);
    yaw_pid.setContinuous(true);
    yaw_pid.setOutputRange(-1.0, 1.0);
    yaw_pid.setPercentTolerance(1.0);
    yaw_pid.setSetpoint(0.0);
    yaw_pid.enable();
  }

  @Override
  public void onEnable() {
    yaw_pid.enable();
    yaw_pid.reset();
  }

  @Override
  protected void onDisable() {
  }

  @Override
  public void pidWrite(double pid_out) {
    this.pid_twist = pid_out;
  }

   // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public void setRobotYaw(double angle) {
    yaw_pid.reset();
    yaw_pid.setSetpoint(angle);
  }

  @Override
  public DriveInput doFilter(DriveInput input) {
    return new DriveInput(input.getX(), input.getY(), this.pid_twist, input.getFieldAngle(), input.getTargetX(), input.getTargetY());
  }
}
