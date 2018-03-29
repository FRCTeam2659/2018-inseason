package team2659.commands;

import edu.wpi.first.wpilibj.command.Command;

import team2659.Robot;
import team2659.RobotMap;

public class StopIntake extends Command {

	public StopIntake() {
		requires(Robot.intake);
	}

	protected void initialize() {
		if (RobotMap.armFront.getSelectedSensorPosition(0) < -16000)
			Robot.arm.setStage(1);
		Robot.intake.stop();
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
