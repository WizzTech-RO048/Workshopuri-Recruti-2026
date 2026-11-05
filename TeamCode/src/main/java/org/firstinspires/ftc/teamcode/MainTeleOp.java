package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class MainTeleOp extends LinearOpMode {

    DcMotor motor1;
    DcMotor motor2;
    DcMotor motor3;
    DcMotor motor4;

    @Override
    public void runOpMode() {

        motor1 = hardwareMap.get(DcMotor.class, "motor1");
        motor2 = hardwareMap.get(DcMotor.class, "motor2");
        motor3 = hardwareMap.get(DcMotor.class, "motor3");
        motor4 = hardwareMap.get(DcMotor.class, "motor4");

        motor2.setDirection(DcMotor.Direction.REVERSE);
        motor4.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();

        while (opModeIsActive()) {

            double fata = -gamepad1.left_stick_y;
            double lateral = gamepad1.left_stick_x;
            double rotatie = gamepad1.right_stick_x;

            fata = fata * fata * fata;
            lateral = lateral * lateral * lateral;
            rotatie = rotatie * rotatie * rotatie;

            double putere1 = fata + lateral + rotatie;
            double putere2 = fata - lateral - rotatie;
            double putere3 = fata - lateral + rotatie;
            double putere4 = fata + lateral - rotatie;

            double max = Math.max(1, Math.max(
                    Math.max(Math.abs(putere1), Math.abs(putere2)),
                    Math.max(Math.abs(putere3), Math.abs(putere4))
            ));

            motor1.setPower(putere1 / max);
            motor2.setPower(putere2 / max);
            motor3.setPower(putere3 / max);
            motor4.setPower(putere4 / max);
        }
    }
}
