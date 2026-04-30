// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.drive;

import org.littletonrobotics.junction.AutoLog;


/**
 * Interface for a Swerve Module where a motor is the drive and a servo is the steer/azimuth
 * 
 * @author Bo Kuang
 * @version 1.0.0
 */
public interface ModuleIO {
    @AutoLog
    public static class ModuleIOInputs {
        public double driveVelocityPercentage = 0.0;
        public double drivePositionRad = 0.0;

        public double steerPositionAngle = 0.0;
    }

    /** Updates the set of loggable inputs. */
    public default void updateInputs(ModuleIOInputs inputs) {
    }

    /** Run the drive motor at the specified percentage. */
    public default void setDriveSpeed(double velocityPercentage) {
    }

    /** Run the steer motor at the specified angle. */
    public default void setSteerPosition(double angle) {
    }
}
