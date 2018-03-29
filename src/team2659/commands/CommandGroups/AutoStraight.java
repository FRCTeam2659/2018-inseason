package team2659.commands.CommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import team2659.commands.DrivePath;
import team2659.commands.ResetPose;
import team2659.paths.StraightPath;
import team2659.util.control.PathContainer;

public class AutoStraight extends CommandGroup {
	public AutoStraight() {
		PathContainer path = new StraightPath();
		addSequential(new ResetPose(path));
		addSequential(new DrivePath(path));
	}
}
