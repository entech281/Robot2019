/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import frc.robot.RobotMap;
import frc.robot.commands.ArmsRelease;
import frc.robot.commands.ArmsSqueeze;
import frc.robot.commands.FlipBackward;
import frc.robot.commands.FlipForward;
import frc.robot.commands.FlipStop;
import frc.robot.commands.HatchExtend;
import frc.robot.commands.HatchRetract;
import frc.robot.commands.NudgeLeft;
import frc.robot.commands.NudgeRight;
import frc.robot.commands.ToggleFieldAbsolute;
import frc.robot.commands.TwistOff;
import frc.robot.commands.TwistOn;
import frc.robot.drive.DriveInput;
import frc.robot.drive.GetDriveInput;

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

    // Flip Subsystem
    private JoystickButton flipForwardButton;
    private JoystickButton flipBackwardButton;
    
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

        // Flip Subsystem
        flipForwardButton = new JoystickButton(driveStick, RobotMap.DriveJoystick.Button.FLIP_FORWARD);
        flipBackwardButton = new JoystickButton(driveStick,RobotMap.DriveJoystick.Button.FLIP_BACKWARD);       
         
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
        armsSqueezeButton.whenPressed(new ArmsSqueeze(this.robot.getArmsSubsystem()));
        armsReleaseButton.whenPressed(new ArmsRelease(this.robot.getArmsSubsystem()));

        // Hatch Subsystem
        hatchRetractButton.whenPressed(new HatchRetract(this.robot.getHatchSubsystem()));
        hatchExtendButton.whenPressed(new HatchExtend(this.robot.getHatchSubsystem()));
        
        // Flip Subsystem
        flipForwardButton.whileHeld(new FlipForward(this.robot.getFlipSubsystem()));
        flipForwardButton.whenReleased(new FlipStop(this.robot.getFlipSubsystem()));
        flipBackwardButton.whileHeld(new FlipBackward(this.robot.getFlipSubsystem()));
        flipBackwardButton.whenReleased(new FlipStop(this.robot.getFlipSubsystem()));

        // Nudge Commands
        nudgeRightButton.whenPressed(new NudgeRight(this.robot.getDriveSubsystem()));
        nudgeLeftButton.whenPressed(new NudgeLeft(this.robot.getDriveSubsystem()));

        // Twist Commands
        twistButton.whenPressed(new TwistOn(this.robot.getDriveSubsystem()));
        twistButton.whenReleased(new TwistOff(this.robot.getDriveSubsystem()));

        // Field Absolute Toggle
        fieldAbsoluteButton.toggleWhenPressed(new ToggleFieldAbsolute(robot));
    } 
}
