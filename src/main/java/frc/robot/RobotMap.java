/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

  public interface CAN {
    public static final int FRONT_LEFT_MOTOR = 14;
    public static final int FRONT_RIGHT_MOTOR = 2;
    public static final int REAR_LEFT_MOTOR = 8;
    public static final int REAR_RIGHT_MOTOR = 16;
    public static final int CLIMB = 7;
    public static final int PCM_ID = 10;
  }

  public interface DriveJoystick {
    public static final int PORT = 0;

    public interface Button {
      public static final int ALLOW_TWIST = 1;
      public static final int NUDGE_LEFT = 3;
      public static final int NUDGE_RIGHT = 4;
      public static final int FIELD_ABSOLUTE = 6;
      public static final int ZERO_YAW = 7;
    }
  }
 
  public interface OperatorPanel {
    public static final int PORT = 1;
    public interface Button {
      public static final int HOLD_LATERAL = 1;
      public static final int HATCH_RETRACT = 2;
      public static final int HATCH_EXTEND = 3;
      public static final int ARMS_DEPLOY = 4;
      public static final int ARMS_SQUEEZE = 5;
      public static final int ARMS_RELEASE = 6;
      public static final int FLIP_FORWARD = 7;
      public static final int FLIP_BACKWARD = 8;
    }
  }
}
