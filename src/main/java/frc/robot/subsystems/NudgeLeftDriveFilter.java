/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Timer;

/**
 * SImply drops the z component
 * @author dcowden
 */
public class NudgeLeftDriveFilter extends DriveFilter {
    static private double nudgeTime = 0.1;
    static private double nudgePower = 0.5;
    private Timer m_timer = new Timer();

    @Override
    public void enable() {
        if (isEnabled()) {
            return;
        }
        m_timer.stop();
        m_timer.reset();
        m_timer.start();
        super.enable();
    }
    
    @Override
    public DriveCommand doFilter(DriveCommand input) {
        if (m_timer.get() > nudgeTime) {
            disable();
            return input;
        }
        return new DriveCommand(0.0, nudgePower, 0.0, 0.0);
    }
    
}
