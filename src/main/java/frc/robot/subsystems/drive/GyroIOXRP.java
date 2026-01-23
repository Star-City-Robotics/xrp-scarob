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

  /**
   * The acceleration in the X-axis.
   *
   * @return The acceleration of the XRP along the X-axis in Gs
   */
  @Override
  public double getAccelX() {
    return m_accelerometer.getX();
  }

  /**
   * The acceleration in the Y-axis.
   *
   * @return The acceleration of the XRP along the Y-axis in Gs
   */
  @Override
  public double getAccelY() {
    return m_accelerometer.getY();
  }

  /**
   * The acceleration in the Z-axis.
   *
   * @return The acceleration of the XRP along the Z-axis in Gs
   */
  @Override
  public double getAccelZ() {
    return m_accelerometer.getZ();
  }

  /**
   * Current angle of the XRP around the X-axis.
   *
   * @return The current angle of the XRP in degrees
   */
  @Override
  public double getGyroAngleX() {
    return m_gyro.getAngleX();
  }

  /**
   * Current angle of the XRP around the Y-axis.
   *
   * @return The current angle of the XRP in degrees
   */
  @Override
  public double getGyroAngleY() {
    return m_gyro.getAngleY();
  }

  /**
   * Current angle of the XRP around the Z-axis.
   *
   * @return The current angle of the XRP in degrees
   */
  @Override
  public double getGyroAngleZ() {
    return m_gyro.getAngleZ();
  }

  /** Reset the gyro. */
  @Override
  public void resetGyro() {
    m_gyro.reset();
  }
}
