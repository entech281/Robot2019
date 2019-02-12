/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frc.robot.drive;

import edu.wpi.first.wpilibj.Timer;

/**
 * Override the drive input to a joystick right
 * @author mandrews
 */
public class NudgeRightFilter extends DriveFilter {
    private Timer m_timer = new Timer();
    static private double nudgeTime = 0.1;
    static private double nudgePower = 0.5;

    public NudgeRightFilter() {
        super(false);
    }

    @Override
    protected void onEnable() {
        m_timer.stop();
        m_timer.reset();
        m_timer.start();
    }
    
    @Override
    public DriveInput doFilter(DriveInput input) {
        if (m_timer.get() > nudgeTime) {
            disable();
            return input;
        }
        return new DriveInput(0.0, -nudgePower, 0.0, 0.0, 0.0, 0.0);
    }
    
}
