// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.outtake;

import org.littletonrobotics.junction.AutoLog;

/** 
 * Minimal OuttakeIO that stores the motor velocity and position
 * 
 * @author Bo Kuang
 * @version 1.0.0
*/
public interface OuttakeIO {
    @AutoLog
    public static class OuttakeIOInputs {
        public double outtakeVelocityPercentage = 0.0;
        public double outtakePositionRad = 0.0;
    }

    /** Updates the set of loggable inputs. */
    public default void updateInputs(OuttakeIOInputs inputs) {
    }

    /** Run the outtake motor at the specified percentage. */
    public default void setOuttakePercent(double velocityPercentage) {
    }
}
