package org.firstinspires.ftc.teamcode.ana;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class MainTeleOp extends OpMode {
    private DcMotor rFront;
    private DcMotor lFront;
    private DcMotor rRear;
    private DcMotor lRear;

@Override
    public void init() {
        rFront = hardwareMap.get(DcMotor.class, "rFront");
        rFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        lFront = hardwareMap.get(DcMotor.class, "lFront");
        lFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        rRear = hardwareMap.get(DcMotor.class, "rRear");
        rRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        lRear = hardwareMap.get(DcMotor.class, "lRear");
        lRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


    }

    @Override
    public void loop() {
        if (gamepad1.left_stick_y < 0) {
            rFront.setPower(-gamepad1.left_stick_y);
            rRear.setPower(-gamepad1.left_stick_y);
            lFront.setPower(-gamepad1.left_stick_y);
            lRear.setPower(-gamepad1.left_stick_y);
        }
        if (gamepad1.left_stick_y > 0) {
            rFront.setPower(gamepad1.left_stick_y);
            rRear.setPower(gamepad1.left_stick_y);
            lFront.setPower(gamepad1.left_stick_y);
            lRear.setPower(gamepad1.left_stick_y);
        }
        if (gamepad1.left_stick_x < 0) {
            rFront.setPower(-gamepad1.left_stick_x);
            rRear.setPower(-gamepad1.left_stick_x);
            lFront.setPower(-gamepad1.left_stick_x);
            lRear.setPower(-gamepad1.left_stick_x);
        }
        if (gamepad1.left_stick_x > 0) {
            rFront.setPower(gamepad1.left_stick_x);
            rRear.setPower(gamepad1.left_stick_x);
            lFront.setPower(gamepad1.left_stick_x);
            lRear.setPower(gamepad1.left_stick_x);
        }
        if (gamepad1.right_stick_x < 0) {
            rFront.setPower(gamepad1.right_stick_x);
            rRear.setPower(gamepad1.right_stick_x);
            lFront.setPower(-gamepad1.right_stick_x);
            lRear.setPower(-gamepad1.right_stick_x);
        }
        if (gamepad1.right_stick_x > 0) {
            rFront.setPower(-gamepad1.right_stick_x);
            rRear.setPower(-gamepad1.right_stick_x);
            lFront.setPower(gamepad1.right_stick_x);
            lRear.setPower(gamepad1.right_stick_x);
        } /*else {
            rFront.setPower(0);
            rRear.setPower(0);
            lFront.setPower(0);
            lRear.setPower(0);
        }*/
    }
}