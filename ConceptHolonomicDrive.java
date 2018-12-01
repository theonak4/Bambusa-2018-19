package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

//  Mecanum Forces Diagram
//
//        ^        ^
//    X  /  FRONT   \  X
//    X /            \ X
//    X/ DC1     DC2  \X
//            X
//           XXX
//    ^       X        ^
//    X\ DC4      DC3 /X
//    X \            / X
//    X  \   BACK   /  X

//   X = Wheel
//   / = Mecanum Force


@TeleOp(name = "BAMBUSA TeleOP 2", group = "TeleOP")
//@Disabled
public class ConceptHolonomicDrive extends OpMode {

    DcMotor motorFrontRight; // Create Front RIGHT Motor variable
    DcMotor motorFrontLeft; // Create Front LEFT Motor variable
    DcMotor motorBackRight; // Create Back RIGHT Motor variable
    DcMotor motorBackLeft; // Create Back LEFT Motor variable

    /**
     * Constructor
     */
    public ConceptHolonomicDrive() {

    }

    @Override
    public void init() {


        motorFrontRight = hardwareMap.dcMotor.get("frontRight"); // Initialize Front RIGHT Motor
        motorFrontLeft = hardwareMap.dcMotor.get("frontLeft"); // Initialize Front LEFT Motor
        motorBackLeft = hardwareMap.dcMotor.get("backRight"); // Initialize Back RIGHT Motor
        motorBackRight = hardwareMap.dcMotor.get("backLeft"); // Initialize Back LEFT Motor


    }

    @Override
    public void loop() {

		// Calculate rotation with Eucladian Norm to assign each vector the length of its arrow.
        double r = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);
        // Use inverse tangent to calculate mecanum movements
		double robotAngle = Math.atan2(gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4;
		// Grab right stick X value
		double rightX = gamepad1.right_stick_x;
		final double v1 = r * Math.cos(robotAngle) + rightX; // Assign DC1 Power
		final double v2 = r * Math.sin(robotAngle) - rightX; // Assign DC2 Power
		final double v3 = r * Math.sin(robotAngle) + rightX; // Assign DC3 Power
		final double v4 = r * Math.cos(robotAngle) - rightX; // Assign DC4 Power
	
		motorFrontLeft.setPower(v1); // Set Front LEFT Power with the DC1 calculation
		motorFrontRight.setPower(v2); // Set Front RIGHT Power with the DC2 calculation
		motorBackLeft.setPower(v3); // Set Back LEFT Power with DC3 calculation
		motorBackRight.setPower(v4); // Set Back RIGHT Power with DC4 calculation

    }

    @Override
    public void stop() {

    }

    /*
     * This method scales the joystick input so for low joystick values, the
     * scaled value is less than linear.  This is to make it easier to drive
     * the robot more precisely at slower speeds.
     */

}
