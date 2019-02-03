/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frc.robot.oi;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ExtendCommand;
import frc.robot.commands.GrabberIn;
import frc.robot.commands.GrabberOut;
import frc.robot.commands.RetractCommand;
import frc.robot.commands.ThumbsDown;
import frc.robot.commands.ThumbsStop;
import frc.robot.commands.ThumbsUp;
import frc.robot.oi.OperatorDriveCommand;
import frc.robot.subsystems.GrabberSubsystem;
import frc.robot.subsystems.SubsystemManager;
import frc.robot.subsystems.ThumbsSubsystem;

/**
 * Has all the code for operator controls
 * @author dcowden
 */
public class OperatorInterface {

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
    private JoystickButton thumbsDownButton ;
    
    private JoystickButton fieldAbsoluteButton;
    
    private JoystickButton turnButton;
      
    public OperatorInterface(SubsystemManager subsystems){
        this.subsystems = subsystems;
        
    }
    
    public OperatorDriveCommand getDriveCommand(){
        boolean isFieldAbsolute = fieldAbsoluteButton.get();
        boolean allowTurn = turnButton.get();
        double z = 0.0;
        if ( allowTurn ){
            z = driveStick.getZ();
        }
        
        OperatorDriveCommand dc = new OperatorDriveCommand(
                driveStick.getX(), 
                driveStick.getY(), 
                z, 
                isFieldAbsolute);
        SmartDashboard.putString("OI Stick", dc + "");
        return dc;
    }
    
    public void startUp(){
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
    }
    
    protected void createCommands(){
        
        shootButton.whenPressed(new ExtendCommand(subsystems.getShooter()));
        retractButton.whenPressed(new RetractCommand(subsystems.getShooter()));

        // Grabber Subsystem
        GrabberSubsystem grabber = subsystems.getGrabber();
        grabInButton.whenPressed(new GrabberIn(grabber));
        grabOutButton.whenPressed(new GrabberOut(grabber));

        // Thumbs Subsystem
        ThumbsSubsystem thumbs = subsystems.getThumbs();
        thumbsUpButton.whileHeld(new ThumbsUp(thumbs));
        thumbsUpButton.whenReleased(new ThumbsStop(thumbs));
        thumbsDownButton.whileHeld(new ThumbsDown(thumbs));
        thumbsDownButton.whenReleased(new ThumbsStop(thumbs));        
    } 
  

    
}
