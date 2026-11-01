package org.firstinspires.ftc.teamcode.HandreaRadu;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(group = "opModes", name = "MainTeleOp")
public class MainTeleOp extends LinearOpMode {

    DcMotor FataDreapta;
    DcMotor FataStanga;
    DcMotor SpateDreapta;
    DcMotor SpateStanga;

    private static final double INPUT_EXPONENT = 3.0;

    @Override
    public void runOpMode() throws InterruptedException {

        FataDreapta = hardwareMap.get(DcMotor.class, "FataDreapta");
        FataStanga = hardwareMap.get(DcMotor.class, "FataStanga");
        SpateDreapta = hardwareMap.get(DcMotor.class, "SpateDreapta");
        SpateStanga = hardwareMap.get(DcMotor.class, "SpateStanga");

        FataStanga.setDirection(DcMotor.Direction.REVERSE);
        SpateStanga.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {

            double rawY  = -gamepad1.left_stick_y;
            double rawX  =  gamepad1.left_stick_x;
            double rawRx =  gamepad1.right_stick_x;

            double y  = scaleInput(rawY);
            double x  = scaleInput(rawX);
            double rx = scaleInput(rawRx);

            double frontLeftPower  = y + x + rx;
            double backLeftPower   = y - x + rx;
            double frontRightPower = y - x - rx;
            double backRightPower  = y + x - rx;

            double maxPower = Math.max(Math.abs(frontLeftPower), Math.max(Math.abs(backLeftPower), Math.max(Math.abs(frontRightPower), Math.abs(backRightPower))));

            if (maxPower > 1.0) {
                frontLeftPower  /= maxPower;
                backLeftPower   /= maxPower;
                frontRightPower /= maxPower;
                backRightPower  /= maxPower;
            }

            FataStanga.setPower(frontLeftPower);
            SpateStanga.setPower(backLeftPower);
            FataDreapta.setPower(frontRightPower);
            SpateDreapta.setPower(backRightPower);

            telemetry.addData("Putere FS", frontLeftPower);
            telemetry.addData("Putere SS", backLeftPower);
            telemetry.addData("Putere FD", frontRightPower);
            telemetry.addData("Putere SD", backRightPower);
            telemetry.update();
        }
    }

    private double scaleInput(double input) {
        return Math.signum(input) * Math.pow(Math.abs(input), INPUT_EXPONENT);
    }
}
