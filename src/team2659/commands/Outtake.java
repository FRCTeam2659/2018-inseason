package team2659.commands;

import edu.wpi.first.wpilibj.command.Command;

import team2659.Robot;

public class Outtake extends Command {

	public Outtake() {
		requires(Robot.intake);
	}

	protected void initialize() {
		Robot.intake.outtake();
		setTimeout(0.8);
	}

	protected void execute() {
		
	}

	protected boolean isFinished() {
		return isTimedOut();
	}

	protected void end() {
		Robot.intake.stop();
		//Robot.intake.retractCylinder();
	}

	protected void interrupted() {
	}
}
