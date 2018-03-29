package team2659.subsystems;

import edu.wpi.first.wpilibj.PWMSpeedController;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import team2659.RobotMap;

public class Lifter extends Subsystem {
	
	Solenoid liftCylinder = RobotMap.liftCylinder;
	//VictorSPX leftWing = RobotMap.lifterLeft;
	//VictorSPX rightWing = RobotMap.lifterRight;
	PWMSpeedController leftWing = RobotMap.lifterLeft;
	PWMSpeedController rightWing = RobotMap.lifterRight;
	boolean isActuated = false;
	
	@Override
	protected void initDefaultCommand() {
	}
	public void actuatePlatforms() {
		isActuated = true;
		liftCylinder.set(true);
		leftWing.set(.4);
		rightWing.set(.4);
		Timer.delay(.6);
		disable();
		//liftCylinder.set(false);
	}
	public void setLeft(double power) {
		if (isActuated)
			leftWing.set(power);
	}
	public void setRight(double power) {
		if (isActuated)
			rightWing.set(power);
	}
	public boolean getAcutated() {
		return isActuated;
	}
	public void disable() {
		leftWing.set(0);
		rightWing.set(0);
	}
}
