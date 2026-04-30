// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.drive;

import org.littletonrobotics.junction.AutoLog;

import edu.wpi.first.math.geometry.Rotation2d;

/**
 * Interface for Gyro I/O to log
 * 
 * TODO: Implement accelerometer as well for future odometry
 * 
 * @author Bo Kuang
 * @version 1.0.0
 */
public interface GyroIO {
    @AutoLog
    public static class GyroIOInputs {
        public Rotation2d yawPosition = Rotation2d.kZero;
        // public double yawVelocityRadPerSec = 0.0;
    }

    public default void updateInputs(GyroIOInputs inputs) {
    }
}
