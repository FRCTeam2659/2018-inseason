package team2659.commands.CommandGroups.OneCubeScaleAuto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import team2659.Constants;
import team2659.commands.DrivePath;
import team2659.commands.Fire;
import team2659.commands.ResetPose;
import team2659.commands.SetArm;
import team2659.paths.oneCubeScale.RightL;
import team2659.util.control.PathContainer;

public class OneCubeScaleRightL extends CommandGroup {
	public OneCubeScaleRightL() {
		PathContainer path = new RightL();
		addSequential(new ResetPose(path));
		addParallel(new SetArm(2));
		addSequential(new DrivePath(path));
		addSequential(new SetArm(3, Constants.arm2to3Timeout));
		addSequential(new Fire());
		//addParallel(new SetArm(0));
	}
}
