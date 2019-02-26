package frc.robot.drive;

/**
 * Add your docs here.
 */
public class AlignLateralFilter extends DriveFilter  {

  public static final double ALIGN_THRESHOLD_INCHES=1.5;
  private BangBangControl bangbang = new BangBangControl(ALIGN_THRESHOLD_INCHES,0.5);
  
  public AlignLateralFilter() {
    super(false);
  }

  @Override
  public void onEnable() {

  }

  @Override
  protected void onDisable() {
  }

  @Override
  public DriveInput doFilter(DriveInput input) {
    double x_js = bangbang.control(0.0,input.getTargetLateral());


    return new DriveInput(x_js, input.getY(), input.getZ(), 0.0, input.getTargetDistance(), input.getTargetLateral());
  }
}