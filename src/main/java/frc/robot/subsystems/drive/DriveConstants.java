// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.drive;

import frc.robot.Constants.PortConstants;

/**
 * Holds important constants for Swerve Drive
 * 
 * @author Bo Kuang
 * @version 1.0.0
 */
public class DriveConstants {
    // Port Ids for devices
    public static final class Ports {
        public static final int kFrontDrivePort = PortConstants.kMotor4Port;
        public static final int kBackDrivePort = PortConstants.kMotorLeftPort;

        public static final int kFrontEncoderAPort = PortConstants.kMotor4EncoderAPort;
        public static final int kFrontEncoderBPort = PortConstants.kMotor4EncoderBPort;

        public static final int kBackEncoderAPort = PortConstants.kMotorLeftEncoderAPort;
        public static final int kBackEncoderBPort = PortConstants.kMotorLeftEncoderBPort;

        public static final int kFrontServoPort = PortConstants.kServo2Port;
        public static final int kBackServoPort = PortConstants.kServo3Port;
    }
}
