package org.firstinspires.ftc.teamcode.xeniasuciu;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class MainTeleOp extends OpMode  {

        DcMotorEx motor1;
    DcMotorEx motor2;
    DcMotorEx motor3;
    DcMotorEx motor4;

        boolean on_off = false;

        @Override
        public void init() {
            motor1 = hardwareMap.get(DcMotorEx.class, "motordreaptasus");
            motor2 = hardwareMap.get(DcMotorEx.class, "motordreaptajos");
            motor3 = hardwareMap.get(DcMotorEx.class, "motorstangasus");
            motor4 = hardwareMap.get(DcMotorEx.class, "motorstangajos");
            motor1.setDirection(DcMotorSimple.Direction.REVERSE);
            motor2.setDirection(DcMotorSimple.Direction.REVERSE);

        }

        @Override
        public void loop() {
            double x= gamepad1.left_stick_x*1.1;
            double y= -gamepad1.left_stick_y;
            double rx= gamepad1.right_stick_x;

            double mecanismcomplex=Math.max(Math.abs(y)+Math.abs(x)+Math.abs(rx),1);
            double a=((y-x-rx)/mecanismcomplex);
            double b=((x+y-rx)/mecanismcomplex);
            double c=((y-x+rx)/mecanismcomplex);
            double d=((x+y+rx)/mecanismcomplex);
            motor1.setPower(Math.pow(a,3));
            motor2.setPower(Math.pow(b,3));
            motor3.setPower(Math.pow(c,3));
            motor4.setPower(Math.pow(d,3));

        }
    }