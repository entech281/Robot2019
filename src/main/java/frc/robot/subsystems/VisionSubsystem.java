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

    private double UNKNOWN = 99999999;
    private double lastFrameCount = 0;
    private double lastDistanceFromTarget = UNKNOWN;
    private double lastLateralDistance = UNKNOWN;

    //private double scaleFactor = 1.0;


    private NetworkTableInstance ntist;
    NetworkTableEntry distance;
    NetworkTableEntry lateral;
    NetworkTableEntry frameCount;


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
        periodicStopWatch.start("Vision subsystem");
        SmartDashboard.putNumber("Frame Count:", frameCount.getDouble(UNKNOWN));
        SmartDashboard.putNumber("Vision Distance To Target:", distance.getDouble(UNKNOWN));
        SmartDashboard.putNumber("Vision Lateral:", lateral.getDouble(UNKNOWN));  
        periodicStopWatch.end("Vision subsystem");
    }

    @Override
    public DriveInput getDriveInput() {
        double currFrameCount = frameCount.getDouble(lastFrameCount);
        DriveInput di = new DriveInput();  // created as invalid
        if (currFrameCount > lastFrameCount) {
          //scaleFactor = 1.0;
          lastDistanceFromTarget = distance.getDouble(UNKNOWN);
          lastLateralDistance = (-1)*lateral.getDouble(UNKNOWN);
          di.setTargetDistance(lastDistanceFromTarget);
          if(lastLateralDistance <= (UNKNOWN-1)){
          di.setTargetLateral(lastLateralDistance);
          }
        } 
        /*else {
          scaleFactor = 0.75*scaleFactor;
          if ((Math.abs(lastLateralDistance-UNKNOWN) > 0.1) && 
              (Math.abs(lastDistanceFromTarget-UNKNOWN) > 0.1)) {
                di.setTargetDistance(scaleFactor*lastDistanceFromTarget);
                di.setTargetLateral(scaleFactor*lastLateralDistance);
                }
          
        }*/
        
        return di;
    }

}