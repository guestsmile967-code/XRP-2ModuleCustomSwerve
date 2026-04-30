// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.drive;

import edu.wpi.first.wpilibj.xrp.XRPGyro;

/**
 * Implementation of the XRP Gyro w/ logging
 * 
 * @author Bo Kuang
 * @version 1.0.0
 */
public class GyroIOReal implements GyroIO {
    private final XRPGyro gyro = new XRPGyro();

    public GyroIOReal() {}

    @Override
    public void updateInputs(GyroIOInputs inputs) {
        inputs.yawPosition = gyro.getRotation2d();
    }
}
