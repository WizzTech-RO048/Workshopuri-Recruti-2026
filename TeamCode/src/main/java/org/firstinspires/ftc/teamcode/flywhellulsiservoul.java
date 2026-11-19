package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;


@TeleOp
public class flywhellulsiservoul extends OpMode {
    private DcMotorEx flywheel;
    private Servo blocker;
    private static final double FLYWHEEL_VELOCITY = 1500;
    @Override
    public void init() {
        flywheel = hardwareMap.get(DcMotorEx.class, "flywheel");
        blocker = hardwareMap.get(Servo.class, "blocker");
        flywheel.setDirection(DcMotor.Direction.FORWARD);
        flywheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        blocker.setPosition(0);
        flywheel.setVelocity(0);
    }
        @Override
                public void loop(){
            if (gamepad1.x){
                flywheel.setVelocity(FLYWHEEL_VELOCITY);
            }
            if (gamepad1.b){
                flywheel.setVelocity(0);
            }
            if (gamepad1.right_trigger > 0.5){
                blocker.setPosition(1);
            }
            if (gamepad1.left_trigger > 0.5){
                blocker.setPosition(0);
            }
            telemetry.addData("Flywheel velocity",
                    flywheel.getVelocity());
            telemetry.addData("Flywheel target",
                    FLYWHEEL_VELOCITY);
            telemetry.addData("Servo position",
                    blocker.getPosition());
            telemetry.update();
        }


}