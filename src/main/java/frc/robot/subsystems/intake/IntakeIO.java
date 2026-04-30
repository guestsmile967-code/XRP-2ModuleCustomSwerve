// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.intake;

import org.littletonrobotics.junction.AutoLog;

/** 
 * Minimal IntakeIO that stores the motor velocity and position
 * 
 * @author Bo Kuang
 * @version 1.0.0
*/
public interface IntakeIO {
    @AutoLog
    public static class IntakeIOInputs {
        public double intakeVelocityPercentage = 0.0;
        public double intakePositionRad = 0.0;
    }

    /** Updates the set of loggable inputs. */
    public default void updateInputs(IntakeIOInputs inputs) {
    }

    /** Run the drive motor at the specified percentage. */
    public default void setIntakePercent(double velocityPercentage) {
    }

}
