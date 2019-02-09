package frc.robot.navigation;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.navigation.RobotPose;;

/**
 * Responsible for tracking the robot's pose during a match. No unit testable
 * classes should depend on Navigation manager, only on Navigation ( or , even
 * better, just getting an estimated pose)
 * 
 * This class is a a holder for all the logic that synthesizes all the info
 * about what our pose is, and combines it.
 * 
 * eventually, this class could store previous updates, so it can use behavior
 * over time to provide better estimates
 * 
 * different methods are provided for each subsystem, because we may trust more
 * than others
 * 
 * @author dcowden
 */
public class NavigationManager implements Navigation{

    private RobotPose currentPose = new RobotPose();
    
    public NavigationManager(){
        
    }
    
    @Override
    public RobotPose getEstimatedRobotPose() {
        //should combine all the logic to figure out our best pose guess.
        return currentPose;
    }
    
    public void acceptVisionPoseUpdate(double targetDistance){
        currentPose.setDistanceToTarget(targetDistance);
    }
    
    public void acceptSensorPoseUpdate(double lateralOffset){
        //probably only lateral direction. 
        //but we trust this a LOT when we have it
    }
    
    public void acceptNavXPoseUpdate(double robotAngle){
        //updates robot angle on field
    }
}