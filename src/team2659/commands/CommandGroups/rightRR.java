package team2659.commands.CommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import team2659.Constants;
import team2659.commands.DrivePath;
import team2659.commands.Fire;
import team2659.commands.Intake;
import team2659.commands.ResetPose;
import team2659.commands.SetArm;
import team2659.paths.RightRR2;
import team2659.paths.RightRR3;
import team2659.paths.RightRR4;
import team2659.paths.oneCubeScale.RightR;
import team2659.util.control.PathContainer;

public class rightRR extends CommandGroup {
	public rightRR() {
		/*PathContainer path = new RightRR1();
		addSequential(new ResetPose(path));
		addParallel(new SetArm(2));
		addSequential(new DrivePath(path));
		addSequential(new SetArm(4, Constants.arm2to4Timeout));
		addSequential(new Fire());
		addSequential(new SetArm(0, Constants.arm4to0Timeout));
		
		addParallel(new DrivePath(new RightRR2()));
		addSequential(new Intake());
		
		addSequential(new SetArm(2, Constants.arm0to2Timeout));
		addSequential(new Fire());*/
		//addSequential(new SetArm(0));
		PathContainer path = new RightR();
		addSequential(new ResetPose(path));
		addParallel(new SetArm(3));
		addSequential(new DrivePath(path));
		//addSequential(new SetArm(3, Constants.arm2to3Timeout));
		addSequential(new Fire());
		
		addParallel(new SetArm(0));
		addSequential(new DrivePath(new RightRR2()));	
		
		addParallel(new DrivePath(new RightRR3()));
		addSequential(new Intake());
		
		addParallel(new SetArm(2));
		addSequential(new DrivePath(new RightRR4()));
		addSequential(new SetArm(3, Constants.arm2to3Timeout));

		addSequential(new Fire());
	}
}
