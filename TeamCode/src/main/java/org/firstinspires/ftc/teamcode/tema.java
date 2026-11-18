package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "tema")
public class tema extends LinearOpMode {

    @Override
    public void runOpMode() {
        DcMotorEx flywheel = hardwareMap.get(DcMotorEx.class, "flywheel");
        Servo blocator = hardwareMap.get(Servo.class, "blocator");

        waitForStart();

        while (opModeIsActive()) {

            if (gamepad1.cross) {
                flywheel.setVelocity(1500);
            }
            if (gamepad1.circle) {
                flywheel.setVelocity(0);
            }

            if (gamepad1.right_trigger > 0.5) {
                blocator.setPosition(1);
            }
            if (gamepad1.left_trigger > 0.5) {
                blocator.setPosition(0);
            }
        }
    }
}