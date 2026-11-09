package org.firstinspires.ftc.teamcode.Dagau_Stefan;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorImpl;

import org.firstinspires.ftc.onbotjava.handlers.objbuild.WaitForBuild;

@TeleOp(name=":D")
public class MainTeleOp2 extends LinearOpMode {
    private DcMotorImpl motor;

    @Override
    public void runOpMode() {
        motor = hardwareMap.get(DcMotorImpl.class, "motorname");
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setTargetPosition(700);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        waitForStart();
        while (opModeIsActive()) {
            motor.setPower(0.5);
            if (!motor.isBusy()) {
                motor.setPower(0);
            }
            telemetry.addData("speed", motor.getCurrentPosition());
            telemetry.update();
        }
    }
}
