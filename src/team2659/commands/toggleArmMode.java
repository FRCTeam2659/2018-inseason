package team2659.commands;

import edu.wpi.first.wpilibj.command.Command;

import team2659.Robot;

public class toggleArmMode extends Command {

	public toggleArmMode() {
		requires(Robot.arm);
	}

	protected void initialize() {
	}

	protected void execute() {	
		Robot.arm.toggleArmMode();
	}

	protected boolean isFinished() {
		return true;
	}

	protected void end() {
	}

	protected void interrupted() {
	}
}
