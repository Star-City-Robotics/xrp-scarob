package frc.robot.subsystems.gyro;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Rotation3d;
import org.littletonrobotics.junction.AutoLog;

public interface GyroIO {
  @AutoLog
  public static class GyroIOInputs {
    // These IO fields *must* have an initial value.
    // Setting that here is simpler, rather than in the GyroIOXRP constructor.
    public boolean connected = false;
    public Rotation2d yawPosition = Rotation2d.kZero;
    public double yawVelocityRadPerSec = 0.0;

    // Necessary when using a queue to write data from vendor libs:
    //
    // public double[] odometryYawTimestamps = new double[] {};
    // public Rotation2d[] odometryYawPositions = new Rotation2d[] {};

    public Rotation3d theta = Rotation3d.kZero;
    public Rotation3d omega = Rotation3d.kZero;
  }
  public default void updateInputs(GyroIOInputs inputs) {}
}
