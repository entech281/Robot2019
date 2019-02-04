/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;

/**
 * Add your docs here.
 */
public class HoldYawDriveFilter extends DriveFilter implements PIDOutput {
  PIDController yaw_pid;
  double pid_twist;
  double Kp = 0.03;
  double Ki = 0.0001;
  double Kd = 0.01;

  public HoldYawDriveFilter(AHRS navx) {
    super();
    yaw_pid = new PIDController(Kp, Ki, Kd, navx, this);
    yaw_pid.setInputRange(-180.0, 180.0);
    yaw_pid.setContinuous(true);
    yaw_pid.setOutputRange(-1.0, 1.0);
    yaw_pid.setPercentTolerance(1.0);
    yaw_pid.enable();
  }

  public void setRobotYaw(double angle) {
    yaw_pid.setSetpoint(angle);
  }

  @Override
  public void enable() {
    if (isEnabled()) {
      return;
    }
    super.enable();
    yaw_pid.reset();
  }

  @Override
  public void pidWrite(double pid_out) {
    this.pid_twist = pid_out;
  }

    // Put methods for controlling this subsystem
  // here. Call these from Commands.
  @Override
  public DriveCommand doFilter(DriveCommand input) {
    return new DriveCommand(input.getX(), input.getY(), this.pid_twist, input.getFieldAngle());
  }
}
