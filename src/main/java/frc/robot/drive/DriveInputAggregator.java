package frc.robot.drive;


/**
 *
 * @author dcowden
 */
public class DriveInputAggregator {
    
    private boolean enableLineSensors = true;
    private boolean enableVision = true;
    
    public DriveInputAggregator(boolean enableLineSensors, boolean enableVision){
        this.enableLineSensors=enableLineSensors;
        this.enableVision = enableVision;
    }

    
    public DriveInput mergeTelemetry(DriveInput input, DriveInput navx, DriveInput vision, DriveInput sensors){
        DriveInput result = input.copy();
       
        if (navx.isValid()) {
          result.setFieldAngle(navx.getFieldAngle());
        }
        
        if (vision.isValid() && enableVision) {
          result.setTargetDistance(vision.getTargetDistance());
          result.setTargetLateral(vision.getTargetLateral());
        }

        if (sensors.isValid() && enableLineSensors) {
          result.setFieldAngle(sensors.getFieldAngle());
          result.setTargetDistance(sensors.getTargetDistance());
          result.setTargetLateral(sensors.getTargetLateral());
        }

        return result;       
    }
}
