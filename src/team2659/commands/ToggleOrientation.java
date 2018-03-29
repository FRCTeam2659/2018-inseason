package team2659.commands;

import edu.wpi.first.wpilibj.command.Command;

import team2659.Robot;

public class ToggleOrientation extends Command {
	
	public ToggleOrientation() {
		requires(Robot.drivetrain);
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		Robot.drivetrain.toggleDirection();
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}
}
