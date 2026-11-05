package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
@TeleOp
public class Workshop2 extends OpMode {
    DcMotorEx motor;

    @Override
    public void init(){
        motor=hardwareMap.get(DcMotorEx.class, "motor");
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    @Override
    public void loop(){
        motor.setTargetPosition(700);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        if(gamepad1.a){
            motor.setPower(0.5);
        }else {
            motor.setPower(0);
        }
        telemetry.addData("motor",motor.getCurrentPosition());
    }
}