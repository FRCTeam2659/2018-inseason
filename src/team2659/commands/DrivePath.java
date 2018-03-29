package team2659.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

import team2659.Robot;
import team2659.RobotStateEstimator;
import team2659.util.control.PathContainer;

public class DrivePath extends Command {
	private PathContainer mPath;

	public DrivePath(PathContainer path) {
		requires(Robot.drivetrain);
		mPath = path;
	}

	@Override
	protected void initialize() {
		Robot.drivetrain.setWantDrivePath(mPath.buildPath(), mPath.isReversed());
		RobotStateEstimator.getInstance().onStart(Timer.getFPGATimestamp());
	}

	@Override
	protected void execute() {
		double timestamp = Timer.getFPGATimestamp();
		RobotStateEstimator.getInstance().onLoop(timestamp);
		Robot.drivetrain.updatePathFollower(timestamp);
	}

	@Override
	protected boolean isFinished() {
		return Robot.drivetrain.isDoneWithPath();
	}

	@Override
	protected void end() {
		Robot.drivetrain.stop();
		//Robot.intake.stop();
	}

	@Override
	protected void interrupted() {
	}
}
