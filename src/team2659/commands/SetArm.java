package team2659.commands;

import edu.wpi.first.wpilibj.command.Command;
import team2659.Robot;

public class SetArm extends Command {
	private int mLevel;//, lastLevel;
	private double timeout = 0.2;
	//private boolean portalHeight = false;
	public SetArm(int stage) {
		requires(Robot.arm);
		mLevel = stage;
	}
	public SetArm(int stage, double timeout) {
		this(stage);
		this.timeout = timeout;
	}

	protected void initialize() {
		setTimeout(timeout);
		Robot.arm.setStage(mLevel);
		/*if (mLevel == 2 && lastLevel == 2) {
			if (portalHeight) {
				Robot.arm.setStage(6);
				portalHeight = !portalHeight;
			}
			else {
				Robot.arm.setStage(2);
				portalHeight = !portalHeight;
			}
		}
		else {
			Robot.arm.setStage(mLevel);
			portalHeight = false;
		}
		lastLevel = mLevel;*/
	}

	protected void execute() {

	}

	protected boolean isFinished() {
		return isTimedOut();
	}

	protected void end() {

	}

	protected void interrupted() {
	}
}
