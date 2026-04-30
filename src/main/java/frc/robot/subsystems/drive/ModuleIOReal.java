// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.drive;

import static frc.robot.Constants.MotorConstants;
import static frc.robot.subsystems.drive.DriveConstants.*;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.xrp.XRPMotor;
import edu.wpi.first.wpilibj.xrp.XRPServo;
import frc.robot.Constants.RobotConstants;

/**
 * Implementation of the a custom Swerve Module w/ logging
 * 
 * @author Bo Kuang
 * @version 1.0.0
 */
public class ModuleIOReal implements ModuleIO {
    private final XRPMotor driveMotor;
    private final XRPServo steerServo;

    private final Encoder driveEncoder;

    /**
     * Implementation
     * 
     * @param isFront whether the module is the front or not
     */
    public ModuleIOReal(boolean isFront) {
        if (isFront) {
            driveMotor = new XRPMotor(Ports.kFrontDrivePort);
            steerServo = new XRPServo(Ports.kFrontServoPort);

            driveEncoder = new Encoder(Ports.kFrontEncoderAPort, Ports.kFrontEncoderBPort);
        } else {
            driveMotor = new XRPMotor(Ports.kBackDrivePort);
            steerServo = new XRPServo(Ports.kBackServoPort);

            driveEncoder = new Encoder(Ports.kBackEncoderAPort, Ports.kBackEncoderBPort);
        }
        driveMotor.setInverted(isFront);
        configure();
    }

    private void configure() {
        // Use inches as unit for encoder distances
        driveEncoder.setDistancePerPulse((Math.PI * RobotConstants.kWheelDiameterInch) / (MotorConstants.kCountsPerRevolution * RobotConstants.kDriveReduction));
        driveEncoder.reset();
    }

    @Override
    public void updateInputs(ModuleIOInputs inputs) {
        inputs.driveVelocityPercentage = driveMotor.get();
        inputs.drivePositionRad = driveEncoder.getDistance();

        inputs.steerPositionAngle = steerServo.getAngle();
    }

    @Override
    public void setDriveSpeed(double velocityPercentage) {
        driveMotor.set(velocityPercentage);
    }

    @Override
    public void setSteerPosition(double angle) {
        steerServo.setAngle(angle);
    }
}
