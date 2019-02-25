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
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.drive.DriveInput;
import frc.robot.drive.GetDriveInput;

/**
 *
 * @author dcowden
 */
public class NavXSubsystem extends BaseSubsystem implements GetDriveInput,PIDOutput {

  private final AHRS navX = new AHRS(SPI.Port.kMXP);
  private double angle_scale = 1.0;

  PIDController yaw_pid;
  double Kp = 0.01;
  double Ki = 0.0;
  double Kd = 0.0;

  private double pid_twist;
      
  public NavXSubsystem() {
  }

  @Override
  public void initialize() {
    while (navX.isCalibrating()) {
      ;
    }
    navX.zeroYaw();

    yaw_pid = new PIDController(Kp, Ki, Kd, navX, this);
    yaw_pid.setInputRange(-180.0, 180.0);
    yaw_pid.setContinuous(true);
    yaw_pid.setOutputRange(-1.0, 1.0);
    yaw_pid.setPercentTolerance(1.0);
    yaw_pid.setSetpoint(0.0);
    yaw_pid.enable();
  }
    
  @Override
  public DriveInput getDriveInput() {
    DriveInput di = new DriveInput();
    if (navX != null) {
      di.setFieldAngle(angle_scale*navX.getYaw());
    }
    return di;
  }

  public double getAngle() {
    if (navX != null) {
      return angle_scale*navX.getYaw();
    } else {
      return 360.0;
    }
  }

  public void zeroYaw() {
    navX.zeroYaw();
  }

  public void flipOutputAngle(boolean flip) {
    if (flip) {
      angle_scale = -1.0;
    } else {
      angle_scale = 1.0;
    }
  }

  @Override
  public void periodic() {
    if (navX != null) {
      SmartDashboard.putData(navX);
      SmartDashboard.putNumber("Yaw Angle", angle_scale*navX.getYaw());
      SmartDashboard.putNumber("Field Angle", angle_scale*navX.getAngle());
    }
  }    
  
  public void holdYawAngle(boolean enable) {
    yaw_pid.disable();
    if (enable) {
      yaw_pid.setSetpoint(findNearestQuadrant());
      yaw_pid.reset();
      yaw_pid.enable();
    }
  }

  @Override
  public void pidWrite(double pid_out) {
    SmartDashboard.putNumber("NavXSubsystem pidWrite()", pid_out);
    pid_twist = pid_out;
  }

  public double getTwist() {
    return pid_twist;
  }

  public double findNearestQuadrant() {
    double angle = angle_scale*navX.getYaw();
    if (angle <= -157.5) {
      return -180.0;
    } else if (angle < -112.5) {
      return -135.0;
    } else if (angle <= -67.5) {
      return -90.0;
    } else if (angle < -22.5) {
      return -45.0;
    } else if (angle <= 22.5) {
      return 0.0;
    } else if (angle < 67.5) {
      return 45.0;
    } else if (angle <= 112.5) {
      return 90.0;
    } else if (angle < 157.5) {
      return 135.0;
    } else {
      return 180.0;
    }
  }

}
