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

        }

        @Override
        public void loop() {
            if ( gamepad1.left_stick_y>0.2 ) {

                if (gamepad1.right_stick_x>0.2 ) {
                    motor2.setDirection(DcMotorSimple.Direction.REVERSE);
                    motor1.setDirection(DcMotorSimple.Direction.REVERSE);
                }else{
                    motor2.setDirection(DcMotorSimple.Direction.FORWARD);
                    motor1.setDirection(DcMotorSimple.Direction.FORWARD);
                }
                if (gamepad1.right_stick_x<-0.2 ) {
                    motor4.setDirection(DcMotorSimple.Direction.REVERSE);
                    motor3.setDirection(DcMotorSimple.Direction.REVERSE);
                }else{
                    motor4.setDirection(DcMotorSimple.Direction.FORWARD);
                    motor3.setDirection(DcMotorSimple.Direction.FORWARD);

                }
            }else if(gamepad1.left_stick_y<-0.2 ){
                if (gamepad1.right_stick_x>0.2 ) {
                    motor1.setDirection(DcMotorSimple.Direction.FORWARD);
                    motor2.setDirection(DcMotorSimple.Direction.FORWARD);
                }else{
                    motor1.setDirection(DcMotorSimple.Direction.REVERSE);
                    motor2.setDirection(DcMotorSimple.Direction.REVERSE);
                }
                if (gamepad1.right_stick_x<-0.2 ) {
                    motor3.setDirection(DcMotorSimple.Direction.FORWARD);
                    motor4.setDirection(DcMotorSimple.Direction.FORWARD);
                }else{
                    motor3.setDirection(DcMotorSimple.Direction.REVERSE);
                    motor4.setDirection(DcMotorSimple.Direction.REVERSE);
                }

            }
            if ( gamepad1.left_stick_y>0.2 || gamepad1.left_stick_y<-0.2) {
                motor1.setPower(gamepad1.left_stick_y*gamepad1.left_stick_y);
                motor2.setPower(gamepad1.left_stick_y*gamepad1.left_stick_y);
                motor3.setPower(gamepad1.left_stick_y*gamepad1.left_stick_y);
                motor4.setPower(gamepad1.left_stick_y*gamepad1.left_stick_y);
            }else{
                motor1.setPower(0);
                motor2.setPower(0);
                motor3.setPower(0);
                motor4.setPower(0);
            }
        }
    }