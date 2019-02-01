package frc.robot.subsystems;

/**
 * Generates output that aligns the robot 
 * left-to-right based on sensor input
 * 
 * input: navx angle
 * sensor feedback ( offset from center)
 * joystick inputs
 * output: x and y control signals for drive
 * @author dcowden
 */
public class AlignController implements DriveFilter{
    
    public AlignController(){
        
    }


    @Override
    public void setEnable(boolean enabled) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean getEnabled() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DriveCommand filter(DriveCommand input, RobotPose state) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
