package frc.robot.subsystems.drive;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation3d;
import org.littletonrobotics.junction.AutoLog;

public interface GyroIO {
  @AutoLog
  public static class GyroIOInputs {

    // These IO fields *must* have an initial value.
    // Setting that here is simpler, rather than in the GyroIOXRP constructor.
    public boolean connected = false;
    public Rotation2d yawPosition = Rotation2d.kZero;
    public double yawVelocityRadPerSec = 0.0;
    public Rotation2d pitchPosition = Rotation2d.kZero;
    public double pitchVelocityRadPerSec = 0.0;
    public Rotation2d rollPosition = Rotation2d.kZero;
    public double rollVelocityRadPerSec = 0.0;
    public Translation3d accel = Translation3d.kZero;

    // public Rotation3d theta = Rotation3d.kZero;
    // public Rotation3d omega = Rotation3d.kZero;

    // Necessary when using a queue to write data from vendor libs:
    //
    // public double[] odometryYawTimestamps  new double[] {};
    // public Rotation2d[] odometryYawPositions = new Rotation2d[] {};
  }

  public default void updateInputs(GyroIOInputs inputs) {}

  // /** Reset the gyro. */
  public default void resetGyro() {}
}
