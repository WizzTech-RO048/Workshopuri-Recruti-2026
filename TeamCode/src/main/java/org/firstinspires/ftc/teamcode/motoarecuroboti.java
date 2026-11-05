package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp

public class motoarecuroboti extends OpMode {
    DcMotorEx motor;
    public void init()
    {
        motor=hardwareMap.get(DcMotorEx.class,"motor");
    }
    public void loop()
    {

    }
}
