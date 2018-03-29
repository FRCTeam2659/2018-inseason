package team2659.commands.CommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import team2659.commands.DrivePath;
import team2659.commands.Fire;
import team2659.commands.Intake;
import team2659.commands.ResetPose;
import team2659.commands.SetArm;
import team2659.paths.LeftRR1;
import team2659.paths.LeftRR2;
import team2659.util.control.PathContainer;

public class leftRR extends CommandGroup {
	public leftRR() {
		PathContainer path = new LeftRR1();
		addSequential(new ResetPose(path));
		addParallel(new SetArm(2));
		addSequential(new DrivePath(path));
		addSequential(new SetArm(4));
		addSequential(new Fire());
		addSequential(new SetArm(0));
		
		addParallel(new DrivePath(new LeftRR2()));
		addSequential(new Intake());
		
		addSequential(new SetArm(2));
		addSequential(new Fire());
		addSequential(new SetArm(0));
	}
}
