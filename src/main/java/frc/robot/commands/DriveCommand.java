/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.navigation.RobotPose;
import frc.robot.oi.OperatorDriveInput;
import frc.robot.subsystems.DriveInstruction;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.oi.OperatorDriveInputSource;

/**
 * The command that runs when we are driving like normal
 * @author dcowden
 */
public class DriveCommand extends Command{

    private final DriveSubsystem drive;
    private final OperatorDriveInputSource joystick;
    private final DriveController controller;

    public DriveCommand(DriveSubsystem drive, OperatorDriveInputSource joystick, DriveController controller) {
        super(drive);
        this.drive = drive;
        this.joystick = joystick;
        this.controller = controller;
        requires(drive);
        setInterruptible(true);
        setRunWhenDisabled(false);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }    

    @Override
    protected void execute() {
        OperatorDriveInput operatorInput = joystick.getOperatorDriveInput();
        RobotPose robotPose = this.drive.getNavigation().getEstimatedPose();
        DriveInstruction di = controller.getDriveInstruction(robotPose, operatorInput);
        drive.drive(di);
    }
    
}
