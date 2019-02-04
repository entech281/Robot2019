/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frc.robot.subsystems;

/**
 * SImply drops the z component
 * @author dcowden
 */
public class DisableFieldAbsDriveFilter extends DriveFilter {

    @Override
    public DriveCommand doFilter(DriveCommand input) {
        return new DriveCommand(input.getX(), input.getY(), input.getZ(), 0.0);          
    }
    
}
