package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.drive.DriveInput;
import frc.robot.drive.GetDriveInput;


public class VisionSubsystem extends BaseSubsystem implements GetDriveInput {

    private double UNKNOWN = -5;
    private NetworkTableInstance ntist;
    NetworkTableEntry distance;
    NetworkTableEntry lateral;

    DriveInput driveInput = new DriveInput();

    public VisionSubsystem() {
    }

    @Override
    public void initialize() {
        ntist = NetworkTableInstance.getDefault();
        distance = ntist.getEntry("team281.Vision.distance");
        lateral = ntist.getEntry("team281.Vision.lateral");
    }

    @Override
    public void periodic() {
    }

    @Override
    public DriveInput getDriveInput() {
        double distanceFromTarget = distance.getDouble(UNKNOWN);
        double lateralDistance = distance.getDouble(UNKNOWN);
        driveInput.setTargetX(lateralDistance);
        driveInput.setTargetY(distanceFromTarget);
        return null;
    }

}