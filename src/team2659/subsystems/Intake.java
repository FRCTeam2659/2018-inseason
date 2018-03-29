package team2659.subsystems;

import team2659.RobotMap;
import edu.wpi.first.wpilibj.PWMSpeedController;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem {

	PWMSpeedController intake = RobotMap.intakeSC;
	PWMSpeedController cheaterBar = RobotMap.cheaterBarSC;
	Solenoid cylinder = RobotMap.shooterCylinder;
	PowerDistributionPanel pdp = RobotMap.pdp;
	private double currentValue;
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	public void intake() {
		cheaterBar.set(1);
		intake.set(1);
	}
	public void outtake() {
		if (RobotMap.armFront.getSelectedSensorPosition(0) > -2000) {
			cheaterBar.set(-1);
			intake.set(-1);
		}
		else {	
			cheaterBar.set(-0.7);
			intake.set(-0.7);
		}
	}
	public void shoot() {
		outtake();
		if (RobotMap.armFront.getSelectedSensorPosition(0) > -2000) {
			Timer.delay(0.04);
			cylinder.set(true);
		}
	}
	public void retractCylinder() {
		cylinder.set(false);
	}
	public boolean isLoaded() {
		currentValue = pdp.getCurrent(8); //turn this to 8
		if (currentValue < 55)
			return false;
		return true;
	}
	public void stop() {
		intake.set(0);
		cheaterBar.set(0);
	}

}
