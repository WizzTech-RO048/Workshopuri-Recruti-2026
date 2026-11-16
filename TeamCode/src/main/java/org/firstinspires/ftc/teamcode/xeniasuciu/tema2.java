package org.firstinspires.ftc.teamcode.xeniasuciu;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class tema2 extends OpMode  {

    DcMotorEx motor1;
    Servo servo;

    boolean on_off = false;

    @Override
    public void init() {
        motor1 = hardwareMap.get(DcMotorEx.class, "flywheel");
        servo = hardwareMap.get(Servo.class, "servo");

        motor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        servo.setPosition(0.0);
    }

    @Override
    public void loop() {
        if (gamepad1.leftTriggerWasPressed()) servo.setPosition(0.0);
        if (gamepad1.rightTriggerWasPressed()) servo.setPosition(1.0);
        if (gamepad1.crossWasPressed()) motor1.setVelocity(1500);
        if (gamepad1.circleWasPressed()) motor1.setVelocity(0);

    }
}