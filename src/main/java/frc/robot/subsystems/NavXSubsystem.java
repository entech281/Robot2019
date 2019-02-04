/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.navigation.NavigationManager;

/**
 *
 * @author dcowden
 */
public class NavXSubsystem extends BaseSubsystem{

    private final AHRS navX = new AHRS(SPI.Port.kMXP);
    private NavigationManager navigation;
    
    public NavXSubsystem(NavigationManager navigationManager) {
        this.navigation = navigationManager;
    }

    @Override
    public void initialize() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void periodic() {
        double navxAngle = navX.getAngle();
        navigation.acceptNavXPoseUpdate(navxAngle);
        SmartDashboard.putNumber("Gyro Angle", navxAngle);
    }
    
}
