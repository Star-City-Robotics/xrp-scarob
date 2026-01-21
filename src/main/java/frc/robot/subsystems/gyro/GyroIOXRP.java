package frc.robot.subsystems.gyro;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Rotation3d;

public class GyroIOXRP implements GyroIO {
  public GyroIOXRP() {}

  @Override
  public void updateInputs(GyroIOInputs inputs) {
    inputs.connected = true; // TODO: update gyro.connected
    inputs.yawPosition = Rotation2d.kZero;
    inputs.yawVelocityRadPerSec = 0.0;

    inputs.theta = Rotation3d.kZero;
    inputs.omega = Rotation3d.kZero;
  }
}