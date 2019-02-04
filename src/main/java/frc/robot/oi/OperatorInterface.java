/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frc.robot.oi;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.Initable;
import frc.robot.commands.DriveNudgeCommand;
import frc.robot.commands.ExtendCommand;
import frc.robot.commands.GrabberIn;
import frc.robot.commands.GrabberOut;
import frc.robot.commands.RetractCommand;
import frc.robot.commands.ThumbsDown;
import frc.robot.commands.ThumbsStop;
import frc.robot.commands.ThumbsUp;
import frc.robot.subsystems.GrabberSubsystem;
import frc.robot.subsystems.SubsystemManager;
import frc.robot.subsystems.ThumbsSubsystem;

/**
 * Has all the code for operator controls
 * Keep in mind that for testing, this cannot be instantiated.
 * @author dcowden
 */
public class OperatorInterface implements OperatorDriveInputSource,Initable{

    public interface Joystick_0{
        public int ID = 0;
        public interface Button {
            public int SHOOT = 11;
            public int RETRACT = 12;
            public int GRAB_IN = 8;
            public int GRAB_OUT = 10;
            public int THUMB_UP = 7;
            public int THUMB_DOWN = 9;
            public int FIELD_ABSOLUTE = 6;
            public int ALLOW_TURN = 1;
            public int NUDGE_LEFT = 2;
            public int NUDGE_RIGHT = 3;
        }
    }    
    
    private SubsystemManager subsystems;
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
      
    public OperatorInterface(SubsystemManager subsystems){
        this.subsystems = subsystems;
        
    }
    
    @Override
    public OperatorDriveInput getOperatorDriveInput() {
        OperatorDriveInput odi = new OperatorDriveInput(
                driveStick.getX(), driveStick.getY(), driveStick.getZ());
        
        odi.setCanTwist(turnButton.get());
        odi.setFieldAbsolute(fieldAbsoluteButton.get());
        return odi;
    }
    
    @Override
    public void initialize(){
        createButtons();
        createCommands();

    }
    
    protected void createButtons(){
        driveStick = new Joystick(Joystick_0.ID);

        shootButton = new JoystickButton(driveStick, Joystick_0.Button.SHOOT);
        retractButton = new JoystickButton(driveStick, Joystick_0.Button.RETRACT);

        // Grabber Subsystem
        grabInButton = new JoystickButton(driveStick, Joystick_0.Button.GRAB_IN);
        grabOutButton = new JoystickButton(driveStick, Joystick_0.Button.GRAB_OUT);

        // Thumbs Subsystem
        thumbsUpButton = new JoystickButton(driveStick, Joystick_0.Button.THUMB_UP);
        thumbsDownButton = new JoystickButton(driveStick,Joystick_0.Button.THUMB_DOWN);       
        
        fieldAbsoluteButton = new JoystickButton(driveStick,Joystick_0.Button.FIELD_ABSOLUTE);  
        turnButton = new JoystickButton(driveStick,Joystick_0.Button.ALLOW_TURN);
        
        nudgeLeftButton = new JoystickButton(driveStick,Joystick_0.Button.NUDGE_LEFT);   
        nudgeRightButton = new JoystickButton(driveStick,Joystick_0.Button.NUDGE_RIGHT); 
      
    }
    
    protected void createCommands(){
        
        int LEFT_NUDGE_AMOUNT = -10;
        int RIGHT_NUDGE_AMOUNT = 10;
        shootButton.whenPressed(new ExtendCommand(subsystems.getShooter()));
        retractButton.whenPressed(new RetractCommand(subsystems.getShooter()));

        // Grabber Subsystem
        GrabberSubsystem grabber = subsystems.getGrabber();
        grabInButton.whenPressed(new GrabberIn(grabber));
        grabOutButton.whenPressed(new GrabberOut(grabber));
        
        nudgeLeftButton.whenPressed( new DriveNudgeCommand(subsystems.getDrive(), LEFT_NUDGE_AMOUNT));
        nudgeRightButton.whenPressed( new DriveNudgeCommand(subsystems.getDrive(),RIGHT_NUDGE_AMOUNT));

        // Thumbs Subsystem
        ThumbsSubsystem thumbs = subsystems.getThumbs();
        thumbsUpButton.whileHeld(new ThumbsUp(thumbs));
        thumbsUpButton.whenReleased(new ThumbsStop(thumbs));
        thumbsDownButton.whileHeld(new ThumbsDown(thumbs));
        thumbsDownButton.whenReleased(new ThumbsStop(thumbs));        
    } 
  

    
}
