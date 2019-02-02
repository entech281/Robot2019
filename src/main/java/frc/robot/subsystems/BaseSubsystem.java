/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public abstract class BaseSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  // Creates a list of all the subsystems extending BaseSubsystem
  private static List <BaseSubsystem> subsystems_to_initialize_list = new ArrayList <BaseSubsystem>();

  public BaseSubsystem() {
    SmartDashboard.putData(this);
    subsystems_to_initialize_list.add(this);
    // not "append" subsystems_to_initialize_list.append(this);
  }

  public static void initializeList() {
    // Loop through all the Subsystems and initialize them
    for ( BaseSubsystem subsystem_initializer: subsystems_to_initialize_list) {
      subsystem_initializer.initialize();
    }
  }

  public abstract void initialize();

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
