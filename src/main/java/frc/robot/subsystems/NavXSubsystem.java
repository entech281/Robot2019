/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.drive.DriveInput;
import frc.robot.drive.GetDriveInput;

/**
 *
 * @author dcowden
 */
public class NavXSubsystem extends BaseSubsystem implements GetDriveInput,PIDSource {

    private final AHRS navX = new AHRS(SPI.Port.kMXP);
    private double angle_scale = -1.0;
    
    public NavXSubsystem() {
    }

    @Override
    public void initialize() {
        DriverStation.reportWarning("NavX Initialize Start", false);
        while (navX.isCalibrating()) {
            ;
        }
        navX.zeroYaw();
        DriverStation.reportWarning("NavX Initialize Complete", false);
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
            SmartDashboard.putNumber("Yaw Angle", angle_scale*navX.getYaw());
        }
    }    
 
    @Override
    public PIDSourceType getPIDSourceType() {
      return navX.getPIDSourceType();
    }
  
    @Override
    public void setPIDSourceType(PIDSourceType pidSource) {
      navX.setPIDSourceType(pidSource);
    }
  
    @Override
    public double pidGet() {
      return angle_scale*navX.getYaw();
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
