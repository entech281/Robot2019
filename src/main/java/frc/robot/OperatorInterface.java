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

import frc.robot.commands.ExtendCommand;
import frc.robot.commands.GrabberIn;
import frc.robot.commands.GrabberOut;
import frc.robot.commands.NudgeLeft;
import frc.robot.commands.NudgeRight;
import frc.robot.commands.RetractCommand;
import frc.robot.commands.ThumbsDown;
import frc.robot.commands.ThumbsStop;
import frc.robot.commands.ThumbsUp;
import frc.robot.commands.TwistOff;
import frc.robot.commands.TwistOn;
import frc.robot.commands.ZeroYaw;

/**
 * Has all the code for operator controls
 * Keep in mind that for testing, this cannot be instantiated.
 * @author dcowden
 */
public class OperatorInterface implements GetDriveInput {
    
    private Robot robot;
    private Joystick driveStick;
    
    private JoystickButton shootButton ;
    private JoystickButton retractButton;

    // Grabber Subsystem
    private JoystickButton grabInButton;
    private JoystickButton grabOutButton;

    // Thumbs Subsystem
    private JoystickButton thumbsUpButton;
    private JoystickButton thumbsDownButton;
    
    // Nudge Commands
    private JoystickButton nudgeLeftButton;
    private JoystickButton nudgeRightButton;

    // Twist Commands
    private JoystickButton twistButton;

    private JoystickButton zeroYawButton;
    //drive related buttons
    //private JoystickButton turnButton;
    //private JoystickButton fieldAbsoluteButton;
      
    public OperatorInterface(Robot robot){
        this.robot = robot;
        createButtons();
        createCommands();
    }
    
    @Override
    public DriveInput getDriveInput() {
        return new DriveInput(-driveStick.getX(), driveStick.getY(), -driveStick.getZ());
    }
    
    protected void createButtons() {
        driveStick = new Joystick(RobotMap.DriveJoystick.PORT);

        // Shooter Subsystem
        shootButton = new JoystickButton(driveStick, RobotMap.DriveJoystick.Button.SHOOT);
        retractButton = new JoystickButton(driveStick, RobotMap.DriveJoystick.Button.RETRACT);

        // Grabber Subsystem
        grabInButton = new JoystickButton(driveStick, RobotMap.DriveJoystick.Button.GRAB_IN);
        grabOutButton = new JoystickButton(driveStick, RobotMap.DriveJoystick.Button.GRAB_OUT);

        // Thumbs Subsystem
        thumbsUpButton = new JoystickButton(driveStick, RobotMap.DriveJoystick.Button.THUMB_UP);
        thumbsDownButton = new JoystickButton(driveStick,RobotMap.DriveJoystick.Button.THUMB_DOWN);       
         
        // Nudge Commands
        nudgeLeftButton = new JoystickButton(driveStick, RobotMap.DriveJoystick.Button.NUDGE_LEFT);   
        nudgeRightButton = new JoystickButton(driveStick, RobotMap.DriveJoystick.Button.NUDGE_RIGHT);

        // Twist Commands
        twistButton = new JoystickButton(driveStick, RobotMap.DriveJoystick.Button.ALLOW_TWIST);

        zeroYawButton = new JoystickButton(driveStick, RobotMap.DriveJoystick.Button.ZERO_YAW);
        //fieldAbsoluteButton = new JoystickButton(driveStick, RobotMap.DriveJoystick.Button.FIELD_ABSOLUTE);  
        //turnButton = new JoystickButton(driveStick, RobotMap.DriveJoystick.Button.ALLOW_TURN);
    }
    
    protected void createCommands() {
        
        // Shooter Subsystem
        shootButton.whenPressed(new ExtendCommand(this.robot.getShooterSubsystem()));
        retractButton.whenPressed(new RetractCommand(this.robot.getShooterSubsystem()));

        // Grabber Subsystem
        grabInButton.whenPressed(new GrabberIn(this.robot.getGrabberSubsystem()));
        grabOutButton.whenPressed(new GrabberOut(this.robot.getGrabberSubsystem()));
        
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

        zeroYawButton.whenPressed(new ZeroYaw(this.robot.getNavXSubsystem()));
    } 
}
