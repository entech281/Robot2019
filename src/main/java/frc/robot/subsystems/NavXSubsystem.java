/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.drive.DriveInput;
import frc.robot.drive.GetDriveInput;

/**
 *
 * @author dcowden
 */
public class NavXSubsystem extends BaseSubsystem implements GetDriveInput {

    private final AHRS navX = new AHRS(SPI.Port.kMXP);
    
    public NavXSubsystem() {
    }

    @Override
    public void initialize() {
    }
    
    @Override
    public DriveInput getDriveInput() {
        DriveInput di = new DriveInput();
        if (navX != null) {
            di.setFieldAngle(navX.getAngle());
        }
        return di;
    }

    public double getAngle() {
        if (navX != null) {
            return navX.getAngle();
        } else {
            return 720.0;
        }
    }

    @Override
    public void periodic() {
        if (navX != null) {
            SmartDashboard.putNumber("Gyro Angle", navX.getAngle());
        }
    }    
}
