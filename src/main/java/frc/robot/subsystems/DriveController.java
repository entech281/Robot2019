package frc.robot.subsystems;

import frc.robot.navigation.RobotPose;
import frc.robot.oi.OperatorDriveCommand;

/**
 *
 * @author dcowden
 */
public class DriveController {
    
    //just a single method for now.
    //this is where we can build up a list of filters, and layer the output on top
    //of the input
    public DriveCommand getDriveCommand( OperatorDriveCommand driverInput, RobotPose robotPose){

        //this can be 
        if ( driverInput.isFieldAbsolute()){
            return new DriveCommand(
                driverInput.getX(),
                driverInput.getY(),
                driverInput.getZ(),
                robotPose.getRobotAngleOnField()
            );
        }
        else{
            return new DriveCommand(
                driverInput.getX(),
                driverInput.getY(),
                driverInput.getZ(),
                0.0
            );            
        }
    }
}
