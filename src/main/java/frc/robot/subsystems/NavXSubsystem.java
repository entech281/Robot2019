/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
      return navX.getAngle();
    }
     
}
