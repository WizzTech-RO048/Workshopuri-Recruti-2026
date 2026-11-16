package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp
public class FlywheelTeleOp extends OpMode {

    private DcMotorEx flywheel;
    private Servo blocker;
    private final double viteza = 1500;

    @Override
    public void init() {

        flywheel = hardwareMap.get(DcMotorEx.class, "flywheel");
        blocker = hardwareMap.get(Servo.class, "blocker");

        flywheel.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        flywheel.setVelocity(0);
        blocker.setPosition(0);
    }

    @Override
    public void loop() {

        if (gamepad1.cross) {
            flywheel.setVelocity(viteza);
        }
        if (gamepad1.circle) {
            flywheel.setVelocity(0);
        }

        if (gamepad1.left_trigger > 0.33) {
            blocker.setPosition(0);
        }
        else if (gamepad1.right_trigger > 0.33) {
            blocker.setPosition(1);
        }


        telemetry.addData("viteza flywheel", flywheel.getVelocity());
        telemetry.addData("blocker pozitie", blocker.getPosition());
        telemetry.update();
    }

    @Override
    public void stop() {
        flywheel.setVelocity(0);
    }
}
