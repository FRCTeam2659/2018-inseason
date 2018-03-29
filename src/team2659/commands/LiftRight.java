package team2659.commands;

import edu.wpi.first.wpilibj.command.Command;

import team2659.Robot;

public class LiftRight extends Command {

	public LiftRight() {
		requires(Robot.lifter);
	}

	protected void initialize() {
		
	}

	protected void execute() {
		if (Robot.lifter.getAcutated())
			Robot.lifter.setRight(1);
	}

	protected boolean isFinished() {
		return true;
	}

	protected void end() {
	}

	protected void interrupted() {
	}
}
