/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import frc.robot.RobotMap;
import frc.robot.drive.DriveInput;
import frc.robot.drive.GetDriveInput;
import frc.robot.commands.Extend;
import frc.robot.commands.NudgeLeft;
import frc.robot.commands.NudgeRight;
import frc.robot.commands.Release;
import frc.robot.commands.Retract;
import frc.robot.commands.Squeeze;
import frc.robot.commands.ThumbsDown;
import frc.robot.commands.ThumbsStop;
import frc.robot.commands.ThumbsUp;
import frc.robot.commands.ToggleFieldAbsoluteCommand;
import frc.robot.commands.TwistOff;
import frc.robot.commands.TwistOn;

/**
 * Has all the code for operator controls
 * Keep in mind that for testing, this cannot be instantiated.
 * @author dcowden
 */
public class OperatorInterface implements GetDriveInput {
    
    private Robot robot;
    private Joystick driveStick;
    
    // Arms Subsystem
    private JoystickButton armsSqueezeButton ;
    private JoystickButton armsReleaseButton;

    // Hatch Subsystem
    private JoystickButton hatchExtendButton;
    private JoystickButton hatchRetractButton;

    // Thumbs Subsystem
    private JoystickButton thumbsUpButton;
    private JoystickButton thumbsDownButton;
    
    // Nudge Commands
    private JoystickButton nudgeLeftButton;
    private JoystickButton nudgeRightButton;

    // Twist Commands
    private JoystickButton twistButton;

    //drive related buttons
    //private JoystickButton turnButton;

    // Field Absolute Toggle
    private JoystickButton fieldAbsoluteButton;
      
    public OperatorInterface(Robot robot){
        this.robot = robot;
        createButtons();
        createCommands();
    }
    
    @Override
    public DriveInput getDriveInput() {
        return new DriveInput(driveStick.getX(), driveStick.getY(), driveStick.getZ());
    }
    
    protected void createButtons() {
        driveStick = new Joystick(RobotMap.DriveJoystick.PORT);

        // Arms Subsystem
        armsSqueezeButton = new JoystickButton(driveStick, RobotMap.DriveJoystick.Button.ARMS_SQUEEZE);
        armsReleaseButton = new JoystickButton(driveStick, RobotMap.DriveJoystick.Button.ARMS_RELEASE);

        // Hatch Subsystem
        hatchRetractButton = new JoystickButton(driveStick, RobotMap.DriveJoystick.Button.HATCH_RETRACT);
        hatchExtendButton = new JoystickButton(driveStick, RobotMap.DriveJoystick.Button.HATCH_EXTEND);

        // Thumbs Subsystem
        thumbsUpButton = new JoystickButton(driveStick, RobotMap.DriveJoystick.Button.THUMB_UP);
        thumbsDownButton = new JoystickButton(driveStick,RobotMap.DriveJoystick.Button.THUMB_DOWN);       
         
        // Nudge Commands
        nudgeLeftButton = new JoystickButton(driveStick, RobotMap.DriveJoystick.Button.NUDGE_LEFT);   
        nudgeRightButton = new JoystickButton(driveStick, RobotMap.DriveJoystick.Button.NUDGE_RIGHT);

        // Twist Commands
        twistButton = new JoystickButton(driveStick, RobotMap.DriveJoystick.Button.ALLOW_TWIST);

        //turnButton = new JoystickButton(driveStick, RobotMap.DriveJoystick.Button.ALLOW_TURN);

        // Field Absolute Toggle
        fieldAbsoluteButton = new JoystickButton(driveStick, RobotMap.DriveJoystick.Button.FIELD_ABSOLUTE);  
    }
    
    protected void createCommands() {
        
        // Arms Subsystem
        armsSqueezeButton.whenPressed(new Squeeze(this.robot.getArmsSubsystem()));
        armsReleaseButton.whenPressed(new Release(this.robot.getArmsSubsystem()));

        // Hatch Subsystem
        hatchRetractButton.whenPressed(new Retract(this.robot.getHatchSubsystem()));
        hatchExtendButton.whenPressed(new Extend(this.robot.getHatchSubsystem()));
        
        // Thumbs Subsystem
        thumbsUpButton.whileHeld(new ThumbsUp(this.robot.getThumbsSubsystem()));
        thumbsUpButton.whenReleased(new ThumbsStop(this.robot.getThumbsSubsystem()));
        thumbsDownButton.whileHeld(new ThumbsDown(this.robot.getThumbsSubsystem()));
        thumbsDownButton.whenReleased(new ThumbsStop(this.robot.getThumbsSubsystem()));

        // Nudge Commands
        nudgeRightButton.whenPressed(new NudgeRight(this.robot.getDriveSubsystem()));
        nudgeLeftButton.whenPressed(new NudgeLeft(this.robot.getDriveSubsystem()));

        // Twist Commands
        twistButton.whenPressed(new TwistOn(this.robot.getDriveSubsystem()));
        twistButton.whenReleased(new TwistOff(this.robot.getDriveSubsystem()));

        // Field Absolute Toggle
        fieldAbsoluteButton.toggleWhenPressed(new ToggleFieldAbsoluteCommand(robot));
    } 
}
