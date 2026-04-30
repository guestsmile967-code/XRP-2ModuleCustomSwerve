// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.drive;

import edu.wpi.first.hal.FRCNetComm.tInstances;
import edu.wpi.first.hal.FRCNetComm.tResourceType;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.hal.HAL;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.RobotConstants;

/** 
 * 2-Module Implementation of Swerve Drive with Motor controlling drive and Swervo controlling azimuth
 * Gyro for field relative drive as well
 * 
 * TODO: Add odometry once robot is fixed
 * 
 * @author Bo Kuang
 * @version 1.0.0
 */
public class Drive extends SubsystemBase {
  private final GyroIO gyroIO;
  private final GyroIOInputsAutoLogged gyroInputs = new GyroIOInputsAutoLogged();
  private final Module[] modules = new Module[2]; // F, B

  private SwerveDriveKinematics kinematics = new SwerveDriveKinematics(RobotConstants.moduleTranslationsInch);
  private Rotation2d rawGyroRotation = Rotation2d.kZero;

  public Drive(
    GyroIO gyroIO,
    ModuleIO fModuleIO,
    ModuleIO bModuleIO
  ) {
    this.gyroIO = gyroIO;
    modules[0] = new Module(fModuleIO, true);
    modules[1] = new Module(bModuleIO, false);

    // Usage reporting for swerve template
    HAL.report(tResourceType.kResourceType_RobotDrive, tInstances.kRobotDriveSwerve_AdvantageKit);
  }

  @Override
  public void periodic() {
    gyroIO.updateInputs(gyroInputs);
    Logger.processInputs("Drive/Gyro", gyroInputs);

    for (var module : modules) {
      module.periodic();
    }

    // Stop moving when disabled
    if (DriverStation.isDisabled()) {
      for (var module : modules) {
        module.stop();
      }
    }

    // Update gyro angle
    rawGyroRotation = gyroInputs.yawPosition;
  }

  /**
   * Runs the drive at the desired velocity.
   *
   * @param speeds Speeds in meters/sec
   */
  public void runVelocity(ChassisSpeeds speeds) {
    // Calculate module setpoints
    ChassisSpeeds discreteSpeeds = ChassisSpeeds.discretize(speeds, 0.02);
    SwerveModuleState[] setpointStates = kinematics.toSwerveModuleStates(discreteSpeeds);
    SwerveDriveKinematics.desaturateWheelSpeeds(setpointStates,  Units.inchesToMeters(RobotConstants.kMaxSpeedInchPerSecond)
    );

    // Log unoptimized setpoints
    Logger.recordOutput("SwerveStates/Setpoints", setpointStates);
    Logger.recordOutput("SwerveChassisSpeeds/Setpoints", discreteSpeeds);

    // Send setpoints to modules
    modules[0].runSetpoint(setpointStates[0]); // Front Right
    modules[1].runSetpoint(setpointStates[1]); // Back Left

    // Log optimized setpoints (runSetpoint mutates each state)
    Logger.recordOutput("SwerveStates/SetpointsOptimized", setpointStates);
  }

  /**
   * Set servo angle from [0,180]
   * 
   * @param frontAngle for the front servo
   * @param backAngle for the back servo
   */
  public void setServos(double frontAngle, double backAngle) {
    modules[0].setAngle(frontAngle);
    modules[1].setAngle(backAngle);
  }

  /**
   * Set motor speed from [-1,1]
   * 
   * @param frontSpeed
   * @param backSpeed
   */
  public void setSpeeds(double frontSpeed, double backSpeed) {
    // modules[0].setSpeed(frontSpeed);
    modules[1].setSpeed(backSpeed);
  }


  /** Stops the drive. */
  public void stop() {
    runVelocity(new ChassisSpeeds());
  }

  /** Resteers the current odometry rotation. */
  public Rotation2d getRotation() {
    return rawGyroRotation;
  }

  /** Resteers the maximum linear speed in inch per sec. */
  public double getMaxLinearSpeedMetersPerSec() {
    return RobotConstants.kMaxSpeedInchPerSecond;
  }

  /** Resteers the maximum angular speed in radians per sec. */
  public double getMaxAngularSpeedRadPerSec() {
    return RobotConstants.kMaxSpeedInchPerSecond / RobotConstants.driveBaseRadiusInch;
  }
}
