package team2659.subsystems;

import team2659.util.DriveSignal;

import team2659.Kinematics;
import team2659.RobotState;
import team2659.util.math.RigidTransform2d;
import team2659.util.math.Rotation2d;
import team2659.util.math.Twist2d;
import team2659.util.control.PathFollower;
import team2659.util.control.Lookahead;
import team2659.util.control.Path;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import team2659.Constants;
import team2659.RobotMap;
import team2659.commands.TortoDrive;

public class Drivetrain extends Subsystem {
	//IOs
	public TalonSRX mLeftMaster = RobotMap.leftMaster,
			mRightMaster = RobotMap.rightMaster,
			mLeftSlave = RobotMap.leftSlave,
			mRightSlave = RobotMap.rightSlave,
			mLeftSlave2 = RobotMap.leftSlave2,
			mRightSlave2 = RobotMap.rightSlave2;
	static ADXRS450_Gyro gyro = RobotMap.gyro;
	Solenoid shiftCylinder = RobotMap.shiftCylinder;
	
	//variables
	DriveControlState mDriveControlState;
	boolean mIsBrakeMode;
	boolean isHighGear = true;
	static boolean isReversed;
	boolean trigger = false;
	boolean forwardDirection = true;
	RobotState mRobotState = RobotState.getInstance();
    PathFollower mPathFollower;
    Path mCurrentPath = null;

	public Drivetrain() {
		reloadGains();
	}
	
	public void initDefaultCommand() {
		setDefaultCommand(new TortoDrive());
	}
	
