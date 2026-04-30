// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.vision;

import org.photonvision.PhotonCamera;

import edu.wpi.first.math.geometry.Rotation2d;

/**
 * Implementation of Vision with USB tether and logging
 * 
 * @author Bo Kuang
 * @version 1.0.0
 */
public class VisionIOReal implements VisionIO {
  private final PhotonCamera camera;

  public VisionIOReal(String name) {
    camera = new PhotonCamera(name);
  }

  @Override
  public void updateInputs(VisionIOInputs inputs) {
    inputs.connected = camera.isConnected();

    for (var result : camera.getAllUnreadResults()) {
      // Update latest target observation
      if (result.hasTargets()) {
        inputs.latestTargetObservation = new TargetObservation(
            Rotation2d.fromDegrees(result.getBestTarget().getYaw()),
            Rotation2d.fromDegrees(result.getBestTarget().getPitch()));

      } else {
        inputs.latestTargetObservation = new TargetObservation(Rotation2d.kZero, Rotation2d.kZero);
      }
    }
  }
}
