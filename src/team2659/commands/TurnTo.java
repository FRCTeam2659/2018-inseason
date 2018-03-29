package team2659.commands;
import team2659.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnTo extends Command {
	private int mDegree;
    public TurnTo(int degree) {
        requires(Robot.drivetrain);
        mDegree = degree;
    }

    protected void initialize() {
    	Robot.drivetrain.rotate(mDegree);
    }

    protected void execute() {

    }

    protected boolean isFinished() {
        return Robot.drivetrain.isRotateFinished(mDegree);
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
