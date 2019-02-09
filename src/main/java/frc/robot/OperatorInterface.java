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

import frc.robot.commands.ExtendCommand;
import frc.robot.commands.GrabberIn;
import frc.robot.commands.GrabberOut;
import frc.robot.commands.RetractCommand;
import frc.robot.commands.ThumbsDown;
import frc.robot.commands.ThumbsStop;
import frc.robot.commands.ThumbsUp;

/**
 * Has all the code for operator controls
 * Keep in mind that for testing, this cannot be instantiated.
 * @author dcowden
 */
public class OperatorInterface {
    
    private Robot m_robot;
    private Joystick driveStick;
    
    private JoystickButton shootButton ;
    private JoystickButton retractButton;

    // Grabber Subsystem
    private JoystickButton grabInButton;
    private JoystickButton grabOutButton;

    // Thumbs Subsystem
    private JoystickButton thumbsUpButton ;
    private JoystickButton thumbsDownButton;
    
    
    //drive related buttons
    private JoystickButton turnButton;
    private JoystickButton fieldAbsoluteButton;
    private JoystickButton nudgeLeftButton;
    private JoystickButton nudgeRightButton;
      
    public OperatorInterface(Robot robot){
        this.m_robot = robot;
        createButtons();
        createCommands();
    }
    
    public DriveInput getDriveInput() {
        DriveInput di = new DriveInput(driveStick.getX(), driveStick.getY(), driveStick.getZ(), m_robot.getRobotAngle());
        return di;
    }
    
    protected void createButtons() {
        driveStick = new Joystick(RobotMap.DriveJoystick.PORT);

        shootButton = new JoystickButton(driveStick, RobotMap.DriveJoystick.Button.SHOOT);
        retractButton = new JoystickButton(driveStick, RobotMap.DriveJoystick.Button.RETRACT);

        // Grabber Subsystem
        grabInButton = new JoystickButton(driveStick, RobotMap.DriveJoystick.Button.GRAB_IN);
        grabOutButton = new JoystickButton(driveStick, RobotMap.DriveJoystick.Button.GRAB_OUT);

        // Thumbs Subsystem
        thumbsUpButton = new JoystickButton(driveStick, RobotMap.DriveJoystick.Button.THUMB_UP);
        thumbsDownButton = new JoystickButton(driveStick,RobotMap.DriveJoystick.Button.THUMB_DOWN);       
        
        fieldAbsoluteButton = new JoystickButton(driveStick,RobotMap.DriveJoystick.Button.FIELD_ABSOLUTE);  
        turnButton = new JoystickButton(driveStick,RobotMap.DriveJoystick.Button.ALLOW_TURN);
        
        nudgeLeftButton = new JoystickButton(driveStick,RobotMap.DriveJoystick.Button.NUDGE_LEFT);   
        nudgeRightButton = new JoystickButton(driveStick,RobotMap.DriveJoystick.Button.NUDGE_RIGHT); 
      
    }
    
    protected void createCommands() {
        
        shootButton.whenPressed(new ExtendCommand(m_robot.getShooterSubsystem()));
        retractButton.whenPressed(new RetractCommand(m_robot.getShooterSubsystem()));

        // Grabber Subsystem
        grabInButton.whenPressed(new GrabberIn(m_robot.getGrabberSubsystem()));
        grabOutButton.whenPressed(new GrabberOut(m_robot.getGrabberSubsystem()));
        
        // Thumbs Subsystem
        thumbsUpButton.whileHeld(new ThumbsUp(m_robot.getThumbsSubsystem()));
        thumbsUpButton.whenReleased(new ThumbsStop(m_robot.getThumbsSubsystem()));
        thumbsDownButton.whileHeld(new ThumbsDown(m_robot.getThumbsSubsystem()));
        thumbsDownButton.whenReleased(new ThumbsStop(m_robot.getThumbsSubsystem()));        
    } 
}
