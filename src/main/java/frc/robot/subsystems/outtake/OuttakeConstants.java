// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.outtake;

import frc.robot.Constants.PortConstants;

/**
 * Holds important constants for Outtake
 * 
 * @author Bo Kuang
 * @version 1.0.0
 */
public class OuttakeConstants {
    // PortIds for outtake hardware
    public static final class Ports {
        public static final int kOuttakeMotorPort = PortConstants.kMotor3Port;

        public static final int kOuttakeMotorEncoderAPort = PortConstants.kMotor3EncoderAPort;
        public static final int kOuttakeMotorEncoderBPort = PortConstants.kMotor3EncoderBPort;
    }
}
