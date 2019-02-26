/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.drive;


/**
 * Add your docs here.
 */
public class HoldYawFilter extends DriveFilter {

  public static final double ANGLE_THRESHOLD_DEGREES=5;
  private BangBangControl bangbang = new BangBangControl(ANGLE_THRESHOLD_DEGREES,0.5);
  double desiredAngle = 0.0;

  public HoldYawFilter() {
    super(false);
  }

  public void setDesiredYaw(double angle) {
       desiredAngle = angle;
  }

  @Override
  public DriveInput doFilter(DriveInput input) {
    double twist = bangbang.control(desiredAngle,input.getFieldAngle());
    
    return new DriveInput(input.getX(), 
            input.getY(), 
            twist, 
            input.getFieldAngle(), 
            input.getTargetDistance(), 
            input.getTargetLateral());
  }
}
