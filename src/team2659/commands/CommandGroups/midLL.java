package team2659.commands.CommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import team2659.commands.DrivePath;
import team2659.commands.Fire;
import team2659.commands.Intake;
import team2659.commands.ResetPose;
import team2659.commands.SetArm;
import team2659.paths.MidL2;
import team2659.paths.MidL3;
import team2659.paths.MidL4;
import team2659.paths.MidL5;
import team2659.paths.oneCubeSwitch.MidL;
import team2659.util.control.PathContainer;

public class midLL extends CommandGroup {
	public midLL() {
		PathContainer path = new MidL();
		addSequential(new ResetPose(path));
		addParallel(new SetArm(2));
		addSequential(new DrivePath(path));
		addSequential(new Fire());
		addParallel(new SetArm(0));
		
		addSequential(new DrivePath(new MidL2()));
		
		addParallel(new DrivePath(new MidL3()));
		addSequential(new Intake());
		addSequential(new DrivePath(new MidL4()));
		addParallel(new SetArm(2));
		addSequential(new DrivePath(new MidL5()));
		addSequential(new Fire());
	}
}
