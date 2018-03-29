package team2659.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import team2659.RobotMap;
import team2659.commands.ManualArm;

public class Arm extends Subsystem {

	public TalonSRX SC = RobotMap.armFront;
	private boolean manualArm = false;
	private int prevArmStage;
	
	public Arm() {
		SC.config_kP(0, 0.2, 0);
		SC.config_kI(0, 0, 0);
		SC.config_kD(0, 0.01, 0);
		SC.config_kF(0, 0.01, 0);
	}
	@Override
	protected void initDefaultCommand() {
		new ManualArm();
	}
	
	public void set(double percentOutput) {
		SC.set(ControlMode.PercentOutput, percentOutput);
	}

	public void setStage(int stage) {
		if (stage == 0)
			SC.set(ControlMode.Position, degreeToEncoderSamples(-84));
		else if (stage == 1)
			SC.set(ControlMode.Position, degreeToEncoderSamples(-72));
		else if (stage == 2)
			SC.set(ControlMode.Position, degreeToEncoderSamples(-36));
		else if (stage == 3)
			SC.set(ControlMode.Position, degreeToEncoderSamples(0));
		else if (stage == 4)
			SC.set(ControlMode.Position, degreeToEncoderSamples(31));
		else if (stage == 5)
			SC.set(ControlMode.Position, degreeToEncoderSamples(10));
		else if (stage == 6)
			SC.set(ControlMode.Position, degreeToEncoderSamples(-50));
		prevArmStage = stage;
	}
	public void toggleArmMode() {
		manualArm = !manualArm;
	}
	public boolean getArmMode() {
		return manualArm;
	}
	public int getPrevArmStage() {
		return prevArmStage;
	}
	public boolean isFinished(int target) {
		if (Math.abs(SC.getSelectedSensorPosition(0)-degreeToEncoderSamples(target)) <= 1000) {
			return true;
		}
		else
			return false;
	}
	private double degreeToEncoderSamples(int degree) {
		return degree*239; //239 = 4096*21/360
	}
}
