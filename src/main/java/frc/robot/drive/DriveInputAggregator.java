package frc.robot.drive;

import frc.robot.subsystems.NavXSubsystem;


/**
 *
 * @author dcowden
 */
public class DriveInputAggregator {
    
    private boolean enableLineSensors = true;
    private boolean enableVision = true;
    private boolean includeAngleOffset = true;
    
    public DriveInputAggregator(boolean enableLineSensors, boolean enableVision, boolean includeAngleOffset){
        this.enableLineSensors=enableLineSensors;
        this.enableVision = enableVision;
        this.includeAngleOffset = includeAngleOffset;
    }

    
    public DriveInput mergeTelemetry(DriveInput input, DriveInput navx, DriveInput vision, DriveInput sensors){
        DriveInput result = input.copy();
       
        if (navx.isValid()) {
          result.setFieldAngle(navx.getFieldAngle());
        }
        
        if (vision.isValid() && enableVision) {
            
          //this version considers lateral offset via misalignment
          //to the target
          //if we're looking askew at the targets, we have to adjust 
          //for our perspective when calculating alignment
          if ( navx.isValid() && includeAngleOffset ){
             double nearestQuadrantAngle = NavXSubsystem.findNearestQuadrant(navx.getFieldAngle());
             double deltaAngle = nearestQuadrantAngle - navx.getFieldAngle();
             double deltaAngleRads = Math.toRadians(deltaAngle);
             double dist = vision.getTargetDistance()*Math.cos(deltaAngleRads);
             double offsetDueToAngle = vision.getTargetDistance()*Math.sin(deltaAngleRads);
             double projectedLateralOffset = vision.getTargetLateral()*Math.cos(deltaAngleRads);
             //System.out.println("visionDistance" + vision.getTargetDistance());
             //System.out.println("visionTargetLateral" + vision.getTargetLateral());
             //System.out.println("deltaRads=" + deltaAngleRads);
             //System.out.println("offestDueToAngle=" + offsetDueToAngle);
             //System.out.println("projectedLateralOffset=" + projectedLateralOffset);
             result.setTargetDistance(dist);
             result.setTargetLateral(offsetDueToAngle+projectedLateralOffset);
          }
          else{
            //this version does not consider the lateral affect of 
            //angular offset to the target.              
            result.setTargetDistance(vision.getTargetDistance());
            result.setTargetLateral(vision.getTargetLateral());  
          }
        }

        if (sensors.isValid() && enableLineSensors) {
          result.setFieldAngle(sensors.getFieldAngle());
          result.setTargetDistance(sensors.getTargetDistance());
          result.setTargetLateral(sensors.getTargetLateral());
        }
        
        if ( ! canDisplay(result.getTargetLateral())){
            result.setTargetLateral(8888.8);
        }
        return result;       
    }
    
    public static boolean canDisplay(double d){
        if ( Double.isNaN(d) ){
            return false;
        }
        if ( Double.isInfinite(d)){
            return false;
        }
        return true;
    }
}
