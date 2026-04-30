// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.RobotBase;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean
 * constants. This class should not be used for any other purpose. All constants
 * should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static final Mode simMode = Mode.SIM;
  public static final Mode currentMode = RobotBase.isReal() ? Mode.REAL : simMode;

  public static enum Mode {
    /** Running on a real robot. */
    REAL,

    /** Running a physics simulator. */
    SIM,

    /** Replaying from a log file. */
    REPLAY
  }

  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }

  // Contains embedded ports to hardware
  public static class PortConstants {
    public static final int kMotorLeftPort = 0;
    public static final int kMotorRightPort = 1;
    public static final int kMotor3Port = 2;
    public static final int kMotor4Port = 3;

    public static final int kServo1Port = 4;
    public static final int kServo2Port = 5;
    public static final int kServo3Port = 6;
    public static final int kServo4Port = 7;

    public static final int kMotorLeftEncoderAPort = 4;
    public static final int kMotorLeftEncoderBPort = 5;

    public static final int kMotorRightEncoderAPort = 6;
    public static final int kMotorRightEncoderBPort = 7;

    public static final int kMotor3EncoderAPort = 8;
    public static final int kMotor3EncoderBPort = 9;

    public static final int kMotor4EncoderAPort = 10;
    public static final int kMotor4EncoderBPort = 11;

    public static final int kUserButtonDIOPort = 0;
    public static final int kGreenLEDDIOPort = 1;

    public static final int kLineFollowingLeftSensorAnalogPort = 0;
    public static final int kLineFollowingRightSensorAnalogPort = 1;

    public static final int kUltrasonicSensorAnalogPort = 2;
  }

  public static class RobotConstants {
    public static final double kWheelDiameterInch = Units.metersToInches(0.05); // 50 mm to inch

    public static final double trackWidthFromCenterInch = Units.metersToInches(0.063341); // mm to inch
    public static final double wheelBaseFromCenterInch = Units.metersToInches(0.065);
    public static final double driveBaseRadiusInch = Math.hypot(trackWidthFromCenterInch, wheelBaseFromCenterInch);

    public static final Translation2d[] moduleTranslationsInch = new Translation2d[] {
        new Translation2d(trackWidthFromCenterInch, -wheelBaseFromCenterInch), // Front Right
        new Translation2d(-trackWidthFromCenterInch, wheelBaseFromCenterInch) // Back Left
    };

    public static final double kDriveReduction = (36.0 / 74.0) * (45.0 / 11.0);

    public static final double kMaxSpeedInchPerSecond = (MotorConstants.kFreeSpeedRPM / 60.0)
        * (Math.PI * kWheelDiameterInch) * (4); // TODO: Scaling issue so multiply by 4 for temp fix
  }

  // Physical hardware specs of the XRP Motor
  public static final class MotorConstants {
    private static final double kMotorGearRatio = (30.0 / 14.0) * (28.0 / 16.0) * (36.0 / 9.0) * (26.0 / 8.0); // 48.75:1
    private static final double kCountsPerMotorShaftRev = 12.0;
    public static final double kCountsPerRevolution = kCountsPerMotorShaftRev * kMotorGearRatio; // 585.0

    public static final double kFreeSpeedRPM = 90;
  }
}
