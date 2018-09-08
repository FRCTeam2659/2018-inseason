package team2659.commands.CommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import team2659.commands.DrivePath;
import team2659.commands.Fire;
import team2659.commands.Intake;
import team2659.commands.ResetPose;
import team2659.commands.SetArm;
import team2659.paths.MidR2;
import team2659.paths.MidR3;
import team2659.paths.MidR4;
import team2659.paths.MidR5;
import team2659.paths.MidR6;
import team2659.paths.oneCubeSwitch.MidR;
import team2659.util.control.PathContainer;

public class midRR extends CommandGroup {
	public midRR() {
		PathContainer path = new MidR();
		addSequential(new ResetPose(path));
		addParallel(new SetArm(2));
		addSequential(new DrivePath(path));
		
		addParallel(new Fire());
		addSequential(new DrivePath(new MidR2()));
		
		addParallel(new SetArm(0));
		addParallel(new DrivePath(new MidR3()));
		addSequential(new Intake());
		
		addSequential(new DrivePath(new MidR4()));
		addParallel(new SetArm(2));
		addSequential(new DrivePath(new MidR5()));
		
		addParallel(new Fire());
		addSequential(new DrivePath(new MidR2()));
		
		addParallel(new SetArm(7));
		addParallel(new DrivePath(new MidR6()));
		addSequential(new Intake());
		
		addSequential(new DrivePath(new MidR4()));
		addParallel(new SetArm(2));
		addSequential(new DrivePath(new MidR5()));
		addSequential(new Fire());
	}
}
