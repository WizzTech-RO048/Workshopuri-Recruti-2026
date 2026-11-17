package org.firstinspires.ftc.teamcode.palex;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;


@TeleOp
public class colorsensor extends OpMode{

    NormalizedColorSensor colorsensor;
    DcMotorEx motor;
    public enum ColorType{
        Red,
        Green,
        Blue,
        Unknown
    }
    ColorType detectedColor;

    @Override
    public void init(){
        colorsensor = hardwareMap.get(NormalizedColorSensor.class, "colorsensor");
        motor = hardwareMap.get(DcMotorEx.class, "motor");
        colorsensor.setGain(8);
    }

    @Override
    public void loop(){
        NormalizedRGBA colors = colorsensor.getNormalizedColors();
        float alpha, normred, normgreen, normblue;
        alpha = colors.alpha;
        normred = colors.red/alpha;
        normgreen =  colors.green/alpha;
        normblue = colors.blue/alpha;
        if(normred > normgreen && normred > normblue && normred > 0.1){
            detectedColor = ColorType.Red;
            motor.setPower(0);
        }
        else if(normgreen > normred && normred > normblue && normgreen > 0.1){
            detectedColor = ColorType.Green;
            motor.setPower(-0.5);
        }
        else if(normblue > normred && normblue > normgreen && normblue > 0.1){
            detectedColor = ColorType.Blue;
            motor.setPower(1);
        }
        else{
            detectedColor = ColorType.Unknown;
            motor.setPower(0);
        }
        telemetry.addData("alpha", alpha);
        telemetry.addData("red", normred);
        telemetry.addData("green", normgreen);
        telemetry.addData("blue", normblue);
        telemetry.addData("detectedColor", detectedColor);
        telemetry.update();


    }
}