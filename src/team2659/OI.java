package team2659;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import team2659.commands.Fire;
import team2659.commands.Intake;
import team2659.commands.Outtake;
import team2659.commands.SetArm;
import team2659.commands.StopIntake;
import team2659.commands.ToggleOrientation;
import team2659.commands.shiftHigh;
import team2659.commands.shiftLow;
import team2659.commands.toggleArmMode;


public class OI {
	public Joystick driveStick, operatorStick;

	private Button shiftLowButton, shiftHighButton, toggleOrientation, intakeButton, outtakeButton, fireButton, armModeButton, firstStage, secondStage, thirdStage, fourthStage, fifthStage;
	
	public OI() {
		driveStick = new Joystick(0);
		operatorStick = new Joystick(1);
		
		shiftLowButton = new JoystickButton(driveStick, 5);
		shiftLowButton.whenPressed(new shiftLow());
		
		shiftHighButton = new JoystickButton(driveStick, 6);
		shiftHighButton.whenPressed(new shiftHigh());
		
		toggleOrientation = new JoystickButton(driveStick, 1);
		toggleOrientation.whenPressed(new ToggleOrientation());
		
		outtakeButton = new JoystickButton(operatorStick, 7);
		outtakeButton.whenPressed(new Fire());
		
		fireButton = new JoystickButton(operatorStick, 5);
		fireButton.whenPressed(new Outtake());

		armModeButton = new JoystickButton(operatorStick, 10);
		armModeButton.whenPressed(new toggleArmMode());
		
		intakeButton = new JoystickButton(operatorStick, 8);
		intakeButton.whileHeld(new Intake());
		intakeButton.whenReleased(new StopIntake());
		
		firstStage = new JoystickButton(operatorStick, 2);
		firstStage.whenPressed(new SetArm(1));
		
		secondStage = new JoystickButton(operatorStick, 1);
		secondStage.whenPressed(new SetArm(6));
		
		thirdStage = new JoystickButton(operatorStick, 3);
		thirdStage.whenPressed(new SetArm(2));
		
		fourthStage = new JoystickButton(operatorStick, 4);
		fourthStage.whenPressed(new SetArm(3));
		
		fifthStage = new JoystickButton(operatorStick, 6);
		fifthStage.whenPressed(new SetArm(5));
		
		//lifterActuateButton = new JoystickButton(operatorStick, 9);
		//lifterActuateButton.whenPressed(new ActuateLifter());
		
		/*lifterRightButton = new JoystickButton(operatorStick, 6);
		lifterRightButton.whileHeld(new LiftRight());
		lifterRightButton.whenReleased(new StopRight());
		
		lifterLeftButton = new JoystickButton(operatorStick, 5);
		lifterLeftButton.whileHeld(new LiftLeft());
		lifterLeftButton.whenReleased(new StopLeft());*/
	}
}
