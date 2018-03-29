package team2659.commands.CommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import team2659.commands.DrivePath;
import team2659.commands.Fire;
import team2659.commands.Intake;
import team2659.commands.ResetPose;
import team2659.commands.SetArm;
import team2659.paths.RightRR1;
import team2659.paths.RightRL2;
import team2659.paths.RightRL3;
import team2659.paths.RightRL4;
import team2659.util.control.PathContainer;

public class rightRL extends CommandGroup {
	public rightRL() {
		PathContainer path = new RightRR1();
		addSequential(new ResetPose(path));
		addParallel(new SetArm(2));
		addSequential(new DrivePath(path));
		
		addSequential(new DrivePath(new RightRL2()));
		addSequential(new Fire());
		
		addParallel(new SetArm(0));
		addParallel(new DrivePath(new RightRL3()));
		addSequential(new Intake());
		addParallel(new SetArm(2));
		addSequential(new DrivePath(new RightRL4()));
		
		addSequential(new SetArm(4));
		addSequential(new Fire());
		addSequential(new SetArm(0));
	}
}
