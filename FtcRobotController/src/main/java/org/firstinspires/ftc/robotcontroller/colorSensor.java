package org.firstinspires.ftc.robotcontroller;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

@TeleOp
public class colorSensor extends OpMode {
    NormalizedColorSensor colorSensor;
    public enum colorType{
        RED,
        BLUE,
        GREEN,
        UNKNOWN
    }
     colorType detectedcolor;
     private DcMotorEx Motor;
    @Override
    public void init(){
        colorSensor=hardwareMap.get(NormalizedColorSensor.class, "colorSensor");
        colorSensor.setGain(10);
    }
    @Override
    public void loop(){
        NormalizedRGBA colors=colorSensor.getNormalizedColors();
        float Normred,Normgreen,Normblue,alpha;
        alpha=colors.alpha;
        Normred=colors.red/alpha;
        Normgreen=colors.green/alpha;
        Normblue=colors.blue/alpha;
        detectedcolor=colorType.UNKNOWN;
        telemetry.addData("alpha",alpha);
        telemetry.addData("red",Normred);
        telemetry.addData("green",Normgreen);
        telemetry.addData("blue",Normblue);
        telemetry.addData("detectedcolor",detectedcolor);
        if (alpha > 0.7) {


            if (Normred > Normblue && Normred > Normgreen) {
                detectedcolor = colorType.RED;
                Motor.setPower(-0.5);
            } else {
                if (Normblue > Normred && Normblue > Normgreen) {
                    detectedcolor = colorType.BLUE;
                    Motor.setPower(0.5);
                } else {
                    if (Normgreen > Normblue && Normgreen > Normred) {
                        detectedcolor = colorType.GREEN;
                        Motor.setPower(1);
                    }

                }
            }
        }
        else{
            Motor.setPower(0);
        }
    }
}
