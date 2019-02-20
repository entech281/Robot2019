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
    NetworkTableEntry targetFound;

    DriveInput driveInput = new DriveInput();

    public VisionSubsystem() {
    }

    @Override
    public void initialize() {
        ntist = NetworkTableInstance.getDefault();
        distance = ntist.getEntry("team281.Vision.distance");
        lateral = ntist.getEntry("team281.Vision.lateral");
        frameCount = ntist.getEntry("team281.frameCount");
        targetFound = ntist.getEntry("team281.Vision.targetFound");
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Frame Count:", frameCount.getDouble(UNKNOWN));   
    }

    @Override
    public DriveInput getDriveInput() {
        boolean targetAreFound = targetFound.getBoolean(false);
        double distanceFromTarget = distance.getDouble(UNKNOWN);
        double lateralDistance = lateral.getDouble(UNKNOWN);
        if(targetAreFound==true){
            driveInput.setTargetX(lateralDistance);
            driveInput.setTargetY(distanceFromTarget);
        }
        return null;
    }

}