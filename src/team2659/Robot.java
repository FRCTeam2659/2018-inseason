package team2659;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import team2659.commands.CommandGroups.*;
import team2659.commands.CommandGroups.OneCubeScaleAuto.*;
import team2659.commands.CommandGroups.OneCudeSwitchAuto.*;

import team2659.subsystems.*;

public class Robot extends IterativeRobot {

	public static Drivetrain drivetrain;
	public static Arm arm;
	public static Intake intake;
	public static Climber climber;
	public static OI oi;
	private String gameData = "";
	private String position;
	private String cubeNum;
	private double autoTimeDelay = 0;
	private CommandGroup autonomousCommand;
	private SendableChooser<String> chooser = new SendableChooser<>();
	private SendableChooser<String> cubeChooser = new SendableChooser<>();
	//private Compressor mCompressor = new Compressor(0);
	@Override
	public void robotInit() {
		RobotMap.init();
		drivetrain = new Drivetrain();
		arm = new Arm();
		intake = new Intake();
		climber = new Climber();
		oi = new OI();
		chooser.addDefault("Default go straight", "straight");
		chooser.addObject("Left", "left");
		chooser.addObject("Middle", "middle");
		chooser.addObject("Right", "right");
		
		cubeChooser.addDefault("one cube switch auto", "oneSwitch");
		cubeChooser.addObject("one cube scale auto", "oneScale");
		cubeChooser.addObject("two cube auto", "two");
		
		//mCompressor.setClosedLoopControl(true);
		
		SmartDashboard.putData("autonomous position", chooser);
		SmartDashboard.putData("cube going to do", cubeChooser);
		SmartDashboard.putNumber("auto time delay", 0);
		RobotMap.gyro.calibrate();
	}

	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("arm little Position", RobotMap.armFront.getSelectedSensorPosition(0));
	}

	@Override
	public void autonomousInit() {
		RobotMap.armFront.setSelectedSensorPosition(0, 0, 0);
		drivetrain.zeroSensors();
		autoTimeDelay = SmartDashboard.getNumber("auto time delay", 0);
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		
		//position = "right";
		position = chooser.getSelected();
		//cubeNum = "two";
		cubeNum = cubeChooser.getSelected();
		
		//one cube switch autonomous
		if (cubeNum.equals("oneSwitch")) {
			if (position.equals("left")) {
				switch (gameData.toUpperCase()) {
				case "LLL":
					autonomousCommand = new OneCubeSwitchLeftL();
					break;
				case "LRL":
					autonomousCommand = new OneCubeSwitchLeftL();
					break;
				case "RLR":
					autonomousCommand = new AutoStraight();
					break;
				case "RRR":
					autonomousCommand = new AutoStraight();
					break;
				}
			}
			else if (position.equals("middle")) {
				switch (gameData.toUpperCase()) {
				case "LLL":
					autonomousCommand = new OneCubeSwitchMidL();
					break;
				case "LRL":
					autonomousCommand = new OneCubeSwitchMidL();
					break;
				case "RLR":
					autonomousCommand = new OneCubeSwitchMidR();
					break;
				case "RRR":
					autonomousCommand = new OneCubeSwitchMidR();
					break;
				}
			}
			else if (position.equals("right")) {
				switch (gameData.toUpperCase()) {
				case "RRR":
					autonomousCommand = new OneCubeSwitchRightR();
					break;
				case "RLR":
					autonomousCommand = new OneCubeSwitchRightR();
					break;
				case "LRL":
					autonomousCommand = new AutoStraight();
					break;
				case "LLL":
					autonomousCommand = new AutoStraight();
					break;
				}
			}
			else
				autonomousCommand = new AutoStraight();
		}
			
		
		//one cube scale autonomous
		else if (cubeNum.equals("oneScale")) {
			if (position.equals("left")) {
				switch (gameData.toUpperCase()) {
				case "LLL":
					autonomousCommand = new OneCubeScaleLeftL();
					break;
				case "LRL":
					autonomousCommand = new OneCubeScaleLeftR();
					break;
				case "RLR":
					autonomousCommand = new OneCubeScaleLeftL();
					break;
				case "RRR":
					autonomousCommand = new OneCubeScaleLeftR();
					break;
				}
			}
			else if (position.equals("middle")) {
				switch (gameData.toUpperCase()) {
				case "LLL":
					autonomousCommand = new OneCubeScaleMidL();
					break;
				case "LRL":
					autonomousCommand = new OneCubeScaleMidR();
					break;
				case "RLR":
					autonomousCommand = new OneCubeScaleMidL();
					break;
				case "RRR":
					autonomousCommand = new OneCubeScaleMidR();
					break;
				}
			}
			else if (position.equals("right")) {
				switch (gameData.toUpperCase()) {
				case "RRR":
					autonomousCommand = new OneCubeScaleRightR();
					break;
				case "RLR":
					autonomousCommand = new OneCubeScaleRightL();
					break;
				case "LRL":
					autonomousCommand = new OneCubeScaleRightR();
					break;
				case "LLL":
					autonomousCommand = new OneCubeScaleRightL();;
					break;
				}
			}
			else
				autonomousCommand = new AutoStraight();
		}
		
		//two cube auto
		else if (cubeNum.equals("two")) {
			if (position.equals("left")) {
				switch (gameData.toUpperCase()) {
				case "LLL":
					autonomousCommand = new OneCubeScaleLeftL();
					break;
				case "LRL":
					autonomousCommand = new leftL();
					break;
				case "RLR":
					autonomousCommand = new OneCubeScaleLeftL();
					break;
				case "RRR":
					autonomousCommand = new OneCubeScaleLeftR();
					break;
				}
			}
			else if (position.equals("middle")) {
				switch (gameData.toUpperCase()) {
				case "LLL":
					autonomousCommand = new midLL();
					break;
				case "LRL":
					autonomousCommand = new midLL();
					break;
				case "RLR":
					autonomousCommand = new midRR();
					break;
				case "RRR":
					autonomousCommand = new midRR();
					break;
				}
			}
			else if (position.equals("right")) {
				switch (gameData.toUpperCase()) {
				case "RRR":
					autonomousCommand = new OneCubeScaleRightR();
					break;
				case "RLR":
					autonomousCommand = new rightR();
					break;
				case "LRL":
					autonomousCommand = new OneCubeScaleRightR();
					break;
				case "LLL":
					autonomousCommand = new AutoStraight();
					break;
				}
			}
			else
				autonomousCommand = new AutoStraight();
		}
		Timer.delay(autoTimeDelay);
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		//SmartDashboard.putNumber("leftEncodervel", RobotMap.leftMaster.getSelectedSensorVelocity(0));
		//SmartDashboard.putNumber("rightEncodervel", RobotMap.rightMaster.getSelectedSensorVelocity(0));
		if (!RobotMap.backSwitch.get())
			arm.setStage(0);
	}

	@Override
	public void teleopInit() {
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		
		//mCompressor.start();
		RobotMap.rightMaster.setSelectedSensorPosition(0, 0, 0);
		RobotMap.leftMaster.setSelectedSensorPosition(0, 0, 0);
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		if (oi.operatorStick.getRawButton(10) && oi.operatorStick.getRawButton(9))
			climber.activate();
		if (oi.driveStick.getRawButton(5)) //hold
			climber.set(-0.5);
		else if (oi.driveStick.getRawButton(6)) //up
			climber.set(-1);
		else if (oi.driveStick.getRawButton(2))
			climber.set(1);
		else
			climber.set(0);
		/*if (Robot.arm.getArmMode())
			Robot.arm.set(-Robot.oi.operatorStick.getRawAxis(1)*0.8);
		if (Math.abs((RobotMap.leftMaster.getSelectedSensorVelocity(0)+RobotMap.leftMaster.getSelectedSensorVelocity(0))/2) > 1500) //6ft/s
			drivetrain.shiftHigh();
		if (Math.abs((RobotMap.leftMaster.getSelectedSensorVelocity(0)+RobotMap.leftMaster.getSelectedSensorVelocity(0))/2) < 750) //3ft/s
			drivetrain.shiftLow();*/
		
		/*SmartDashboard.putNumber("leftEncoder", RobotMap.leftMaster.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("rightEncoder", RobotMap.rightMaster.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("leftEncodervel", RobotMap.leftMaster.getSelectedSensorVelocity(0));
		SmartDashboard.putNumber("rightEncodervel", RobotMap.rightMaster.getSelectedSensorVelocity(0));*/
		SmartDashboard.putNumber("gyro", RobotMap.gyro.getAngle());
		SmartDashboard.putNumber("armPosition", RobotMap.armFront.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("intake current 2", RobotMap.pdp.getCurrent(8));
		/*SmartDashboard.putNumber("0", RobotMap.pdp.getCurrent(0));
		SmartDashboard.putNumber("1", RobotMap.pdp.getCurrent(1));
		SmartDashboard.putNumber("2", RobotMap.pdp.getCurrent(2));
		SmartDashboard.putNumber("3", RobotMap.pdp.getCurrent(3));
		SmartDashboard.putNumber("6", RobotMap.pdp.getCurrent(6));
		SmartDashboard.putNumber("7", RobotMap.pdp.getCurrent(7));
		SmartDashboard.putNumber("9", RobotMap.pdp.getCurrent(9));
		SmartDashboard.putNumber("10", RobotMap.pdp.getCurrent(10));
		SmartDashboard.putNumber("11", RobotMap.pdp.getCurrent(11));
		SmartDashboard.putNumber("12", RobotMap.pdp.getCurrent(12));
		SmartDashboard.putNumber("13", RobotMap.pdp.getCurrent(13));
		SmartDashboard.putNumber("14", RobotMap.pdp.getCurrent(14));
		SmartDashboard.putNumber("15", RobotMap.pdp.getCurrent(15));*/
	}

	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
	
	public String getData() {
		return gameData;
	}
}
