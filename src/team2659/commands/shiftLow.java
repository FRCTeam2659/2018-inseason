package team2659.commands;
import team2659.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class shiftLow extends Command {

    public shiftLow() {
        requires(Robot.drivetrain);
    }

    protected void initialize() {
    		Robot.drivetrain.shiftLow();
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