	public enum DriveControlState {
		 OPEN_LOOP, // open loop voltage control
	     VELOCITY_SETPOINT, // velocity PID control
	     PATH_FOLLOWING, // used for autonomous driving
	     TURN_TO_HEADING, // turn in place
	     POSITION_SETPOINT
	}
	
	
	//open loop control
	public synchronized void setOpenLoop(DriveSignal signal) {
        if (mDriveControlState != DriveControlState.OPEN_LOOP) {
        	mLeftMaster.configNominalOutputForward(0, 0);
            mLeftMaster.configNominalOutputReverse(0, 0);
            mRightMaster.configNominalOutputForward(0, 0);
            mRightMaster.configNominalOutputReverse(0, 0);
            mDriveControlState = DriveControlState.OPEN_LOOP;
            setBrakeMode(false);
        }
        // Right side is reversed, but reverseOutput doesn't invert PercentVBus.
        // So set negative on the right master.
        mRightMaster.set(ControlMode.PercentOutput, signal.getRight());
        mLeftMaster.set(ControlMode.PercentOutput, signal.getLeft());
    }
	public synchronized void setBrakeMode(boolean on) {
		mIsBrakeMode = on;
        if (on) {
            mRightMaster.setNeutralMode(NeutralMode.Brake);
            mRightSlave.setNeutralMode(NeutralMode.Brake);
            mRightSlave2.setNeutralMode(NeutralMode.Brake);
            mLeftMaster.setNeutralMode(NeutralMode.Brake);
            mLeftSlave.setNeutralMode(NeutralMode.Brake);
            mLeftSlave2.setNeutralMode(NeutralMode.Brake);
        }
        else {
        	mRightMaster.setNeutralMode(NeutralMode.Coast);
            mRightSlave.setNeutralMode(NeutralMode.Coast);
            mRightSlave2.setNeutralMode(NeutralMode.Coast);
            mLeftMaster.setNeutralMode(NeutralMode.Coast);
            mLeftSlave.setNeutralMode(NeutralMode.Coast);
            mLeftSlave2.setNeutralMode(NeutralMode.Coast);
        }
    }
	public void toggleDirection() {
		forwardDirection = !forwardDirection;
	}
	public boolean getDirection() {
		return forwardDirection;
	}
	
	
	//velocity control
	protected static boolean usesTalonVelocityControl(DriveControlState state) {
		if (state == DriveControlState.VELOCITY_SETPOINT || state == DriveControlState.PATH_FOLLOWING) {
           return true;
       }
       return false;
	}
    private void configureTalonsForSpeedControl() {
        if (!usesTalonVelocityControl(mDriveControlState)) {
        	mLeftMaster.selectProfileSlot(1, 0); //set the profile always to 1 for speed control
        	mLeftMaster.configNominalOutputForward(0.04, 0);
    		mLeftMaster.configNominalOutputReverse(-0.04, 0);
    		mLeftMaster.configPeakOutputForward(1, 0);
    		mLeftMaster.configPeakOutputReverse(-1, 0);
            
            mRightMaster.selectProfileSlot(1, 0);
            mRightMaster.configNominalOutputForward(0.0, 0);//.04
			mRightMaster.configNominalOutputReverse(0.0, 0);
			mRightMaster.configPeakOutputForward(1, 0);
			mRightMaster.configPeakOutputReverse(-1, 0);
            setBrakeMode(false);
        }
    }
	public synchronized void setVelocitySetpoint(double left_inches_per_sec, double right_inches_per_sec) {
        configureTalonsForSpeedControl();
        mDriveControlState = DriveControlState.VELOCITY_SETPOINT;
        updateVelocitySetpoint(left_inches_per_sec, right_inches_per_sec);
    }
	public void updateVelocitySetpoint(double left, double right) {
		if (usesTalonVelocityControl(mDriveControlState)) {
            final double max_desired = Math.max(Math.abs(left), Math.abs(right));
            final double scale = max_desired > Constants.highGearMaxVel ? Constants.highGearMaxVel / max_desired : 1.0;  //need to use the number from constant class later on
            mLeftMaster.set(ControlMode.Velocity, inchesPerSecondToEncoderSamples(left*scale));
            mRightMaster.set(ControlMode.Velocity, inchesPerSecondToEncoderSamples(right*scale));
            SmartDashboard.putNumber("velocityleft", left);
        } else {
            System.out.println("Hit a bad velocity control state");
            mLeftMaster.set(ControlMode.Velocity, 0);
            mRightMaster.set(ControlMode.Velocity, 0);
        }
	}
	
	
	//position control
	protected static boolean usesTalonPositionControl(DriveControlState state) {
	       if (state == DriveControlState.TURN_TO_HEADING || state == DriveControlState.POSITION_SETPOINT)
	           return true; 
	       return false;
	   }
    private void configureTalonsForPositionControl() {
        if (!usesTalonPositionControl(mDriveControlState)) {
        		shiftLow();
            
            mLeftMaster.selectProfileSlot(0, 0);
            mLeftMaster.configNominalOutputForward(0.04, 0);
			mLeftMaster.configNominalOutputReverse(-0.04, 0);
			mLeftMaster.configPeakOutputForward(1, 0);
			mLeftMaster.configPeakOutputReverse(-1, 0);
            mRightMaster.selectProfileSlot(0, 0);
            mRightMaster.configNominalOutputForward(0.04, 0);
			mRightMaster.configNominalOutputReverse(-0.04, 0);
			mRightMaster.configPeakOutputForward(1, 0);
			mRightMaster.configPeakOutputReverse(-1, 0);
            setBrakeMode(true);
        }
    }
    public synchronized void updatePositionSetpoint(double left_position_inches, double right_position_inches) {
    	configureTalonsForPositionControl();
		zeroSensors();
		mDriveControlState = DriveControlState.TURN_TO_HEADING;
        if (usesTalonPositionControl(mDriveControlState)) {
            mLeftMaster.set(ControlMode.MotionMagic, inchesToEncoderSamples(left_position_inches));
            mRightMaster.set(ControlMode.MotionMagic, inchesToEncoderSamples(right_position_inches));
        } else {
            System.out.println("Hit a bad position control state");
            mLeftMaster.set(ControlMode.MotionMagic, 0);
            mRightMaster.set(ControlMode.MotionMagic, 0);
        }
    }
	public void rotate(double angle) {
		configureTalonsForPositionControl();
		zeroSensors();
	    mDriveControlState = DriveControlState.TURN_TO_HEADING;
	    Kinematics.DriveVelocity wheel_delta = Kinematics.inverseKinematics(new Twist2d(0,0,angle*.0175));
		SmartDashboard.putNumber("leftdealta", wheel_delta.left);
		updatePositionSetpoint(-wheel_delta.left, -wheel_delta.right);//mRightMaster.getSelectedSensorPosition(0) - 
	}
	public boolean isRotateFinished(int target) {
		if (Math.abs(gyro.getAngle()-target) < 1.5) {
			SmartDashboard.putBoolean("gyroFinished", true);
			return true;
		}
		return false;
	}
	public boolean isDriveFinished(int target) {
		if (Math.abs(encoderSamplesToInches((mLeftMaster.getSelectedSensorPosition(0)+mRightMaster.getSelectedSensorPosition(0))/2)-target)<2)
			return true;
		return false;
	}    
    
	
	//Path craps
    public synchronized void setWantDrivePath(Path path, boolean reversed) {
        if (mCurrentPath != path || mDriveControlState != DriveControlState.PATH_FOLLOWING) {
            configureTalonsForSpeedControl();
            RobotState.getInstance().resetDistanceDriven();
            mPathFollower = new PathFollower(path, reversed, new PathFollower.Parameters(new Lookahead(12.0, 24.0, 6.0, 75), 0.0, 5.0, 0.03, 0.02, 1.0, 0.05, 150, 150, 0.75, 12.0, 9.0));
            mDriveControlState = DriveControlState.PATH_FOLLOWING;
            mCurrentPath = path;
            isReversed = reversed;
        } else {
            setVelocitySetpoint(0, 0);
        }
    }
    public void updatePathFollower(double timestamp) {
        RigidTransform2d robot_pose = mRobotState.getLatestFieldToVehicle().getValue();
        Twist2d command = mPathFollower.update(timestamp, robot_pose, RobotState.getInstance().getDistanceDriven(), RobotState.getInstance().getPredictedVelocity().dx);
        if (!mPathFollower.isFinished()) {
            Kinematics.DriveVelocity setpoint = Kinematics.inverseKinematics(command);
            updateVelocitySetpoint(setpoint.left, setpoint.right); //reverse here to turn to comp code
        } else {
            updateVelocitySetpoint(0, 0);
        }
    }
    public synchronized boolean isDoneWithPath() {
        if (mDriveControlState == DriveControlState.PATH_FOLLOWING && mPathFollower != null) {
            return mPathFollower.isFinished();
        } else {
            System.out.println("Robot is not in path following mode");
            return true;
        }
    }

    
    
