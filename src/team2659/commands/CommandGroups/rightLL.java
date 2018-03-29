package team2659.commands.CommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import team2659.Constants;
import team2659.commands.DrivePath;
import team2659.commands.Fire;
import team2659.commands.Intake;
import team2659.commands.ResetPose;
import team2659.commands.SetArm;
import team2659.paths.RightLL2;
import team2659.paths.RightLL3;
import team2659.paths.RightLL4;
import team2659.paths.oneCubeScale.RightL;
import team2659.util.control.PathContainer;

public class rightLL extends CommandGroup {
	public rightLL() {
		PathContainer path = new RightL();
		addSequential(new ResetPose(path));
		addParallel(new SetArm(2));
		addSequential(new DrivePath(path));
		addSequential(new SetArm(3, Constants.arm2to3Timeout));
		addSequential(new Fire());
		
		addParallel(new SetArm(0));
		addSequential(new DrivePath(new RightLL2()));
		
		addParallel(new DrivePath(new RightLL3()));
		addSequential(new Intake());
		
		addParallel(new SetArm(2));
		addSequential(new DrivePath(new RightLL4()));
		addSequential(new SetArm(3, Constants.arm2to3Timeout));
		addSequential(new Fire());
		//addSequential(new SetArm(0));
	}
}
