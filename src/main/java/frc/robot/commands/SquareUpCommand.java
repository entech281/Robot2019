/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.NavXSubsystem;

/**
 *
 * @author dcowden
 */
public class SquareUpCommand extends Command{

    private DriveSubsystem drive;
    private NavXSubsystem navx;
    public SquareUpCommand(NavXSubsystem navx, DriveSubsystem drive){
        this.drive = drive;
        this.navx = navx;
    }

    @Override
    protected void initialize() {
        double angle = navx.findNearestQuadrant();
        drive.enableHoldYaw(angle);        
    }
    
    @Override
    protected boolean isFinished() {
        return true;
    }
    
}
