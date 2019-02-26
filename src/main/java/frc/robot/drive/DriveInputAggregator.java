package frc.robot.drive;


/**
 *
 * @author dcowden
 */
public class DriveInputAggregator {
    
    public DriveInput mergeTelemetry(DriveInput input, DriveInput navx, DriveInput vision, DriveInput sensors){
        DriveInput result = input.copy();
       
        if (navx.isValid()) {
          result.setFieldAngle(navx.getFieldAngle());
        }
        
        if (vision.isValid()) {
          result.setTargetX(vision.getTargetX());
          result.setTargetY(vision.getTargetY());
        }

        if (sensors.isValid()) {
          result.setFieldAngle(sensors.getFieldAngle());
          result.setTargetX(sensors.getTargetX());
          result.setTargetY(sensors.getTargetY());
        }

        return result;       
    }
}
