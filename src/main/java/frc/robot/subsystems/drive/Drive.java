// Copyright (c) 2024-2025 FRC 6328
// http://github.com/Mechanical-Advantage
//
// Use of this source code is governed by an MIT-style
// license that can be found in the LICENSE file at
// the root directory of this project.

package frc.robot.subsystems.drive;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.littletonrobotics.junction.AutoLogOutput;
import org.littletonrobotics.junction.Logger;

public class Drive extends SubsystemBase {

  private final DrivetrainIO drivetrainIO;
  private final DrivetrainIOInputsAutoLogged inputs = new DrivetrainIOInputsAutoLogged();

  private final GyroIO gyroIO;
  private final GyroIOInputsAutoLogged gyroInputs = new GyroIOInputsAutoLogged();

  /**
   * IMPORTANT: We never use HID objects like this in a subsystem class. This code is provided as a
   * starting point, and we will discuss how to improve it very soon.
   */
  private final GenericHID keyboard = new GenericHID(0);

  /** Creates a new Drive. */
  public Drive(GyroIO gyroIO, DrivetrainIO drivetrainIO) {
    this.drivetrainIO = drivetrainIO;
    this.gyroIO = gyroIO;
  }

  @Override
  public void periodic() {
    io.updateInputs(inputs);
    Logger.processInputs("Drive", inputs);
  }

  /** Run open loop based on percentages. */
  public void drivePercent(double left, double right) {
    drivetrainIO.setVoltage(left * 6.0, right * 6.0);
  }

  /** Run open loop based on voltages. */
  public void driveVolts(double leftVolts, double rightVolts) {
    drivetrainIO.setVoltage(leftVolts, rightVolts);
  }

  /** Stops the drive. */
  public void stop() {
    drivetrainIO.setVoltage(0.0, 0.0);
  }

  @Override
  public void periodic() {
    gyroIO.updateInputs(gyroInputs);
    Logger.processInputs("Drive/Gyro", gyroInputs);
    drivetrainIO.updateInputs(inputs);
    Logger.processInputs("Drive", inputs);
  }
}
