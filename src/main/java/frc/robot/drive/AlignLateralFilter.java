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
public class AlignLateralFilter extends DriveFilter implements PIDOutput {
  Robot robot;
  PIDController lateral_pid;
  double pid_lateral;
  double Kp = -0.1;
  double Ki =  0.0;
  double Kd = -0.2;

  public AlignLateralFilter(Robot robot) {
    super(false);
    this.robot = robot;
    lateral_pid = new PIDController(Kp, Ki, Kd, this.robot.getVisionSubsystem(), this);
    lateral_pid.setInputRange(-180.0, 180.0);
    lateral_pid.setContinuous(true);
    lateral_pid.setOutputRange(-1.0, 1.0);
    lateral_pid.setPercentTolerance(1.0);
    lateral_pid.enable();
  }

  @Override
  public void onEnable() {
    if (isEnabled()) {
      return;
    }
    lateral_pid.enable();
    lateral_pid.reset();
  }

  @Override
  protected void onDisable() {
    lateral_pid.disable();
  }

  @Override
  public void pidWrite(double pid_out) {
    this.pid_lateral = pid_out;
  }

   // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public void setRobotYaw(double angle) {
    lateral_pid.setSetpoint(angle);
  }

  @Override
  public DriveInput doFilter(DriveInput input) {
    return new DriveInput(this.pid_lateral, input.getY(), input.getZ(), 0.0, input.getTargetX(), input.getTargetY());
  }
}