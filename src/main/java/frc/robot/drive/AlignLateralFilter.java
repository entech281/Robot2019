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
  double Kp =  0.1;
  double Ki =  0.0;
  double Kd = -0.2;
  boolean lineSensorsActive = true;

  public AlignLateralFilter(Robot robot) {
    super(false);
    this.robot = robot;
    lateral_pid = new PIDController(Kp, Ki, Kd, this.robot.getVisionSubsystem(), this);
    lateral_pid.setOutputRange(-1.0, 1.0);
    lateral_pid.setPercentTolerance(1.0);
    lateral_pid.enable();
  }

  @Override
  public void onEnable() {
    lateral_pid.setSetpoint(0.0);
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

  public void useLineSensors(boolean enable) {
    lineSensorsActive = enable;
  }

  @Override
  public DriveInput doFilter(DriveInput input) {
    double x_js = this.pid_lateral;
    // If we have valid line sensor data, use in in place of vision
    if (lineSensorsActive && this.robot.getSensorSubsystem().isSensorDataValid()) {
      if (input.getTargetY() > 0.0) {
        x_js = -0.5;
      } else if (input.getTargetY() < 0.0) {
        x_js = 0.5;
      } else {
        x_js = 0.0;
      }
    }
    return new DriveInput(x_js, input.getY(), input.getZ(), 0.0, input.getTargetX(), input.getTargetY());
  }
}