package team2659.commands.CommandGroups.OneCubeScaleAuto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import team2659.commands.DrivePath;
import team2659.commands.ResetPose;
import team2659.commands.SetArm;
import team2659.paths.oneCubeScale.LeftR;
import team2659.util.control.PathContainer;

public class OneCubeScaleLeftR extends CommandGroup {
	public OneCubeScaleLeftR() {
		PathContainer path = new LeftR();
		addSequential(new ResetPose(path));
		addParallel(new SetArm(2));
		addSequential(new DrivePath(path));
		//addSequential(new Fire());
	}
}
