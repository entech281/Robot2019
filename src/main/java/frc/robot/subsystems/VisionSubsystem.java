/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.drive.DriveInput;
import frc.robot.drive.GetDriveInput;


public class VisionSubsystem extends BaseSubsystem implements GetDriveInput {

    private double UNKNOWN = -5;
    private NetworkTableInstance ntist;
    NetworkTableEntry distance;
    NetworkTableEntry lateral;
    NetworkTableEntry frameCount;

    DriveInput driveInput = new DriveInput();

    public VisionSubsystem() {
    }

    @Override
    public void initialize() {
        ntist = NetworkTableInstance.getDefault();
        distance = ntist.getEntry("team281.Vision.distance");
        lateral = ntist.getEntry("team281.Vision.lateral");
        frameCount = ntist.getEntry("team281.frameCount");
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Frame Count:", frameCount.getDouble(UNKNOWN));   
    }

    @Override
    public DriveInput getDriveInput() {
        double distanceFromTarget = distance.getDouble(UNKNOWN);
        double lateralDistance = lateral.getDouble(UNKNOWN);
        driveInput.setTargetX(lateralDistance);
        driveInput.setTargetY(distanceFromTarget);
        return null;
    }

}