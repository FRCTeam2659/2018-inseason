package team2659.commands.CommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import team2659.commands.DrivePath;
import team2659.commands.Fire;
import team2659.commands.Intake;
import team2659.commands.ResetPose;
import team2659.commands.SetArm;
import team2659.paths.RightR2;
import team2659.paths.RightR3;
import team2659.paths.RightR4;
import team2659.paths.RightR5;
import team2659.paths.RightR6;
import team2659.paths.RightR7;
import team2659.paths.oneCubeSwitch.RightR;
import team2659.util.control.PathContainer;

public class rightR extends CommandGroup {
	public rightR() {
		PathContainer path = new RightR();
		addSequential(new ResetPose(path));
		addParallel(new SetArm(2));
		addSequential(new DrivePath(path));
		addSequential(new Fire());
		addSequential(new DrivePath(new RightR2()));
		addParallel(new SetArm(0));
		addParallel(new DrivePath(new RightR3()));
		addSequential(new Intake());
		addSequential(new SetArm(2, 0.8));
		addSequential(new DrivePath(new RightR4()));
		addParallel(new Fire());
		addSequential(new DrivePath(new RightR5()));
		addParallel(new SetArm(0));
		addParallel(new DrivePath(new RightR6()));
		addSequential(new Intake());
		addSequential(new SetArm(2, 0.8));
		addSequential(new DrivePath(new RightR7()));
		addSequential(new Fire());
	}
}
