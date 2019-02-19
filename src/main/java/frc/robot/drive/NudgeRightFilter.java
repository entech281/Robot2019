/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.drive;

import edu.wpi.first.wpilibj.Timer;

/**
 * Override the drive input to a joystick right
 * @author mandrews
 */
public class NudgeRightFilter extends DriveFilter {
    private Timer m_timer = new Timer();
    static private double nudgeTime = 0.2;
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
        return new DriveInput(nudgePower, 0.0, 0.0, 0.0, input.getTargetX(), input.getTargetY());
    }
}
