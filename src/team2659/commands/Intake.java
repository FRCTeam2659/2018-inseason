package team2659.commands;

import edu.wpi.first.wpilibj.command.Command;

import team2659.Robot;
import team2659.RobotMap;

public class Intake extends Command {

	public Intake() {
		requires(Robot.intake);
		//requires(Robot.arm);
	}

	protected void initialize() {
		if (RobotMap.armFront.getSelectedSensorPosition(0) < -16000)
			Robot.arm.setStage(0);
	}

	protected void execute() {
		Robot.intake.intake();
	}

	protected boolean isFinished() {
		return Robot.intake.isLoaded();
	}

	protected void end() {
		Robot.intake.stop();
	}

	protected void interrupted() {
	}
}
