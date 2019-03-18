/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.LoosenFlipRope;
import frc.robot.commands.ArmsDeploy;
import frc.robot.subsystems.ArmsSubsystem;
import frc.robot.subsystems.FlipSubsystem;;

public class LoosenAndDeploy extends CommandGroup {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);



    public LoosenAndDeploy(FlipSubsystem flip, ArmsSubsystem arms) {
      addSequential(new LoosenFlipRope(flip));
      addSequential(new ArmsDeploy(arms));
  }
}
