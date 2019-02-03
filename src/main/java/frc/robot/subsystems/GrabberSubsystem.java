/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import frc.robot.navigation.NavigationManager;

/**
 * Add your docs here.
 */
public class GrabberSubsystem extends BaseSubsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private DoubleSolenoid solenoid;

    public GrabberSubsystem(NavigationManager navigationManager) {
        super(navigationManager);
    }

  
  @Override
  public void initialize(){
    solenoid= new DoubleSolenoid(10, 2, 3);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
  
  public void extend(){
    solenoid.set(DoubleSolenoid.Value.kForward);
  }

  public void retract(){
    solenoid.set(DoubleSolenoid.Value.kReverse);
  }
}
