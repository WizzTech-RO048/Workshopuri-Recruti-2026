package org.firstinspires.ftc.teamcode.ana;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.vision.opencv.ColorBlobLocatorProcessor;

@TeleOp
public class Tema2 extends OpMode {

    private DcMotorEx flywheel;
    private Servo blocator;
    private final double BLOCAT = 0.0;
    private final double OPEN = 1.0;
    private final double VELOCITY = 1500;


    @Override
    public void init() {
        flywheel = hardwareMap.get(DcMotorEx.class,"flywheel");
        blocator = hardwareMap.get(Servo.class,"blocator");

        flywheel.setVelocity(0);
        blocator.setPosition(BLOCAT);
    }

    @Override
    public void loop() {
        if (gamepad1.b){
            flywheel.setVelocity(0);
        }
        if(gamepad1.right_trigger>0.5) {
            blocator.setPosition(OPEN);
        }

        flywheel.setVelocity(0);
        blocator.setPosition(BLOCAT);
    }
}
