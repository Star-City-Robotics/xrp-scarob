// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static final class KOperator {
    public static final int kDriverControllerPort = 0;
    public static final int kOperatorControllerPort = 1;
    public static final boolean kForceDriveGamepad = true;
  }

  public class KController {
    public static final SimControllerType kSimControllerType = SimControllerType.XBOX;
    // or: SimControllerType.DUAL_SENSE, SimControllerType.JOYSTICK, SimControllerType.KEYBOARD;

    public enum SimControllerType {
      XBOX,
      DUAL_SENSE,
      KEYBOARD
    }
  }

  /** Period of main loop in milliseconds */
  public static final double loopPeriodSecs = 0.02;

  /** The robot being used */
  public static final RobotType robotType = RobotType.XRP;

  /** Whether to load a log file and run simulation replay */
  public static final boolean isReplay = false;

  /** Whether to publish and allow editing of tunable numbers */
  public static final boolean tuningMode = true;

  public enum RobotType {
    XRP,
    SIMBOT,
    REALBOT
  }

  public static final class KDrivetrain {
    // motor device numbers. we use L/R, but 3/4 can also be added
    public static final int kMotorLDeviceNum = 0;
    public static final int kMotorRDeviceNum = 1;
    public static final int kMotor3DeviceNum = 2;
    public static final int kMotor4DeviceNum = 3;

    // motor encoder device numbers. "channelA" and "channelB"
    //
    // DIOLeftInputID = (deviceNum*2) + 4; DIORightInputID = (deviceNum*2) + 5;
    public static final int kEncoderLDeviceNum = 0;
    public static final int kEncoderRDeviceNum = 1;

    public static final double kGearRatio =
        (30.0 / 14.0) * (28.0 / 16.0) * (36.0 / 9.0) * (26.0 / 8.0); // 48.75:1

    public static final double kCountsPerMotorShaftRev = 12.0;
    public static final double kCountsPerRevolution = kCountsPerMotorShaftRev * kGearRatio; // 585.0

    public static final double kWheelDiameterInch = 2.3622; // 60 mm

    public static final double kDistancePerPulse =
        Math.PI * kWheelDiameterInch / kCountsPerRevolution;
  }
}