    //shifting
	public void shiftHigh() {
		if (!isHighGear) {
			shiftCylinder.set(false);
			isHighGear = true;
		}
	}
	public void shiftLow() {
		if (isHighGear) {
			shiftCylinder.set(true);
			isHighGear = false;
		}
	}
	public boolean isHighGear() {
		return isHighGear;
	}
	
	
	//conversions
	private static double inchesPerSecondToEncoderSamples(double inches_per_second) {
		return inchesToEncoderSamples(inches_per_second)/10;
	}
	private static double inchesToEncoderSamples(double inches) {
		return inches/(Constants.kDriveWheelDiameterInches*Math.PI)*4096;
	}
	private static double encoderSamplesToInches(int samples) {
		return (samples/4096)*Constants.kDriveWheelDiameterInches*Math.PI;
	}
	
	
	//Cleanse the robot
	public synchronized Rotation2d getGyroAngle() {
		Rotation2d mAngleAdjustment = Rotation2d.identity();
		/*if (isReversed || trigger) {
			trigger = true;
			return mAngleAdjustment.rotateBy(Rotation2d.fromDegrees(-(gyro.getAngle()+180)));	
		}
		else*/
			return mAngleAdjustment.rotateBy(Rotation2d.fromDegrees(-gyro.getAngle()));
	}
	private void reloadGains() {
		//position
		mLeftMaster.config_kP(0, 0.3, 0);
		mLeftMaster.config_kI(0, 0.0, 0);
		mLeftMaster.config_kD(0, 0.01, 0);
		mLeftMaster.config_kF(0, 0.05, 0);//.45
		//mLeftMaster.config_IntegralZone(0, 700, 0);

		mRightMaster.config_kP(0, 0.3, 0);
		mRightMaster.config_kI(0, 0.0, 0);
		mRightMaster.config_kD(0, 0.01, 0);
		mRightMaster.config_kF(0, 0.05, 0);//.45
		//mRightMaster.config_IntegralZone(0, 700, 0);
		/*mLeftMaster.configMotionCruiseVelocity(3000, 0);
		mLeftMaster.configMotionAcceleration(100000, 0);
		mRightMaster.configMotionCruiseVelocity(3000, 0);
		mRightMaster.configMotionAcceleration(100000, 0);*/
		
		//velocity
		mLeftMaster.config_kP(1, 0.5, 0);
		mLeftMaster.config_kI(1, 0.0, 0);
		mLeftMaster.config_kD(1, 0, 0);
		mLeftMaster.config_kF(1, 0.0, 0);

		mRightMaster.config_kP(1, 0.5, 0);
		mRightMaster.config_kI(1, 0.0, 0);
		mRightMaster.config_kD(1, 0, 0);
		mRightMaster.config_kF(1, 0.0, 0);//.15
	}
	public void zeroSensors() {
		mLeftMaster.setSelectedSensorPosition(0, 0, 0);
		mRightMaster.setSelectedSensorPosition(0, 0, 0);
		gyro.reset();
	}
	public void free() {
		setOpenLoop(DriveSignal.NEUTRAL);
	}
	public void stop() {
		setOpenLoop(DriveSignal.BRAKE);
	}
}
