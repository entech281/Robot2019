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
            di.setFieldAngle(-navX.getAngle());
        }
        return di;
    }

    public double getAngle() {
        if (navX != null) {
            return -navX.getAngle();
        } else {
            return 720.0;
        }
    }

    public void zeroYaw() {
        navX.zeroYaw();
    }

    @Override
    public void periodic() {
        if (navX != null) {
            SmartDashboard.putNumber("Gyro Angle", -navX.getAngle());
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
      return -navX.getAngle();
    }

    public double findNearestQuadrant() {
        double angle = -navX.getAngle();
        while (angle > 360.0) {
          angle -= 360.0;
        }
        while (angle < 0.0) {
          angle += 360.0;
        }
        if (angle < 22.5) {
          return 0.0;
        } else if ( angle <= 67.5 ) {
          return 45.0;
        } else if ( angle <= 112.5 ) {
          return 90.0;
        } else if ( angle < 157.5 ) {
          return 135.0;
        } else if ( angle <= 202.5 ) {
          return 180.0;
        } else if ( angle < 247.5 ) {
          return 225.0;
        } else if ( angle < 292.5 ) {
          return 270.0;
        } else if ( angle < 337.5 ) {
          return 315.0;
        } else {
          return 360.0;
        }
      }
         
}
