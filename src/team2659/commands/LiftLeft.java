package team2659.commands;

import edu.wpi.first.wpilibj.command.Command;

import team2659.Robot;

public class LiftLeft extends Command {

	public LiftLeft() {
		requires(Robot.lifter);
	}

	protected void initialize() {
		
	}

	protected void execute() {
		if (Robot.lifter.getAcutated())
			Robot.lifter.setLeft(1);
	}

	protected boolean isFinished() {
		return true;
	}

	protected void end() {

	}

	protected void interrupted() {
	}
}
