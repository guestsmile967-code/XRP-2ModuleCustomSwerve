// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.vision;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.Alert;
import edu.wpi.first.wpilibj.Alert.AlertType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Vision system with USB tether to the computer running PhotonVision
 *
 * TODO: Properly implement this once hardware is fixed
 * 
 * @author Bo Kuang 
 * @version 1.0.0
 */
public class Vision extends SubsystemBase {
  private final VisionIO io;
  private final VisionIOInputsAutoLogged inputs = new VisionIOInputsAutoLogged();
  private final Alert disconnectedAlerts = new Alert("Vision Camera is disconnected", AlertType.kWarning);

  public Vision(VisionIO visionIO) {
    io = visionIO;
  }

  /**
   * Returns the X angle to the best target, which can be used for simple zeroing
   * with vision.
   *
   * @param cameraIndex The index of the camera to use.
   */
  public Rotation2d getTargetX() {
    return inputs.latestTargetObservation.tx();
  }
  

  @Override
  public void periodic() {
    io.updateInputs(inputs);
    Logger.processInputs("Vision", inputs);

    disconnectedAlerts.set(!inputs.connected);
    }
  }
