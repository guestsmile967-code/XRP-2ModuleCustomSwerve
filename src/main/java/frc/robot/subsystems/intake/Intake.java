// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.intake;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * XRP Intake where a 1-axle spaced disk mechanism to pick up balls and roll them onto teeth
 * Thus, only needing one motor
 * 
 * @author Bo Kuang
 * @version 1.0.0
 */
public class Intake extends SubsystemBase {
    private final IntakeIO intakeIO;
    private final IntakeIOInputsAutoLogged intakeInputs = new IntakeIOInputsAutoLogged();

    public Intake(IntakeIO intakeIO) {
        this.intakeIO = intakeIO;
    }

    /** Calls {@link updateInputs} to properly update the inputs for the intake */
    @Override
    public void periodic() {
        intakeIO.updateInputs(intakeInputs);
        Logger.processInputs("Intake", intakeInputs);

        // Stop moving when disabled
        if (DriverStation.isDisabled()) {
            intakeIO.setIntakePercent(0);
        }
    }

    /**
     * Sets the percent speed of the intake from [-1,1]
     * @param percent of the motor
     */
    public void setPercent(double percent) {
        intakeIO.setIntakePercent(percent);
    }

    /**
     * Stops the intake
     */
    public void stop() {
        intakeIO.setIntakePercent(0);
    }
}
