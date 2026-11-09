package org.firstinspires.ftc.teamcode.Dagau_Stefan.problema2;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class Main extends OpMode {

    private Components components = new Components();
    @Override
    public void init() {
        components.init(hardwareMap);
    }

    @Override
    public void loop() {
        if (gamepad1.left_trigger_pressed) {
            components.setServoPosition(0);
        } else if (gamepad1.right_trigger_pressed) {
            components.setServoPosition(1);
        }

        if (gamepad1.xWasPressed()) {
            components.setMotorSpeed(1500);
        } else if (gamepad1.bWasPressed()) {
            components.setMotorSpeed(0);
        }
    }
}
