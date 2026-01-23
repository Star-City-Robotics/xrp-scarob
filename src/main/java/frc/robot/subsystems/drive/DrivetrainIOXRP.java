// Copyright (c) 2024-2025 FRC 6328
// http://github.com/Mechanical-Advantage
//
// Use of this source code is governed by an MIT-style
// license that can be found in the LICENSE file at
// the root directory of this project.

package frc.robot.subsystems.drive;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.xrp.XRPMotor;
import frc.robot.Constants;
import frc.robot.util.XRPEncoder;

public class DrivetrainIOXRP implements DrivetrainIO {
  private final XRPMotor leftMotor = new XRPMotor(Constants.KDrivetrain.kMotorLDeviceNum);
  private final XRPMotor rightMotor = new XRPMotor(Constants.KDrivetrain.kMotorRDeviceNum);
  private final XRPEncoder leftEncoder = new XRPEncoder(Constants.KDrivetrain.kEncoderLDeviceNum);
  private final XRPEncoder rightEncoder = new XRPEncoder(Constants.KDrivetrain.kEncoderRDeviceNum);

  private boolean closedLoop = false;
  private double leftVolts = 0.0;
  private double rightVolts = 0.0;

  // Set up the differential drive controller
  private final DifferentialDrive diffDrive =
      new DifferentialDrive(leftMotor::set, rightMotor::set);

  public DrivetrainIOXRP() {
    closedLoop = false;

    SendableRegistry.addChild(diffDrive, leftMotor);
    SendableRegistry.addChild(diffDrive, rightMotor);

    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    rightMotor.setInverted(true);

    // Use inches as unit for encoder distances
    leftEncoder.setDistancePerPulse(Constants.KDrivetrain.kDistancePerPulse);
    rightEncoder.setDistancePerPulse(Constants.KDrivetrain.kDistancePerPulse);

    resetEncoders();
  }

  @Override
  public void updateInputs(DrivetrainIOInputs inputs) {
    inputs.leftEncoderCount = leftEncoder.get();
    inputs.rightEncoderCount = rightEncoder.get();
    inputs.leftVelocity = leftEncoder.getRate();
    inputs.rightVelocity = rightEncoder.getRate();
    inputs.leftDistance = leftEncoder.getDistance();
    inputs.rightDistance = rightEncoder.getDistance();
    inputs.leftVolts = leftVolts;
    inputs.rightVolts = rightVolts;
  }

  @Override
  public void setVoltage(double leftVolts, double rightVolts) {
    leftVolts = MathUtil.clamp(leftVolts, -6.0, 6.0);
    rightVolts = MathUtil.clamp(rightVolts, -6.0, 6.0);
    leftMotor.set(leftVolts / 6.0);
    rightMotor.set(rightVolts / 6.0);
  }

  @Override
  public void resetEncoders() {
    leftEncoder.reset();
    rightEncoder.reset();
  }

  public void arcadeDrive(double xaxisSpeed, double zaxisRotate) {
    diffDrive.arcadeDrive(xaxisSpeed, zaxisRotate);
  }
}
