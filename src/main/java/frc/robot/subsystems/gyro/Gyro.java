package frc.robot.subsystems.gyro;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.xrp.XRPGyro;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

// import edu.wpi.first.math.estimator.PoseEstimator3d;

public class Gyro extends SubsystemBase {

  // Set up the XRPGyro
  private final XRPGyro m_gyro = new XRPGyro();

  // Set up the BuiltInAccelerometer
  private final BuiltInAccelerometer m_accelerometer = new BuiltInAccelerometer();

  private final GyroIO gyroIO;
  private final GyroIOInputsAutoLogged gyroInputs = new GyroIOInputsAutoLogged();

  public Gyro(GyroIO gyroIO) {
    this.gyroIO = gyroIO;
  }

  /**
   * The acceleration in the X-axis.
   *
   * @return The acceleration of the XRP along the X-axis in Gs
   */
  public double getAccelX() {
    return m_accelerometer.getX();
  }

  /**
   * The acceleration in the Y-axis.
   *
   * @return The acceleration of the XRP along the Y-axis in Gs
   */
  public double getAccelY() {
    return m_accelerometer.getY();
  }

  /**
   * The acceleration in the Z-axis.
   *
   * @return The acceleration of the XRP along the Z-axis in Gs
   */
  public double getAccelZ() {
    return m_accelerometer.getZ();
  }

  /**
   * Current angle of the XRP around the X-axis.
   *
   * @return The current angle of the XRP in degrees
   */
  public double getGyroAngleX() {
    return m_gyro.getAngleX();
  }

  /**
   * Current angle of the XRP around the Y-axis.
   *
   * @return The current angle of the XRP in degrees
   */
  public double getGyroAngleY() {
    return m_gyro.getAngleY();
  }

  /**
   * Current angle of the XRP around the Z-axis.
   *
   * @return The current angle of the XRP in degrees
   */
  public double getGyroAngleZ() {
    return m_gyro.getAngleZ();
  }

  /** Reset the gyro. */
  public void resetGyro() {
    m_gyro.reset();
  }

  @Override
  public void periodic() {
    gyroIO.updateInputs(gyroInputs);
    Logger.processInputs("Drive/Gyro", gyroInputs);
  }
}