package team2659;

import team2659.util.math.Rotation2d;
import team2659.util.math.Twist2d;

public class RobotStateEstimator {
	 static RobotStateEstimator instance_ = new RobotStateEstimator();

	    public static RobotStateEstimator getInstance() {
	        return instance_;
	    }

	    RobotStateEstimator() {
	    }

	    RobotState robot_state_ = RobotState.getInstance();
	    double left_encoder_prev_distance_ = 0;
	    double right_encoder_prev_distance_ = 0;

	   public synchronized void onStart(double timestamp) {
	        left_encoder_prev_distance_ = encoderSamplestoInches(RobotMap.leftMaster.getSelectedSensorPosition(0));
	        right_encoder_prev_distance_ = encoderSamplestoInches(RobotMap.rightMaster.getSelectedSensorPosition(0));
	    }

	    public synchronized void onLoop(double timestamp) {
	        final double left_distance = encoderSamplestoInches(RobotMap.leftMaster.getSelectedSensorPosition(0));
	        final double right_distance = encoderSamplestoInches(RobotMap.rightMaster.getSelectedSensorPosition(0));
	        final Rotation2d gyro_angle = Robot.drivetrain.getGyroAngle();
	        final Twist2d odometry_velocity = robot_state_.generateOdometryFromSensors(left_distance - left_encoder_prev_distance_, right_distance - right_encoder_prev_distance_, gyro_angle);
	        final Twist2d predicted_velocity = Kinematics.forwardKinematics(samplestoinchespersec(RobotMap.leftMaster.getSelectedSensorVelocity(0)), samplestoinchespersec(RobotMap.rightMaster.getSelectedSensorVelocity(0)));
	        robot_state_.addObservations(timestamp, odometry_velocity, predicted_velocity);
	        left_encoder_prev_distance_ = left_distance;
	        right_encoder_prev_distance_ = right_distance;
	    }

	    public void onStop(double timestamp) {
	        // no-op
	    }
	    
	    private static double samplestoinchespersec(double samples_per_100ms) {
			return encoderSamplestoInches(samples_per_100ms)*10;
		}
		private static double encoderSamplestoInches(double samples) {
			return samples/4096*Constants.kDriveWheelDiameterInches*Math.PI;
		}
}
