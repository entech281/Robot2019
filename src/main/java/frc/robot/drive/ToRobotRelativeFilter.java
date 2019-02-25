/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.drive;

/**
 * Convert the DriveInput from FieldAbsolute to RobotRelative input while keeping the same driving direction
 * @author mandrews
 */
public class ToRobotRelativeFilter extends DriveFilter {
    public ToRobotRelativeFilter() {
        super(false);
    }

    @Override
    protected void onEnable() {
    }
    
    @Override
    public DriveInput doFilter(DriveInput input) {
        double ang_rad = Math.PI * input.getFieldAngle() / 180.0;
        double ca = Math.cos(ang_rad);
        double sa = Math.sin(ang_rad);
        double di_x = ca * input.getX() - sa * input.getY();
        double di_y = ca * input.getY() + sa * input.getX();
        return new DriveInput(di_x, di_y, input.getZ(), 0.0, input.getTargetX(), input.getTargetY());
    }
}
