// Copyright (c) 2021-2026 TigerBotics

// Use of this source code is governed by a BSD
// license that can be found in the LICENSE file
// at the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.DriveCommands;
import frc.robot.subsystems.drive.Drive;
import frc.robot.subsystems.drive.GyroIO;
import frc.robot.subsystems.drive.GyroIOReal;
import frc.robot.subsystems.drive.ModuleIO;
import frc.robot.subsystems.drive.ModuleIOReal;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.intake.IntakeIO;
import frc.robot.subsystems.intake.IntakeIOReal;
import frc.robot.subsystems.outtake.Outtake;
import frc.robot.subsystems.outtake.OuttakeIO;
import frc.robot.subsystems.outtake.OuttakeIOReal;
import frc.robot.subsystems.vision.Vision;
import frc.robot.subsystems.vision.VisionConstants;
import frc.robot.subsystems.vision.VisionIO;
import frc.robot.subsystems.vision.VisionIOReal;

import org.littletonrobotics.junction.networktables.LoggedDashboardChooser;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // Subsystems
  private final Drive drive;
  private final Intake intake;
  private final Outtake outtake;
  private final Vision vision;

  // Booleans for toggles and states
  private boolean isFieldRelative = false;

  // Controller
  private final CommandXboxController controller = new CommandXboxController(0);

  // Dashboard inputs
  private final LoggedDashboardChooser<Command> autoChooser;
  private final LoggedDashboardChooser<Double> linearSpeedLimitChooser;
  private final LoggedDashboardChooser<Double> angularSpeedLimitChooser;


  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    switch (Constants.currentMode) {
      case SIM:
        // XRP is a simbot so use this
        drive = new Drive(
            new GyroIOReal(),
            new ModuleIOReal(true),
            new ModuleIOReal(false));
        intake = new Intake(
            new IntakeIOReal()
        );
        outtake = new Outtake(
            new OuttakeIOReal()
        );
        vision = new Vision(
            new VisionIOReal(VisionConstants.camName)
        );
      
        break;
      default:
        // Replayed robot, disable IO implementations
        drive = new Drive(
            new GyroIO() {
            },
            new ModuleIO() {
            },
            new ModuleIO() {
            });
        intake = new Intake(new IntakeIO() {});
        outtake = new Outtake(new OuttakeIO() {});
        vision = new Vision(new VisionIO() {});

    }

    // Set up auto routines
    autoChooser = new LoggedDashboardChooser<>("Auto Choices");

    // Set up speed limit chooser
    linearSpeedLimitChooser = new LoggedDashboardChooser<>("Linear Speed Limit");
    angularSpeedLimitChooser = new LoggedDashboardChooser<>("Angular Speed Limit");

    linearSpeedLimitChooser.addDefaultOption("Competition Mode", 1.0);
    linearSpeedLimitChooser.addOption("Fast Speed (70%)", 0.7);
    linearSpeedLimitChooser.addOption("Medium Speed (30%)", 0.3);
    linearSpeedLimitChooser.addOption("Slow Speed (15%)", 0.15);

    angularSpeedLimitChooser.addDefaultOption("Competition Mode", 1.0);
    angularSpeedLimitChooser.addOption("Fast Speed (70%)", 0.7);
    angularSpeedLimitChooser.addOption("Mediumer Speed (50%)", 0.5);
    angularSpeedLimitChooser.addOption("Medium Speed (30%)", 0.3);
    angularSpeedLimitChooser.addOption("Slow Speed (15%)", 0.15);
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing
   * it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // Default command, normal field-relative drive
    drive.setDefaultCommand(
        DriveCommands.joystickDrive(
            drive,
            () -> -controller.getLeftY(),
            () -> -controller.getLeftX(),
            () -> -controller.getRightX(),
            () -> linearSpeedLimitChooser.get(),
            () -> angularSpeedLimitChooser.get(),
            () -> {
              return isFieldRelative;
            }));
    
    intake.setDefaultCommand(
        Commands.run(intake::stop, intake)
    );

    
    outtake.setDefaultCommand(
        Commands.run(outtake::stop, outtake)
    );

    controller.leftStick().onTrue(
        Commands.runOnce(
            () -> {
                isFieldRelative = !isFieldRelative;
            })
    );

    controller.y().whileTrue(
        DriveCommands.joystickDriveAtAngle(
            drive, 
            () -> -controller.getLeftX(),
            () -> -controller.getLeftY(), 
            () -> linearSpeedLimitChooser.get(), 
            () -> angularSpeedLimitChooser.get(), 
            () -> vision.getTargetX())
    );

    controller.leftBumper().whileTrue(
        Commands.run(
            () -> {
                intake.setPercent(-0.7);
            }, intake)
    );

    controller.rightBumper().whileTrue(
        Commands.run(
            () -> {
                outtake.setPercent(-0.7);
            }, outtake)
    );

    /* 
    controller.pov(0).whileTrue(
        DriveCommands.joystickDrive(
            drive,
            () -> -1,
            () -> 0,
            () -> 0,
            () -> linearSpeedLimitChooser.get(),
            () -> angularSpeedLimitChooser.get(),
            () -> {
              return isFieldRelative;
            })
    );
            */

    controller.pov(0).whileTrue(
        Commands.run(
            () -> {
                drive.setServos(0, 0);
                drive.setSpeeds(1, 1);
            }, drive)
    );

    controller.pov(45).whileTrue(
        Commands.run(
            () -> {
                drive.setServos(-45, -45);
                drive.setSpeeds(1, 1);
            }, drive)
    );

    controller.pov(90).whileTrue(
        Commands.run(
            () -> {
                drive.setServos(90, 90);
                drive.setSpeeds(-1,-1);
            }, drive)
    );

    controller.pov(135).whileTrue(
        Commands.run(
            () -> {
                drive.setServos(45, 45);
                drive.setSpeeds(-1, -1);
            }, drive)
    );

    controller.pov(180).whileTrue(
        Commands.run(
            () -> {
                drive.setServos(0, 0);
                drive.setSpeeds(-1, -1);
            }, drive)
    );

    controller.pov(225).whileTrue(
        Commands.run(
            () -> {
                drive.setServos(-45, -45);
                drive.setSpeeds(-1, -1);
            }, drive)
    );

    controller.pov(270).whileTrue(
        Commands.run(
            () -> {
                drive.setServos(90, 90);
                drive.setSpeeds(1,1);
            }, drive)
    );

    controller.pov(360 - 45).whileTrue(
        Commands.run(
            () -> {
                drive.setServos(45, 45);
                drive.setSpeeds(1, 1);
            }, drive)
    );

    

    
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return autoChooser.get();
  }
}