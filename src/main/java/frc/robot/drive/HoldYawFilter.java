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
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

/**
 * Add your docs here.
 */
public class HoldYawFilter extends DriveFilter implements PIDSource,PIDOutput {
  Robot robot;
  PIDController yaw_pid;
  PIDSourceType pidSourceType = PIDSourceType.kRate;
  double pid_twist;
  double Kp = 0.03;
  double Ki = 0.0001;
  double Kd = 0.01;

  public HoldYawFilter(Robot robot) {
    super(false);
    this.robot = robot;
    yaw_pid = new PIDController(Kp, Ki, Kd, this, this);
    yaw_pid.setInputRange(-180.0, 180.0);
    yaw_pid.setContinuous(true);
    yaw_pid.setOutputRange(-1.0, 1.0);
    yaw_pid.setPercentTolerance(1.0);
    yaw_pid.enable();
  }

  @Override
  public void onEnable() {
    if (isEnabled()) {
      return;
    }
    yaw_pid.enable();
    yaw_pid.reset();
  }

  @Override
  protected void onDisable() {
    yaw_pid.disable();
  }

  @Override
  public PIDSourceType getPIDSourceType() {
    return pidSourceType;
  }

  @Override
  public void setPIDSourceType(PIDSourceType pidSource) {
    pidSourceType = pidSource;
  }

  @Override
  public double pidGet() {
    return this.robot.getNavXSubsystem().getAngle();
  }

  @Override
  public void pidWrite(double pid_out) {
    this.pid_twist = pid_out;
  }

   // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public void setRobotYaw(double angle) {
    yaw_pid.setSetpoint(angle);
  }

  @Override
  public DriveInput doFilter(DriveInput input) {
    return new DriveInput(input.getX(), input.getY(), this.pid_twist, input.getFieldAngle(), input.getTargetX(), input.getTargetY());
  }
}
