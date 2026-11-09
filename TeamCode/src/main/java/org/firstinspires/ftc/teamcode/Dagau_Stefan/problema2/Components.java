package org.firstinspires.ftc.teamcode.Dagau_Stefan.problema2;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Components {
    private Servo servo;
    private DcMotorEx motor;

    public void init(HardwareMap hdmap) {
        servo = hdmap.get(Servo.class, "Servo");
        motor = hdmap.get(DcMotorEx.class, "motor");
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void setServoPosition(double position) {
        servo.setPosition(position);
    }

    public void setMotorSpeed(double velocity) {
        motor.setVelocity(velocity);
    }
}
