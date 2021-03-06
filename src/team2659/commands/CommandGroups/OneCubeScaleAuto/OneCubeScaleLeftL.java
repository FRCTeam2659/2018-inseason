package team2659.commands.CommandGroups.OneCubeScaleAuto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import team2659.commands.DrivePath;
import team2659.commands.Fire;
import team2659.commands.ResetPose;
import team2659.commands.SetArm;
import team2659.paths.oneCubeScale.LeftL;
import team2659.paths.oneCubeScale.LeftL2;
import team2659.util.control.PathContainer;

public class OneCubeScaleLeftL extends CommandGroup {
	public OneCubeScaleLeftL() {
		PathContainer path = new LeftL();
		addSequential(new ResetPose(path));
		addParallel(new SetArm(5));//use 3
		addSequential(new DrivePath(path));
		addSequential(new Fire());
		addSequential(new DrivePath(new LeftL2()));
		
		addSequential(new SetArm(1));
	}
}
