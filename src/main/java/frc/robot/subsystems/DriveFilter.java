package frc.robot.subsystems;

/**
 *
 * @author dcowden
 */
public interface DriveFilter {
    void setEnable(boolean enabled);
    boolean getEnabled();
    DriveCommand filter(DriveCommand input, RobotPose state);
}
