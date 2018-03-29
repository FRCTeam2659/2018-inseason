package team2659;

import team2659.util.math.Rotation2d;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.VelocityMeasPeriod;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PWMSpeedController;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.VictorSP;

public class RobotMap {
	//Speed controllers 
	public static TalonSRX leftMaster, rightMaster, leftSlave, rightSlave, leftSlave2, rightSlave2, armFront, armRear;
	public static PWMSpeedController intakeSC, cheaterBarSC, lifterLeft, lifterRight;
	
	//Pneumatics 
	public static Solenoid liftCylinder, shooterCylinder, shooterCylinder2, shiftCylinder;
	
	//sensors
	public static ADXRS450_Gyro gyro;
	public static DigitalInput frontSwitch, backSwitch;
	public static PowerDistributionPanel pdp;
	public static UsbCamera myCamera;
	
	public static void init() {
		//drive
		leftMaster = new TalonSRX(3);
		leftMaster.set(ControlMode.PercentOutput, 0);
	    leftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
	    leftMaster.setSensorPhase(false);
	    leftMaster.setInverted(true); //!!! reverse it to true before comp

	    leftSlave = new TalonSRX(4);
	    leftSlave.set(ControlMode.Follower, 3);
	    leftSlave.setInverted(true);
	    leftSlave2 = new TalonSRX(5);
	    leftSlave2.set(ControlMode.Follower, 3);
	    leftSlave2.setInverted(true);
	    leftMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, 0);
	    leftMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, 0);
	    
	    rightMaster = new TalonSRX(0);
		rightMaster.set(ControlMode.PercentOutput, 0);
	    rightMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
	    rightMaster.setSensorPhase(false);
	    rightMaster.setInverted(false);

	    rightSlave = new TalonSRX(1);
	    rightSlave.set(ControlMode.Follower, 0);
	    rightSlave.setInverted(false);
	    rightSlave2 = new TalonSRX(2);
	    rightSlave2.set(ControlMode.Follower, 0);
	    rightSlave2.setInverted(false);
	    rightMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, 0);
	    rightMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, 0);
	    
	    leftMaster.configVelocityMeasurementPeriod(VelocityMeasPeriod.Period_10Ms, 0);
	    leftMaster.configVelocityMeasurementWindow(32, 0);
	    rightMaster.configVelocityMeasurementPeriod(VelocityMeasPeriod.Period_10Ms, 0);
	    rightMaster.configVelocityMeasurementWindow(32, 0);
	    
	    
	    //arm
	    armFront = new TalonSRX(6);
	    armFront.setInverted(true);
	    armFront.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
	    armFront.setNeutralMode(NeutralMode.Brake);
	    armFront.configClosedloopRamp(0.2, 0);
	    armFront.configPeakOutputForward(1, 0);
	    armFront.configPeakOutputReverse(-0.75, 0);
	    armFront.setSensorPhase(true);
	    
	    
	    armRear = new TalonSRX(7);
	    armRear.setInverted(true);
	    armRear.setNeutralMode(NeutralMode.Brake);
	    armRear.set(ControlMode.Follower, 6);
	    
	    //intake
	    intakeSC = new VictorSP(0);
	    intakeSC.setInverted(true);
	    cheaterBarSC = new VictorSP(1);
	    cheaterBarSC.setInverted(true);
	    
	    //lift
	    lifterLeft = new VictorSP(2);//for compbot
	    lifterRight = new VictorSP(3);
	    //lifterLeft = new VictorSPX(0);//for practicebot
	    //lifterRight = new VictorSPX(1);
	    
	    //pneumatics
	    shiftCylinder = new Solenoid(0);
	    liftCylinder = new Solenoid(2);
	    shooterCylinder = new Solenoid(1);
	    
	    gyro = new ADXRS450_Gyro();
	    frontSwitch = new DigitalInput(0);
	    backSwitch = new DigitalInput(1);
	    pdp = new PowerDistributionPanel();
	    
	    myCamera = CameraServer.getInstance().startAutomaticCapture(0);
	    myCamera.setExposureAuto();
	    myCamera.setResolution(480, 360);
	}
    
	public static Rotation2d getGyroAngle() {
		Rotation2d mAngleAdjustment = Rotation2d.identity();
		return mAngleAdjustment.rotateBy(Rotation2d.fromDegrees(-gyro.getAngle()));
	}
}
