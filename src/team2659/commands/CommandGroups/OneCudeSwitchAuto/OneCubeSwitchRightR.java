package team2659.commands.CommandGroups.OneCudeSwitchAuto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import team2659.commands.DrivePath;
import team2659.commands.Fire;
import team2659.commands.ResetPose;
import team2659.commands.SetArm;
import team2659.paths.oneCubeSwitch.RightR;
import team2659.util.control.PathContainer;

public class OneCubeSwitchRightR extends CommandGroup {
	public OneCubeSwitchRightR() {
		PathContainer path = new RightR();
		addSequential(new ResetPose(path));
		addParallel(new SetArm(2));
		addSequential(new DrivePath(path));
		addSequential(new Fire());
		//addSequential(new DrivePath(new RightR2()));
		//addSequential(new SetArm(0));
	}
}
