package team2659.commands;

import team2659.Robot;
import team2659.RobotState;
import team2659.util.control.PathContainer;
import team2659.util.math.RigidTransform2d;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class ResetPose extends Command {
	protected PathContainer mPathContainer;
    public ResetPose(PathContainer path) {
        requires(Robot.drivetrain);
        mPathContainer = path;
    }

    protected void initialize() {
    	Robot.arm.setStage(8);
    	Timer.delay(0.18);
    	Robot.arm.setStage(3);
    	RigidTransform2d startPose = mPathContainer.getStartPose();
        RobotState.getInstance().reset(Timer.getFPGATimestamp(), startPose);
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
