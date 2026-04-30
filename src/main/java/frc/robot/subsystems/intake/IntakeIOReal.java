// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.intake;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.xrp.XRPMotor;
import frc.robot.Constants.MotorConstants;
import frc.robot.Constants.RobotConstants;
import frc.robot.subsystems.intake.IntakeConstants.Ports;

/**
 * Implementation of the 1-axle intake with logging
 * 
 * TODO: Update reductions even though it we are not using encoders yet
 * 
 * @author Bo Kuang
 * @version 1.0.0
 */
public class IntakeIOReal implements IntakeIO {
    private final XRPMotor intakeMotor;
    private final Encoder intakeEncoder;

    public IntakeIOReal() {
        intakeMotor = new XRPMotor(Ports.kIntakeMotorPort);
        intakeEncoder = new Encoder(
            Ports.kIntakeMotorEncoderAPort, 
            Ports.kIntakeMotorEncoderBPort
        );

        configure();
    }

    private void configure() {
        intakeMotor.setInverted(false);

        // Use inches as unit for encoder distances
        intakeEncoder.setDistancePerPulse((Math.PI * RobotConstants.kWheelDiameterInch) /
         (MotorConstants.kCountsPerRevolution));
        intakeEncoder.reset();
    }

    @Override
    public void updateInputs(IntakeIOInputs inputs) {
        inputs.intakeVelocityPercentage = intakeMotor.get();
        inputs.intakePositionRad = intakeEncoder.getDistance();
    }

    @Override
    public void setIntakePercent(double velocityPercentage) {
        intakeMotor.set(velocityPercentage);
    }
}
