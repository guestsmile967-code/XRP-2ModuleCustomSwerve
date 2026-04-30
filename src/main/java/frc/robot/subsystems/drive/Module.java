// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.drive;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.kinematics.SwerveModuleState;

/**
 * Real implementation of a Swerve Module where a motor is the drive and a servo is the steer/azimuth
 * 
 * @author Bo Kuang
 * @version 1.0.0
 */
public class Module {
    private final ModuleIO io;
    private final ModuleIOInputsAutoLogged inputs = new ModuleIOInputsAutoLogged();
    private final boolean isFront;

    public Module(ModuleIO io, boolean isFront) {
        this.io = io;
        this.isFront = isFront;
    }

    /**
     * Logs and runs inputs.
     * 
     * Calls {@link updateInputs} for logging
     */
    public void periodic() {
        io.updateInputs(inputs);
        Logger.processInputs("Drive/Module" + (isFront ? "Front" : "Back"), inputs);
    }

    public void runSetpoint(SwerveModuleState state) {
        double currentAngle = Math.toRadians(getAngle()); // convert to radians
        double targetAngle = state.angle.getRadians();
        double speed = state.speedMetersPerSecond;

        // Compute shortest angular difference
        double delta = targetAngle - currentAngle;

        // Normalize to [-pi, pi]
        delta = Math.atan2(Math.sin(delta), Math.cos(delta));

        // If we would need to rotate more than 90 deg, flip direction
        if (Math.abs(delta) > Math.PI / 2) {
            speed = -speed;
            targetAngle += Math.PI;
        }

        // Convert to degrees for servo
        double angleDeg = Math.toDegrees(targetAngle);

        // Wrap into [0, 180]
        angleDeg = MathUtil.clamp((angleDeg % 180 + 180) % 180,0,180);

        // Apply setpoints
        io.setDriveSpeed(speed); 
        io.setSteerPosition(angleDeg);
    }

    /** Stops the module */
    public void stop() {
        io.setDriveSpeed(0);
    }

    /**
     * Sets the angle of the servo
     * @param angleDeg of the servo from [0,180]
     */
    public void setAngle(double angleDeg) {
        double currentAngle = Math.toRadians(getAngle());
        double targetAngle = Math.toRadians(angleDeg);

        
        // Compute shortest angular difference
        double delta = targetAngle - currentAngle;

        // Normalize to [-pi, pi]
        delta = Math.atan2(Math.sin(delta), Math.cos(delta));

        // If we would need to rotate more than 90 deg, flip direction
        if (Math.abs(delta) > Math.PI / 2) {
            targetAngle += Math.PI;
        }

        // Convert to degrees for servo
        angleDeg = Math.toDegrees(targetAngle);

        // Wrap into [0, 180]
        angleDeg = MathUtil.clamp((angleDeg % 180 + 180) % 180,0,180);

        io.setSteerPosition(angleDeg);
    }

    /**
     * Sets the speed of the motor
     * @param speed percent of the motor from [-1,1]
     */
    public void setSpeed(double speed) {
        if (getAngle() > 90.0) {
            speed = -speed;
        }

        io.setDriveSpeed(speed);
    }

    /**
     * Gets the angle of the servo in degrees
     * @return angle from [0,180]
     */
    public double getAngle() {
        return inputs.steerPositionAngle;
    }

    /**
     * Gets the velocity of the motor in percentage
     * @return velocity form [-1,1]
     */
    public double getVelocityPercentage() {
        return inputs.driveVelocityPercentage;
    }

    
}
