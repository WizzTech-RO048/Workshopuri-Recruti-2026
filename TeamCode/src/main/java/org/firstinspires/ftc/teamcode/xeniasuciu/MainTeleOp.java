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
    double n;

        boolean on_off = false;

        @Override
        public void init() {
            motor1 = hardwareMap.get(DcMotorEx.class, "motordreaptasus");
            motor2 = hardwareMap.get(DcMotorEx.class, "motordreaptajos");
            motor3 = hardwareMap.get(DcMotorEx.class, "motorstangasus");
            motor4 = hardwareMap.get(DcMotorEx.class, "motorstangajos");

        }

        @Override
        public void loop() {
            if (Math.abs(gamepad1.left_stick_y)>Math.abs(gamepad1.left_stick_x) ) {

                if (gamepad1.left_stick_y<-0.2 ) {
                    motor1.setDirection(DcMotorSimple.Direction.REVERSE);
                    motor2.setDirection(DcMotorSimple.Direction.REVERSE);
                    motor3.setDirection(DcMotorSimple.Direction.REVERSE);
                    motor4.setDirection(DcMotorSimple.Direction.REVERSE);
                }
                if (gamepad1.left_stick_y>0.2 ) {
                    motor2.setDirection(DcMotorSimple.Direction.FORWARD);
                    motor1.setDirection(DcMotorSimple.Direction.FORWARD);
                    motor3.setDirection(DcMotorSimple.Direction.FORWARD);
                    motor4.setDirection(DcMotorSimple.Direction.FORWARD);
                }
            }else if(Math.abs(gamepad1.left_stick_x)>Math.abs(gamepad1.left_stick_y) ){
                if (gamepad1.left_stick_x>0.2 ) {
                    motor1.setDirection(DcMotorSimple.Direction.REVERSE);
                    motor2.setDirection(DcMotorSimple.Direction.FORWARD);
                    motor3.setDirection(DcMotorSimple.Direction.FORWARD);
                    motor4.setDirection(DcMotorSimple.Direction.REVERSE);
                }
                if (gamepad1.left_stick_x<-0.2 ) {
                    motor1.setDirection(DcMotorSimple.Direction.FORWARD);
                    motor2.setDirection(DcMotorSimple.Direction.REVERSE);
                    motor3.setDirection(DcMotorSimple.Direction.REVERSE);
                    motor4.setDirection(DcMotorSimple.Direction.FORWARD);
                }
            }
            if (gamepad1.right_stick_x<-0.2 ) {
                motor1.setDirection(DcMotorSimple.Direction.FORWARD);
                motor2.setDirection(DcMotorSimple.Direction.FORWARD);
                motor3.setDirection(DcMotorSimple.Direction.REVERSE);
                motor4.setDirection(DcMotorSimple.Direction.REVERSE);
            } else if (gamepad1.right_stick_x>0.2 ) {
                motor1.setDirection(DcMotorSimple.Direction.REVERSE);
                motor2.setDirection(DcMotorSimple.Direction.REVERSE);
                motor3.setDirection(DcMotorSimple.Direction.FORWARD);
                motor4.setDirection(DcMotorSimple.Direction.FORWARD);
            }

            if ( Math.abs(gamepad1.left_stick_y) >0.2) {
                n = Math.sqrt(Math.pow(gamepad1.left_stick_y,2)+Math.pow(gamepad1.left_stick_x,2));
                motor1.setPower(n);
                motor2.setPower(n);
                motor3.setPower(n);
                motor4.setPower(n);
            } else if (Math.abs(gamepad1.left_stick_x)>0.2 ){
                n=Math.sqrt(Math.pow(gamepad1.left_stick_y,2)+Math.pow(gamepad1.left_stick_x,2));
                motor1.setPower(n);
                motor2.setPower(n);
                motor3.setPower(n);
                motor4.setPower(n);
            } else if (Math.abs(gamepad1.right_stick_x)>0.2 ){
                n=Math.sqrt(Math.pow(gamepad1.right_stick_y,2)+Math.pow(gamepad1.right_stick_x,2));
                motor1.setPower(n);
                motor2.setPower(n);
                motor3.setPower(n);
                motor4.setPower(n);
            } else {
                motor1.setPower(0);
                motor2.setPower(0);
                motor3.setPower(0);
                motor4.setPower(0);
            }

        }
    }