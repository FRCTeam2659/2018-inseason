package team2659.subsystems;

import edu.wpi.first.wpilibj.PWMSpeedController;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import team2659.RobotMap;

public class Climber extends Subsystem {
	
	Solenoid liftCylinder = RobotMap.liftCylinder;
	//VictorSPX SC = RobotMap.lifterLeft;
	PWMSpeedController SC = RobotMap.climber;
	//VictorSPX leftWing = RobotMap.lifterLeft;
	//VictorSPX rightWing = RobotMap.lifterRight;
	//PWMSpeedController leftWing = RobotMap.lifterLeft;
	//PWMSpeedController rightWing = RobotMap.lifterRight;
	
	boolean isActuated = false;
	
	@Override
	protected void initDefaultCommand() {
	}
	public void activate() {
		if (!isActuated) {
			isActuated = true;
			set(-0.3);
			Timer.delay(0.15);
			set(0);
			Timer.delay(.1);
			set(1);
			Timer.delay(2.4);
			set(0);
		}
		/*liftCylinder.set(true);
		leftWing.set(.4);
		rightWing.set(.4);
		Timer.delay(.6);
		disable();
		//liftCylinder.set(false);*/
	}
	public void set(double power) {
		//if (isActuated)
		
			SC.set(power);
	}
	/*public void up() {
		if (isActuated)
			SC.set(ControlMode.PercentOutput, 1);
	}
	public void down() {
		if (isActuated)
			SC.set(ControlMode.PercentOutput, -1);
	}*/
	public boolean getAcutated() {
		return isActuated;
	}
	public void disable() {
		SC.set(0);
	}
}
