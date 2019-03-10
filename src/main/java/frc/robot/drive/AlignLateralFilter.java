package frc.robot.drive;

import frc.pid.Controller;
import frc.pid.CappedLinearControl;;

/**
 * Add your docs here.
 */
public class AlignLateralFilter extends DriveFilter  {

  public static final double ALIGN_THRESHOLD_INCHES=0.1;
  private Controller cappedLinear = new CappedLinearControl(
          ALIGN_THRESHOLD_INCHES,10, 0.2, 0.8);
  
  public AlignLateralFilter() {
    super(false);
  }

  @Override
  public DriveInput doFilter(DriveInput input) {
           
    double x_js = cappedLinear.getOutput(input.getTargetLateral(),0.0);

    return new DriveInput(x_js, input.getY(), 
            input.getZ(), 
            0.0, 
            input.getTargetDistance(), 
            input.getTargetLateral());
  }
}