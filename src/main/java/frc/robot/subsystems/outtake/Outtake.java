// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.outtake;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * XRP Outtake with a vertical flywheel connected via gears
 * 
 * @author Bo Kuang
 * @version 1.0.0
 */
public class Outtake extends SubsystemBase {
    private final OuttakeIO outtakeIO;
    private final OuttakeIOInputsAutoLogged outtakeInputs = new OuttakeIOInputsAutoLogged();

    public Outtake(OuttakeIO outtakeIO) {
        this.outtakeIO = outtakeIO;
    }

    @Override
    public void periodic() {
        outtakeIO.updateInputs(outtakeInputs);
        Logger.processInputs("Outtake", outtakeInputs);

        // Stop moving when disabled
        if (DriverStation.isDisabled()) {
            outtakeIO.setOuttakePercent(0);
        }
    }

    public void setPercent(double percent) {
        outtakeIO.setOuttakePercent(percent);
    }

    public void stop() {
        outtakeIO.setOuttakePercent(0);
    }
}
