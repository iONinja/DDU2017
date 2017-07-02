package org.usfirst.frc.team4729.robot.subsystems;

import org.usfirst.frc.team4729.robot.Robot;
import org.usfirst.frc.team4729.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */

public class extends Command {
	Timer timer;
	String mode;
	int TIME;
	double SPEED;

	CANTalon driveTrainLeftFront = new CANTalon(RobotMap.LEFT_FRONT_DRIVE);
    CANTalon driveTrainLeftBack = new CANTalon(RobotMap.LEFT_BACK_DRIVE);
    CANTalon driveTrainRightFront = new CANTalon(RobotMap.RIGHT_FRONT_DRIVE);
	CANTalon driveTrainRightBack = new CANTalon(RobotMap.RIGHT_BACK_DRIVE);

    public AutoDeliverGear(String m) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);

    	mode = m;

    	//TODO: change these
    	TIME = 2;
    	SPEED = 1;

    	driveTrainLeftFront.changeControlMode(TalonControlMode.PercentVbus);
    	driveTrainLeftBack.changeControlMode(TalonControlMode.PercentVbus);
    	driveTrainRightFront.changeControlMode(TalonControlMode.PercentVbus);
		driveTrainRightBack.changeControlMode(TalonControlMode.PercentVbus);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	int time = TIME;
    	double left = 0;
    	double right = 0;

    	// Set speed of each side based on mode (TODO)
    	if (mode == "straight") {
    		// WIP (currently moving forward)
    		left = SPEED;
    		right = SPEED;
    	}

    	// Start moving
    	driveTrainLeftFront.set(left);
    	driveTrainLeftBack.set(left);
    	driveTrainRightFront.set(right);
		driveTrainRightBack.set(right);

		while (timer.get() < time) {
			// Wait...
		}

		// Stop
		driveTrainLeftFront.set(0);
    	driveTrainLeftBack.set(0);
    	driveTrainRightFront.set(0);
		driveTrainRightBack.set(0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
