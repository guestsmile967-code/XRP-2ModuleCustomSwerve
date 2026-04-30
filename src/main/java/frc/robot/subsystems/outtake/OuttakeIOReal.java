// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.outtake;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.xrp.XRPMotor;
import frc.robot.Constants.MotorConstants;
import frc.robot.Constants.RobotConstants;
import frc.robot.subsystems.outtake.OuttakeConstants.Ports;

/**
 * Implementation of vertical flywheel outtake with logging
 * 
 * TODO: Update reductions even though it we are not using encoders yet
 * 
 * @author Bo Kuang
 * @version 1.0.0
 */
public class OuttakeIOReal implements OuttakeIO {
    private final XRPMotor outtakeMotor;
    private final Encoder outtakeEncoder;

    public OuttakeIOReal() {
        outtakeMotor = new XRPMotor(Ports.kOuttakeMotorPort);
        outtakeEncoder = new Encoder(
            Ports.kOuttakeMotorEncoderAPort, 
            Ports.kOuttakeMotorEncoderBPort
        );

        configure();
    }

    private void configure() {
        outtakeMotor.setInverted(false);

        // Use inches as unit for encoder distances
        outtakeEncoder.setDistancePerPulse(
            (Math.PI * RobotConstants.kWheelDiameterInch) /
            (MotorConstants.kCountsPerRevolution * RobotConstants.kDriveReduction)
        );
        outtakeEncoder.reset();
    }

    @Override
    public void updateInputs(OuttakeIOInputs inputs) {
        inputs.outtakeVelocityPercentage = outtakeMotor.get();
        inputs.outtakePositionRad = outtakeEncoder.getDistance();
    }

    @Override
    public void setOuttakePercent(double velocityPercentage) {
        outtakeMotor.set(velocityPercentage);
    }
}
