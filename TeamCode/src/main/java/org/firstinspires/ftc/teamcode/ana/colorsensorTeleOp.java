package org.firstinspires.ftc.teamcode.ana;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

@TeleOp
public class colorsensorTeleOp extends OpMode {

    NormalizedColorSensor colorsensor;
    DcMotorEx motor;
    public enum colorType {
        RED,
        BLUE,
        GREEN,
        UNKNOWN,
    }
    //rosu,unknown nu se invarte
    //verde -0.5
    //albastru 1

    colorType detectedcolor;

    @Override
    public void init() {
        colorsensor = hardwareMap.get(NormalizedColorSensor.class,"senzor");
        colorsensor.setGain(8);
        motor = hardwareMap.get(DcMotorEx.class,"motor");
    }

    @Override
    public void loop() {
        NormalizedRGBA colors = colorsensor.getNormalizedColors();
        float alpha,normred,normblue,normgreen;
        alpha = colors.alpha;
        normred = colors.red/alpha;
        normblue = colors.blue/alpha;
        normgreen = colors.green/alpha;

        telemetry.addData("alpha",alpha);
        telemetry.addData("red",normred);
        telemetry.addData("blue",normblue);
        telemetry.addData("green",normgreen);
        telemetry.addData("Detected Color",detectedcolor);
        telemetry.update();

        if(normred>normblue && normred>normgreen && normred>0.1)
        {
            detectedcolor = colorType.RED;
            motor.setPower(0);
        }
        else if(normblue>normred && normblue>normgreen && normblue>0.1)
        {
            detectedcolor = colorType.BLUE;
            motor.setPower(1);
        }
        else if (normgreen>normred && normgreen>normblue && normgreen>0.1)
        {
            detectedcolor = colorType.GREEN;
            motor.setPower(-0.5);

        }
        else
            detectedcolor = colorType.UNKNOWN;
            motor.setPower(0);
    }
}
