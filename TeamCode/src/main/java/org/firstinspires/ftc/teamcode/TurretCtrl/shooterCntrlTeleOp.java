package org.firstinspires.ftc.teamcode.TurretCtrl;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Shooter TeleOp")
public class shooterCntrlTeleOp extends OpMode {
    shooterCntrl shooter = new shooterCntrl();
    @Override
    public void init() {
        shooter.init(hardwareMap);
    }
    @Override
    public void loop() {
        if(gamepad1.cross){
            shooter.spinUp();
        }
        else if(gamepad1.circle){
            shooter.spinDown();
        }
        if(gamepad1.left_trigger_pressed){
            shooter.close();
        }
        else if(gamepad1.right_trigger_pressed) {
            shooter.open();
        }
        telemetry.addData("Flywheel Speed", shooter.motorVelocity());
        telemetry.addData("Servo Position", shooter.servoPosition());
        telemetry.update();
    }
}
