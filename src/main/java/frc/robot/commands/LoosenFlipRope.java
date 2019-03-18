/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.FlipSubsystem;;

public class LoosenFlipRope extends Command {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    private FlipSubsystem flip;
    private static double LOOSEN_SECONDS = 3;
    
    public LoosenFlipRope(FlipSubsystem flip) {
      this.flip=flip;
      requires(flip);
      setTimeout(LOOSEN_SECONDS);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    flip.backward();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    flip.backward();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return isTimedOut();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    flip.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
