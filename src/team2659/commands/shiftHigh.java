package team2659.commands;
import team2659.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class shiftHigh extends Command {

    public shiftHigh() {
        requires(Robot.drivetrain);
    }

    protected void initialize() {
    		Robot.drivetrain.shiftHigh();
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
