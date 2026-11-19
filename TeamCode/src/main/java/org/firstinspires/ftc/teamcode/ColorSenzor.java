package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

public class ColorSenzor  extends OpMode {

    NormalizedColorSensor ColorSensor;
    DcMotorEx motor;

    public enum colortype {Red,Blue,Green,Unknown}
    public colortype DetectedColor;

    @Override
    public void init(){
        ColorSensor=hardwareMap.get(NormalizedColorSensor.class, "ColorSensor");
        ColorSensor.setGain(10);
        motor=hardwareMap.get(DcMotorEx.class, "motor");
    }

    @Override
    public void loop(){
        NormalizedRGBA Colors=ColorSensor.getNormalizedColors();

        float NormRed;
        float NormGreen;
        float NormBlue;
        float alpha;

        alpha =Colors.alpha;
        NormRed=Colors.red/ alpha;
        NormGreen=Colors.green/ alpha;
        NormBlue=Colors.blue/ alpha;

        telemetry.addData("red", NormRed);
        telemetry.addData("blue", NormGreen);
        telemetry.addData("green",NormBlue);
        telemetry.addData("alpha",alpha);
        telemetry.addData("DetectedColor",DetectedColor);
        telemetry.update();

        if(alpha>0.7 && NormRed>0.4) {
            DetectedColor = colortype.Red;
            motor.setPower(-0.5);
        } else if (alpha>0.7 && NormGreen>0.4) {
            DetectedColor=colortype.Green;
            motor.setPower(1);
        } else if (alpha>0.7 && NormBlue>0.4) {
            DetectedColor=colortype.Blue;
            motor.setPower(0.5);
        } else {
            DetectedColor=colortype.Unknown;
            motor.setPower(0);
        }
    }
}
