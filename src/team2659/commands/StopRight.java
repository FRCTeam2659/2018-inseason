package team2659.commands;

import edu.wpi.first.wpilibj.command.Command;

import team2659.Robot;

public class StopRight extends Command {

	public StopRight() {
		requires(Robot.lifter);
	}

	protected void initialize() {
		Robot.lifter.setRight(0);
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
