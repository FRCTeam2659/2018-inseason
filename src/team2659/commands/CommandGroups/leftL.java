package team2659.commands.CommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import team2659.commands.DrivePath;
import team2659.commands.Fire;
import team2659.commands.Intake;
import team2659.commands.ResetPose;
import team2659.commands.SetArm;
import team2659.paths.LeftL2;
import team2659.paths.LeftL3;
import team2659.paths.LeftL4;
import team2659.paths.LeftL5;
import team2659.paths.LeftL6;
import team2659.paths.LeftL7;
import team2659.paths.oneCubeSwitch.LeftL;
import team2659.util.control.PathContainer;

public class leftL extends CommandGroup {
	public leftL() {
		PathContainer path = new LeftL();
		addSequential(new ResetPose(path));
		addParallel(new SetArm(2));
		addSequential(new DrivePath(path));
		addSequential(new Fire());
		addSequential(new DrivePath(new LeftL2()));
		addParallel(new SetArm(0));
		addParallel(new DrivePath(new LeftL3()));
		addSequential(new Intake());
		addSequential(new SetArm(2, 0.8));
		addSequential(new DrivePath(new LeftL4()));
		addParallel(new Fire());
		addSequential(new DrivePath(new LeftL5()));
		addParallel(new SetArm(0));
		addParallel(new DrivePath(new LeftL6()));
		addSequential(new Intake());
		addSequential(new SetArm(2, 0.8));
		addSequential(new DrivePath(new LeftL7()));
		addSequential(new Fire());
		//addSequential(new Fire());
	}
}
