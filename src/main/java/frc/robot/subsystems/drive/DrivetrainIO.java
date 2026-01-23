// Copyright (c) 2024-2025 FRC 6328
// http://github.com/Mechanical-Advantage
//
// Use of this source code is governed by an MIT-style
// license that can be found in the LICENSE file at
// the root directory of this project.

package frc.robot.subsystems.drive;

import org.littletonrobotics.junction.AutoLog;

public interface DrivetrainIO {
  @AutoLog
  public static class DrivetrainIOInputs {
    public int leftEncoderCount = 0;
    public int rightEncoderCount = 0;
    public double leftDistance = 0.0;
    public double rightDistance = 0.0;
    public double leftVelocity = 0.0;
    public double rightVelocity = 0.0;
    public double leftVolts = 0.0;
    public double rightVolts = 0.0;
  }

  /** Updates the set of loggable inputs. */
  public default void updateInputs(DrivetrainIOInputs inputs) {}

  /** Run open loop at the specified voltage. */
  public default void setVoltage(double leftVolts, double rightVolts) {}

  public default void resetEncoders() {}

  public default void arcadeDrive(double xaxisSpeed, double zaxisRotate) {}
}
