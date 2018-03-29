package team2659.commands.CommandGroups.OneCudeSwitchAuto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import team2659.commands.DrivePath;
import team2659.commands.Fire;
import team2659.commands.ResetPose;
import team2659.commands.SetArm;
import team2659.paths.oneCubeSwitch.MidR;
import team2659.util.control.PathContainer;

public class OneCubeSwitchMidR extends CommandGroup {
	public OneCubeSwitchMidR() {
		PathContainer path = new MidR();
		addSequential(new ResetPose(path));
		addParallel(new SetArm(2));
		addSequential(new DrivePath(path));
		addSequential(new Fire());
		//addParallel(new SetArm(0));
	}
}
