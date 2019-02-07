package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

public abstract class BaseSubsystem extends Subsystem{
    @Override
    protected void initDefaultCommand(){

    }

    public abstract void initialize();
}