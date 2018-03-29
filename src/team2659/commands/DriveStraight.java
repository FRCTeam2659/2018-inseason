package team2659.commands;
import team2659.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class DriveStraight extends Command {
	private int distance;
    public DriveStraight(int inches) {
        requires(Robot.drivetrain);
        distance = inches;
    }

    protected void initialize() {
    	Robot.drivetrain.updatePositionSetpoint(distance, distance);
    }

    protected void execute() {

    }

    protected boolean isFinished() {
        return Robot.drivetrain.isDriveFinished(distance);
    }

    protected void end() {
    	Robot.intake.stop();
    }

    protected void interrupted() {
    }
}
