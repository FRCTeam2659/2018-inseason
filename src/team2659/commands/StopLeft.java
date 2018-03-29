package team2659.commands;

import edu.wpi.first.wpilibj.command.Command;

import team2659.Robot;

public class StopLeft extends Command {

	public StopLeft() {
		requires(Robot.lifter);
	}

	protected void initialize() {
		Robot.lifter.setLeft(0);
	}

	protected void execute() {
		
	}

	protected boolean isFinished() {
		return true;
	}

	protected void end() {

	}

	protected void interrupted() {
	}
}
