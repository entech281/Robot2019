/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frc.robot.subsystems;

import java.lang.Math;

/**
 * SImply drops the z component
 * @author dcowden
 */
public class DeadbandDriveFilter extends DriveFilter {

    @Override
    public DriveCommand doFilter(DriveCommand input) {
        double x = input.getX();
        double y = input.getY();
        double z = input.getZ();

        if (Math.abs(x) < 0.05) {
            x = 0.0;
        }
        if (Math.abs(y) < 0.05) {
            y = 0.0;
        }
        if (Math.abs(z) < 0.05) {
            z = 0.0;
        }
        return new DriveCommand(x, y, z, input.getFieldAngle());          
    }
    
}
