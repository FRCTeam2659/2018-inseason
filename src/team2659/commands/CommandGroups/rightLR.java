package team2659.commands.CommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import team2659.Constants;
import team2659.commands.DrivePath;
import team2659.commands.Fire;
import team2659.commands.Intake;
import team2659.commands.ResetPose;
import team2659.commands.SetArm;
import team2659.paths.RightRR1;
import team2659.paths.RightRR3;
import team2659.paths.RightLR3;
import team2659.paths.RightLR4;
import team2659.util.control.PathContainer;

public class rightLR extends CommandGroup {
	public rightLR() {
		PathContainer path = new RightRR1();
		addSequential(new ResetPose(path));
		addParallel(new SetArm(2));
		addSequential(new DrivePath(path));
		addSequential(new SetArm(4, Constants.arm2to4Timeout));
		addSequential(new Fire());
		addSequential(new SetArm(0, Constants.arm4to0Timeout));
		addParallel(new DrivePath(new RightRR3()));
		addSequential(new Intake());
		
		addSequential(new DrivePath(new RightLR3()));
		addParallel(new SetArm(2, Constants.arm0to2Timeout));
		addSequential(new DrivePath(new RightLR4()));
		addSequential(new Fire());
		//addSequential(new SetArm(0));
	}
}
