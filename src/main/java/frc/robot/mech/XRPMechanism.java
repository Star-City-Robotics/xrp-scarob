package frc.robot.mech;

import edu.wpi.first.hal.HALUtil;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Translation3d;
import org.littletonrobotics.junction.Logger;

public class XRPMechanism {
  private long prevMicroseconds = 0;

  public static final double wheelAxisToGround = 0.030184;
  public static final Translation3d wheelAxisTranslation =
      new Translation3d(0, 0, wheelAxisToGround);

  public double wheelLeft = 0.0;
  public double wheelRight = 0.0;

  private static XRPMechanism measured;
  public static XRPMechanism getMeasured() {
    if (measured == null) {
      measured = new XRPMechanism();
    }
    return measured;
  }

  public void log(String key) {
    var microseconds = HALUtil.getFPGATime();
    var deltaT = ((double) (microseconds - prevMicroseconds)) / (1000000);
    this.prevMicroseconds = microseconds;

    var radsPerSecond = Math.PI;
    var rads = radsPerSecond * deltaT;

    this.wheelLeft += rads;
    if (wheelLeft > Math.PI) {
      wheelLeft -= 2 * Math.PI;
    }
    this.wheelRight += rads;
    if (wheelRight > Math.PI) {
      wheelRight -= 2 * Math.PI;
    }

    var wheelRotationLeft = new Rotation3d(0, wheelLeft, 0);
    var wheelRotationRight = new Rotation3d(0, wheelRight, 0);
    var wheelLeftPose = Pose3d.kZero.rotateAround(wheelAxisTranslation, wheelRotationLeft);
    var wheelRightPose = Pose3d.kZero.rotateAround(wheelAxisTranslation, wheelRotationRight);

    Logger.recordOutput(key + "/Rotations/Input", rads);
    Logger.recordOutput(key + "/Rotations/WheelLeft", this.wheelLeft);
    Logger.recordOutput(key + "/Rotations/WheelRight", this.wheelRight);
    Logger.recordOutput(key + "/Components", wheelLeftPose, wheelRightPose);

    var cameraPose = Pose3d.kZero;

    Logger.recordOutput(key + "/CameraPose", cameraPose);
  }
}
