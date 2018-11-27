package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

//  ROBOT SEEN FROM ABOVE
//
//        X FRONT X
//      X           X
//    X  P1       P2  X
//            X
//           XXX
//            X
//    X  P4       P3  X
//      X           X
//        X       X

@TeleOp(name = "BAMBUSA TeleOP", group = "TeleOP")
//@Disabled
public class ConceptHolonomicDrive extends OpMode {

    DcMotor motorFrontRight;
    DcMotor motorFrontLeft;
    DcMotor motorBackRight;
    DcMotor motorBackLeft;


    /**
     * Constructor
     */
    public ConceptHolonomicDrive() {

    }

    @Override
    public void init() {


		/*
		 * Use the hardwareMap to get the dc motors and servos by name. Note
		 * that the names of the devices must match the names used when you
		 * configured your robot and created the configuration file.
		 */


        motorFrontRight = hardwareMap.dcMotor.get("frontRight");
        motorFrontLeft = hardwareMap.dcMotor.get("frontLeft");
        motorBackLeft = hardwareMap.dcMotor.get("backRight");
        motorBackRight = hardwareMap.dcMotor.get("backLeft");
        //These work without reversing (Tetrix motors).
        //AndyMark motors may be opposite, in which case uncomment these lines:
        pulley = hardwareMap.dcMotor.get("pulley");

    }

    @Override
    public void loop() {


        double r = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);
	double robotAngle = Math.atan2(gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4;
	double rightX = gamepad1.right_stick_x;
	final double v1 = r * Math.cos(robotAngle) + rightX;
	final double v2 = r * Math.sin(robotAngle) - rightX;
	final double v3 = r * Math.sin(robotAngle) + rightX;
	final double v4 = r * Math.cos(robotAngle) - rightX;
	
	motorFrontLeft.setPower(v1);
	motorFrontRight.setPower(v2);
	motorBackLeft.setPower(v3)
	motorBackRight.setPower(v4);

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
