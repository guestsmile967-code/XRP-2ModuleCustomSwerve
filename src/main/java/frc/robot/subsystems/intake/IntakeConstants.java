// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.intake;

import frc.robot.Constants.PortConstants;

/**
 * * Holds important constants for Intake
 * 
 * @author Bo Kuang
 * @version 1.0.0
 */
public class IntakeConstants {
    // PortIds for intake hardware
    public static final class Ports {
        public static final int kIntakeMotorPort = PortConstants.kMotorRightPort;

        public static final int kIntakeMotorEncoderAPort = PortConstants.kMotorRightEncoderAPort;
        public static final int kIntakeMotorEncoderBPort = PortConstants.kMotorRightEncoderBPort;
    }
}
