package team2659.commands.CommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import team2659.commands.DrivePath;
import team2659.commands.Fire;
import team2659.commands.Intake;
import team2659.commands.ResetPose;
import team2659.commands.SetArm;
import team2659.paths.LeftLL1;
import team2659.paths.LeftLR2;
import team2659.paths.LeftLR3;
import team2659.paths.LeftLR4;
import team2659.util.control.PathContainer;

public class leftLR extends CommandGroup {
	public leftLR() {
		PathContainer path = new LeftLL1();
		addSequential(new ResetPose(path));
		addParallel(new SetArm(2));
		addSequential(new DrivePath(path));
		
		addSequential(new Fire());
		addSequential(new DrivePath(new LeftLR2()));
		
		addParallel(new SetArm(0));
		addParallel(new DrivePath(new LeftLR3()));
		addSequential(new Intake());
		addParallel(new SetArm(2));
		addSequential(new DrivePath(new LeftLR4()));
		
		addSequential(new SetArm(4));
		addSequential(new Fire());
		addSequential(new SetArm(0));
	}
}
