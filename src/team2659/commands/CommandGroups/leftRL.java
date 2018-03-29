package team2659.commands.CommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import team2659.commands.DrivePath;
import team2659.commands.Fire;
import team2659.commands.Intake;
import team2659.commands.ResetPose;
import team2659.commands.SetArm;
import team2659.paths.LeftLL1;
import team2659.paths.LeftLL2;
import team2659.paths.LeftRL3;
import team2659.paths.LeftRL4;
import team2659.util.control.PathContainer;

public class leftRL extends CommandGroup {
	public leftRL() {
		PathContainer path = new LeftLL1();
		addSequential(new ResetPose(path));
		addParallel(new SetArm(4));
		addSequential(new DrivePath(path));
		addSequential(new Fire());
		addSequential(new SetArm(0));
		addParallel(new DrivePath(new LeftLL2()));
		addSequential(new Intake());
		addSequential(new DrivePath(new LeftRL3()));
		addParallel(new SetArm(2));
		addSequential(new DrivePath(new LeftRL4()));
		addSequential(new Fire());
		addSequential(new SetArm(0));
	}
}
