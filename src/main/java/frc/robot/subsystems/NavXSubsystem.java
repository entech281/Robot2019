/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

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

    private PIDSourceType pidSourceType = PIDSourceType.kRate;
    private final AHRS navX = new AHRS(SPI.Port.kMXP);
    
    public NavXSubsystem() {
    }

    @Override
    public void initialize() {
        navX.zeroYaw();
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
      return pidSourceType;
    }
  
    @Override
    public void setPIDSourceType(PIDSourceType pidSource) {
      pidSourceType = pidSource;
    }
  
    @Override
    public double pidGet() {
      return navX.getAngle();
    }
     
}
