package frc.robot.subsystems.drive;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.xrp.XRPGyro;

public class GyroIOXRP implements GyroIO {
  private final XRPGyro m_gyro = new XRPGyro();
  private final BuiltInAccelerometer m_accelerometer = new BuiltInAccelerometer();

  public GyroIOXRP() {}

  @Override
  public void updateInputs(GyroIOInputs inputs) {
    inputs.connected = true;
    inputs.yawPosition = Rotation2d.fromRotations(m_gyro.getAngleZ());
    inputs.yawVelocityRadPerSec = 0.0;
    inputs.pitchPosition = Rotation2d.fromRotations(m_gyro.getAngleY());
    inputs.rollVelocityRadPerSec = 0.0;
    inputs.rollPosition = Rotation2d.fromRotations(m_gyro.getAngleX());
    inputs.pitchVelocityRadPerSec = 0.0;
    inputs.accel =
        new Translation3d(m_accelerometer.getX(), m_accelerometer.getY(), m_accelerometer.getZ());
  }

  /** Reset the gyro. */
  @Override
  public void resetGyro() {
    m_gyro.reset();
  }
}
