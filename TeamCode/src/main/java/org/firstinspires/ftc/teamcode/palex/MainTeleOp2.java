package org.firstinspires.ftc.teamcode.palex;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "MainTeleOp2")
public class MainTeleOp2 extends OpMode {
    DcMotorEx flywheel;
    Servo blocker;

    @Override
    public void init(){
        flywheel = hardwareMap.get(DcMotorEx.class, "flywheel");
        blocker = hardwareMap.get(Servo.class, "blocker");
        flywheel.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        blocker.setPosition(0);
    }
    @Override
    public void loop(){
        if(gamepad1.cross){
            flywheel.setVelocity(1500);
        }
        if(gamepad1.circle){
            flywheel.setVelocity(0);
        }
        if(gamepad1.left_trigger > 0.5){
            blocker.setPosition(0);
        }
        if(gamepad1.right_trigger > 0.5){
            blocker.setPosition(1);
        }
    }
}
