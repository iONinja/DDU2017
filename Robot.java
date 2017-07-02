package org.usfirst.frc.team4729.robot;

import com.ctre.CANTalon;

import com.mindsensors.CANLight;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;


public class Robot extends IterativeRobot {

    Joystick joyLeft = new Joystick(0);
    Joystick joyRight = new Joystick(1);
    Timer timer = new Timer();

    CANTalon driveTrainLeftFront = new CANTalon(3);
    CANTalon driveTrainLeftBack = new CANTalon(4);
    CANTalon driveTrainRightFront = new CANTalon(1);
    CANTalon driveTrainRightBack = new CANTalon(2);

    Talon climber = new Talon(0);

    CANLight lights = new CANLight(5);

    int driveMode = 1; // 0 = tank, 1 = 2 stick arcade // Please use constants

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
    }

    /**
     * This function is run once each time the robot enters autonomous mode
     */
    @Override
    public void autonomousInit() {
        timer.reset();
        timer.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    @Override
    public void autonomousPeriodic() {

    }

    /**
     * This function is called once each time the robot enters tele-operated
     * mode
     */
    @Override
    public void teleopInit() {
        lights.showRGB(255, 0, 255);
    }

    /**
     * This function is called periodically during operator control
     */
    @Override
    public void teleopPeriodic() {
        // Select drive mode
        if (joyLeft.getRawButton(8) || joyRight.getRawButton(8)) {
            driveMode = 0;
        } else if (joyLeft.getRawButton(9) || joyRight.getRawButton(9)) {
            driveMode = 1;
        }
        // Lights
        if (joyLeft.getRawButton(10) || joyRight.getRawButton(10)) {
            lights.showRGB(255, 0, 0);
        } else if (joyLeft.getRawButton(11) || joyRight.getRawButton(11)) {
            lights.showRGB(0, 0, 255);
        }

        if (joyLeft.getRawButton(6) || joyRight.getRawButton(6)) {
            climber.set(6);
        } else if (joyLeft.getRawButton(7) || joyRight.getRawButton(7)) {
            climber.set(0.25);
            lights.showRGB(0, 0, 0);
        } else {
            climber.set(0);
        }

        if (driveMode == 0) {
            // TANK DRIVE
            if(Math.abs(joyLeft.getY()) > 0.1) {
                driveTrainLeftFront.set(-joyLeft.getY());
                driveTrainLeftBack.set(-joyLeft.getY());
            } else {
                driveTrainLeftFront.set(0);
                driveTrainLeftBack.set(0);
            }
            if(Math.abs(joyRight.getY()) > 0.1) {
                driveTrainRightFront.set(joyRight.getY());
                driveTrainRightBack.set(joyRight.getY());
            } else {
                driveTrainRightFront.set(0);
                driveTrainRightBack.set(0);
            }
        } else if (driveMode == 1) {
            // 2 STICK ARCADE
            double forward = 0;
            double turn = 0;

            double finalLeft = 0;
            double finalRight = 0;

            if (Math.abs(joyLeft.getY()) > 0.1) {
                forward = joyLeft.getY();
            }

            if (Math.abs(joyRight.getX()) > 0.1) {
                turn = joyRight.getX();
            }

            finalLeft = forward - turn;
            finalRight = forward + turn;

            if (finalLeft > 1) {
                finalLeft = 1;
            } else if (finalLeft < -1) {
                finalLeft = -1;
            }

            if (finalRight > 1) {
                finalRight = 1;
            } else if (finalRight < -1) {
                finalRight = -1;
            }

            driveTrainLeftFront.set(-finalLeft);
            driveTrainLeftBack.set(-finalLeft);
            driveTrainRightFront.set(finalRight);
            driveTrainRightBack.set(finalRight);
        }
    }

    /**
     * This function is called periodically during test mode
     */
    @Override
    public void testPeriodic() {
        LiveWindow.run();
    }
}
