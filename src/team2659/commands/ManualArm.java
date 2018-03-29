package team2659.commands;

import edu.wpi.first.wpilibj.command.Command;

import team2659.Robot;

public class ManualArm extends Command {
	
	public ManualArm() {
		requires(Robot.arm);
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		if (Robot.arm.getArmMode())
			Robot.arm.set(-Robot.oi.operatorStick.getRawAxis(1)*0.8);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}
}
