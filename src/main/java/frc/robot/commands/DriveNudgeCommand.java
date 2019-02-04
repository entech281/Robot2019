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

/**
 * The command that runs when we are driving like normal
 * @author dcowden
 */
public class DriveNudgeCommand extends Command{

    public static final double TOLERANCE=0.5;
    public static final double TIMEOUT=2.0;
    public static final double GAIN=1.0;
    private final DriveSubsystem drive;
    private final NudgeController nudgeController;
    public DriveNudgeCommand(DriveSubsystem drive, double sidewaysDistance) {
        super(TIMEOUT,drive);
        this.drive = drive;
        requires(drive);
        setInterruptible(true);
        setRunWhenDisabled(false);
        
        double currentValue = getCurrentLateralOffset();
        double desiredValue = currentValue + sidewaysDistance;
        nudgeController = new NudgeController(currentValue, desiredValue, GAIN);
    }

    private double getCurrentLateralOffset(){
        RobotPose pose = drive.getNavigation().getEstimatedPose();
        return pose.getLateralOffsetFromTarget();
    }
  

    @Override
    protected boolean isFinished() {
        //TODO: have to figure out when finished somehowS
        return nudgeController.isFinished();
    }    

    @Override
    protected void execute() {        

        double output = nudgeController.computeOutput(getCurrentLateralOffset());
        DriveInstruction di = new DriveInstruction(output,0,0,0);

        drive.drive(di);
    }
    
}
