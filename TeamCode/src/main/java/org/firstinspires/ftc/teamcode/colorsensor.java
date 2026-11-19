package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com .qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;



@TeleOp
public class colorsensor extends OpMode{
    public enum colortype{Red,Blue,Green,Unknown}
    public colortype Detectedcolor;
    public DcMotorEx motor;
    NormalizedColorSensor colorsensor;
    @Override
    public void init(){
        colorsensor= hardwareMap.get(NormalizedColorSensor.class,"colorsensor");
        colorsensor.setGain(10);
        motor = hardwareMap.get(DcMotorEx.class, "motor");
        motor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        motor.setVelocity(0);

    }
    @Override
    public void loop(){
        NormalizedRGBA colors= colorsensor.getNormalizedColors();
        float Alpha, Normred, Normgreen, Normblue;
        Alpha= colors.alpha;
        Normred= colors.red/Alpha;
        Normgreen= colors.green / Alpha;
        Normblue= colors.blue / Alpha;
/*
        if(Normred>Normblue && Normred>Normgreen && Normred>0.4){
            Detectedcolor=colortype.Red;
        }else if(Normblue>Normred && Normblue>Normgreen && Normblue>0.4){
            Detectedcolor=colortype.Blue;
        }else if(Normgreen>Normred && Normgreen>Normblue && Normgreen>0.4){
            Detectedcolor=colortype.Green;
        }else {
            Detectedcolor=colortype.Unknown;
        }
 */
        if(Alpha>0.7 && 2*Normred> Normblue+Normgreen){
            Detectedcolor=colortype.Red;
            motor.setVelocity(-0.5);
        }else if(Alpha>0.7 && 2*Normblue>Normgreen+Normred){
            Detectedcolor=colortype.Blue;
            motor.setVelocity(0.5);
        }else if(Alpha>0.7 && 2*Normgreen>Normred+Normblue){
            Detectedcolor=colortype.Green;
            motor.setVelocity(1);
        }else {
            Detectedcolor=colortype.Unknown;
            motor.setVelocity(0);
        }
        

        telemetry.addData("Red", Normred);
        telemetry.addData("Green",Normgreen);
        telemetry.addData("Blue", Normblue);
        telemetry.addData("Alpha", Alpha);
        telemetry.addData("Detectedcolor",Detectedcolor);
        telemetry.update();
    }
}
