/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  private XboxController xbox = new XboxController(0);
  private DigitalInput limitSwitch = new DigitalInput(9) ;
  
  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
    
    SmartDashboard.putNumber("Rumble Left",  0.0);
    SmartDashboard.putNumber("Rumble Right", 0.0);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    SmartDashboard.putNumber("POV", (double) xbox.getPOV());
    SmartDashboard.putNumber("X - Left",  xbox.getX(Hand.kLeft));
    SmartDashboard.putNumber("Y - Left",  xbox.getY(Hand.kLeft));
    SmartDashboard.putNumber("X - Right", xbox.getX(Hand.kRight));
    SmartDashboard.putNumber("Y - Right", xbox.getY(Hand.kRight));


    SmartDashboard.putBoolean("Bumper - Left",  xbox.getBumper(Hand.kLeft));
    SmartDashboard.putBoolean("Bumper - Right", xbox.getBumper(Hand.kRight));
    SmartDashboard.putNumber("Trigger - Left",  xbox.getTriggerAxis(Hand.kLeft));
    SmartDashboard.putNumber("Trigger - Right", xbox.getTriggerAxis(Hand.kRight));

    SmartDashboard.putBoolean("A", xbox.getAButton());
    SmartDashboard.putBoolean("A - Pressed", xbox.getAButtonPressed());
    SmartDashboard.putBoolean("A - Released", xbox.getAButtonReleased());
    SmartDashboard.putBoolean("B", xbox.getBButton());
    SmartDashboard.putBoolean("B - Pressed", xbox.getBButtonPressed()); 
    SmartDashboard.putBoolean("B - Released",xbox.getBButtonReleased()); 
    SmartDashboard.putBoolean("X", xbox.getXButton()); 
    SmartDashboard.putBoolean("X - Pressed", xbox.getXButtonPressed()); 
    SmartDashboard.putBoolean("X - Released",xbox.getXButtonReleased()); 
    SmartDashboard.putBoolean("Y", xbox.getYButton());
    SmartDashboard.putBoolean("Y - Pressed", xbox.getYButtonPressed());
    SmartDashboard.putBoolean("Y - Released", xbox.getYButtonReleased()); 

    SmartDashboard.putBoolean("Stick - Left",  xbox.getStickButton(Hand.kLeft));
    SmartDashboard.putBoolean("Stick - Left-Pressed",  xbox.getStickButtonPressed(Hand.kLeft));
    SmartDashboard.putBoolean("Stick - Left-Released",  xbox.getStickButtonReleased(Hand.kLeft));
    SmartDashboard.putBoolean("Stick - Right", xbox.getStickButton(Hand.kRight));
    SmartDashboard.putBoolean("Stick - Right-Pressed", xbox.getStickButtonPressed(Hand.kRight));
    SmartDashboard.putBoolean("Stick - Right-Released", xbox.getStickButtonReleased(Hand.kRight)); 
    

    SmartDashboard.putBoolean("Back",  xbox.getBackButton());
    SmartDashboard.putBoolean("Back-Pressed",  xbox.getBackButtonPressed());  
    SmartDashboard.putBoolean("Back-Released",  xbox.getBackButtonReleased()); 
    SmartDashboard.putBoolean("Start", xbox.getStartButton());
    SmartDashboard.putBoolean("Start-Pressed", xbox.getStartButtonPressed());
    SmartDashboard.putBoolean("Start-Released", xbox.getStartButtonReleased());

    SmartDashboard.putBoolean("Limit-Switch", limitSwitch.get());
    
    xbox.setRumble(GenericHID.RumbleType.kLeftRumble,  SmartDashboard.getNumber("Rumble Left",  0.0));
    xbox.setRumble(GenericHID.RumbleType.kRightRumble, SmartDashboard.getNumber("Rumble Right", 0.0));
    // can't use XBox button, so no detector for that

    pollAxis(xbox);
    pollButtons(xbox);
  }
  
  private void pollAxis(XboxController controller) {
    for (int axis = 0; axis < controller.getAxisCount(); axis++) {
      String axisKey = String.format("Axis(%d)", axis);
      SmartDashboard.putNumber(axisKey, controller.getRawAxis(axis));
    }
  }

  private void pollButtons(XboxController controller) {
    for (int button = 1; button < controller.getButtonCount(); button++){
      String buttonKey = String.format("Buttong(%d)", button);
      SmartDashboard.putBoolean(buttonKey, controller.getRawButton(button));
    }
  }

  @Override
  public void testInit() {
      teleopInit();
  }

  @Override
  public void testPeriodic() {
      teleopPeriodic();
  } 
}
