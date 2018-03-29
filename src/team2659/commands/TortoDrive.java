package team2659.commands;

import edu.wpi.first.wpilibj.command.Command;

import team2659.Robot;
import team2659.TortoDriveHelper;

public class TortoDrive extends Command {
	
	private TortoDriveHelper myDrive = new TortoDriveHelper();
	
	public TortoDrive() {
		requires(Robot.drivetrain);
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		if (Robot.drivetrain.getDirection())
			Robot.drivetrain.setOpenLoop(myDrive.tortoDrive(-Robot.oi.driveStick.getRawAxis(1), -Robot.oi.driveStick.getRawAxis(2), true, Robot.drivetrain.isHighGear()));
		else
			Robot.drivetrain.setOpenLoop(myDrive.tortoDrive(Robot.oi.driveStick.getRawAxis(1), -Robot.oi.driveStick.getRawAxis(2), true, Robot.drivetrain.isHighGear()));
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}
}
