// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.vision;

import org.littletonrobotics.junction.AutoLog;

import edu.wpi.first.math.geometry.Rotation2d;

/** 
 * Minimal OuttakeIO that stores the latest seen AprilTag
 * 
 * @author Bo Kuang
 * @version 1.0.0
*/
public interface VisionIO {
    @AutoLog
    public static class VisionIOInputs {
        public boolean connected = false;
        public TargetObservation latestTargetObservation = 
            new TargetObservation(Rotation2d.kZero, Rotation2d.kZero);
    }

    /** Represents the angle to a simple target, not used for pose estimation. */
    public static record TargetObservation(Rotation2d tx, Rotation2d ty) {
    }

    /** Updates the set of inputs */
    public default void updateInputs(VisionIOInputs inputs) {}
}
