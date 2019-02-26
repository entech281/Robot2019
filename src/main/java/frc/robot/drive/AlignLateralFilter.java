package frc.robot.drive;

import frc.logging.Logger;
import frc.logging.Logging;
import frc.pid.BangBangController;
import frc.pid.Controller;

/**
 * Add your docs here.
 */
public class AlignLateralFilter extends DriveFilter  {

  public static final double ALIGN_THRESHOLD_INCHES=1.5;
  private Controller bangbang = new BangBangController(ALIGN_THRESHOLD_INCHES,0.5);
  
  public AlignLateralFilter() {
    super(false);
  }

  @Override
  public DriveInput doFilter(DriveInput input) {
           
    double x_js = bangbang.getOutput(input.getTargetLateral(),0.0);

    return new DriveInput(x_js, input.getY(), 
            input.getZ(), 
            0.0, 
            input.getTargetDistance(), 
            input.getTargetLateral());
  }
}