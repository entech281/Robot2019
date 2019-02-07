package frc.robot.navigation;

/**
 * The situation of the robot. 
 * Could represent all of its parts relative to the others,
 * position on the field ( if that's needed, etc)
 * 
 * Each year, the pose will be different
 * @author dcowden
 */
public class RobotPose {
    public static final double UNKNOWN = 999999999.9;

    public double getDistanceToTarget() {
        return distanceToTarget;
    }

    public void setDistanceToTarget(double distanceToTarget) {
        this.distanceToTarget = distanceToTarget;
    }

    public double getAngleFromTarget() {
        return angleFromTarget;
    }

    public void setAngleFromTarget(double angleFromTarget) {
        this.angleFromTarget = angleFromTarget;
    }

    public double getLateralOffsetFromTarget() {
        return lateralOffsetFromTarget;
    }

    public void setLateralOffsetFromTarget(double lateralOffsetFromTarget) {
        this.lateralOffsetFromTarget = lateralOffsetFromTarget;
    }

    public double getRobotAngleOnField() {
        return robotAngleOnField;
    }

    public void setRobotAngleOnField(double robotAngleOnField) {
        this.robotAngleOnField = robotAngleOnField;
    }
    

    private double distanceToTarget = UNKNOWN;
    private double angleFromTarget = UNKNOWN;
    private double lateralOffsetFromTarget = UNKNOWN;
    private double robotAngleOnField = UNKNOWN;

}