package team2659.commands.CommandGroups.OneCudeSwitchAuto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import team2659.commands.DrivePath;
import team2659.commands.Fire;
import team2659.commands.ResetPose;
import team2659.commands.SetArm;
import team2659.paths.oneCubeSwitch.LeftL;
import team2659.util.control.PathContainer;

public class OneCubeSwitchLeftL extends CommandGroup {
	public OneCubeSwitchLeftL() {
		PathContainer path = new LeftL();
		addSequential(new ResetPose(path));
		addParallel(new SetArm(2));
		addSequential(new DrivePath(path));
		addSequential(new Fire());
	}
}
