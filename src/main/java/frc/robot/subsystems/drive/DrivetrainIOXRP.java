// Copyright (c) 2024-2025 FRC 6328
// http://github.com/Mechanical-Advantage
//
// Use of this source code is governed by an MIT-style
// license that can be found in the LICENSE file at
// the root directory of this project.

package frc.robot.subsystems.drive;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.xrp.XRPMotor;

public class DrivetrainIOXRP implements DrivetrainIO {
  private final XRPMotor leftMotor = new XRPMotor(0);
  private final XRPMotor rightMotor = new XRPMotor(1);

  public DrivetrainIOXRP() {
    rightMotor.setInverted(true);
  }

  @Override
  public void updateInputs(DrivetrainIOInputs inputs) {}

  @Override
  public void setVoltage(double leftVolts, double rightVolts) {
    leftVolts = MathUtil.clamp(leftVolts, -6.0, 6.0);
    rightVolts = MathUtil.clamp(rightVolts, -6.0, 6.0);
    leftMotor.set(leftVolts / 6.0);
    rightMotor.set(rightVolts / 6.0);
  }
}
