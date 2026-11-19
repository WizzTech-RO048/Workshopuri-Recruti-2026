package org.firstinspires.ftc.teamcode.TurretCtrl;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
public class shooterCntrl {
    private DcMotorEx flywheel;
    private Servo blocker;
    public void init(HardwareMap hwMap){
        blocker = hwMap.get(Servo.class, "servo");
        flywheel = hwMap.get(DcMotorEx.class, "flywheel");
        flywheel.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        blocker.setPosition(0);
    }
    public void open(){blocker.setPosition(1);}
    public void close(){blocker.setPosition(0);}
    public void spinUp(){flywheel.setVelocity(1500);}
    public void spinDown(){flywheel.setVelocity(0);}
    public double servoPosition(){return blocker.getPosition();}
    public double motorVelocity(){return flywheel.getVelocity();}
}
