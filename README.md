XRP Swerve Drive (2-Module)

This project implements a custom 2-module swerve drive on the XRP platform, using servo-based azimuth (steering) control, as well as a disk-based intake and vertical flywheel outtake.
It’s designed as a lightweight, experimental drivetrain with integrated vision and logging tools.

Overview:

Unlike traditional 4-module swerve systems, this robot uses two independent swerve modules, each capable of:

- Driving (wheel rotation)
- Steering (azimuth via servo)

This simplifies mechanical complexity while still enabling omnidirectional movement concepts.

Features:

- 2-Module Swerve Drive:
Independent drive + steering per module
Servo-controlled azimuth (steering angle)
- AdvantageKit Integration
Logging and telemetry for debugging and performance analysis
- PhotonVision (USB Tethered)
Vision processing via external camera
USB tether to computer that runs PhotonVision
- Custom CAD
Includes mechanical design files for the swerve modules
Designed specifically for XRP constraints

Hardware Setup

- Custom XRP Chassis and various parts from CAD
- M3 and M4 hardware
- 4x XRP Hobby Motors
- 2x servos (for azimuth control)
- USB camera (for vision via PhotonVision)
- Supporting electronics (wiring, battery, motherboard, etc.)

Software Stack

Java / WPILib (XRP environment)
AdvantageKit for logging & telemetry
PhotonVision for vision processing
GitHub for version control

Getting Started
1. Clone the repo
```git clone https://github.com/username/repo.git```
2. Open in VS Code

Open the folder in Visual Studio Code.

3. Build & deploy

Use WPILib tools to deploy to the XRP robot.

Vision Setup
Install and run PhotonVision on your coprocessor or laptop
Connect camera via USB to the computer
Configure pipelines in PhotonVision UI

Telemetry (AdvantageKit)
Logs are automatically recorded during runtime
Use AdvantageScope to replay and analyze data
Helpful for tuning steering control and drive performance

CAD Files

CAD files are found on OnShape at this link ```https://cad.onshape.com/documents/9c818541e2d90d03905fff6a/w/c1461b4ce5be43414c71195b/e/e1d23d7083b80ccbfaad2e29?renderMode=0&uiState=69f3c060d62beea003e24e4a```

Notes & Limitations
- 2-module swerve is not fully holonomic like 4-module systems
- Stability and control algorithms may require tuning
- Servo-based steering may have slower response compared to motor-driven azimuth
- Tolerances are tight

Contributing

This is a personal project so pull requests and improvements are welcome, but probably will not be looked at too much. However, feel free to fork and experiment.

Acknowledgements

Thank you Dr. Hailong Jiang for sponsoring this project for Computer Projects at YSU. 
Thank you Howland High School and APTIV for the oppurtunity to do FRC robotics. 
Thank you to the Siegfrieds for the resources for this project.

Without anyone of them, this project would not be possible.

License

This project is licensed under the BSD 3-Clause License.

See the [LICENSE](LICENSE.md) file for details.
