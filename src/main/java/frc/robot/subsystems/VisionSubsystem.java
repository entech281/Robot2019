package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.navigation.NavigationManager;

public class VisionSubsystem extends BaseSubsystem {
    
    private NavigationManager navigation;
    private double UNKNOWN = 9999999;
    private NetworkTableInstance ntist;
    NetworkTableEntry vision; 

    public VisionSubsystem(NavigationManager navigationManager){
        this.navigation = navigationManager;
    }

    @Override
    public void initialize(){
        ntist = NetworkTableInstance.getDefault();
        vision = ntist.getEntry("team281.Vision");
    }

    @Override
    public void periodic(){
        double distance_from_target = vision.getDouble(UNKNOWN);
        navigation.acceptVisionPoseUpdate(distance_from_target);
    }

}