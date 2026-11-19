package org.firstinspires.ftc.teamcode.David_Oros;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

@TeleOp
public class ColorSensor extends OpMode{
    NormalizedColorSensor colorSensor;
    public enum colorType{
        RED,
        GREEN,
        BLUE,
        UNKNOWN,
    }
 colorType detectedColor;
    DcMotorEx motor;

    @Override
public void init(){
   colorSensor=hardwareMap.get(NormalizedColorSensor.class,"colorSensor");
   colorSensor.setGain(10);
   motor=hardwareMap.get(DcMotorEx.class,"motor");
}
@Override
    public void loop(){
        NormalizedRGBA colors=colorSensor.getNormalizedColors();
        float normred,normgreen,normblue,alpha;
        alpha=colors.alpha;
        normred=colors.red/alpha;
        normblue=colors.blue/alpha;
        normgreen=colors.green/alpha;
        detectedColor=colorType.UNKNOWN;

        if(normred>0.5&&normblue<0.5&&normgreen<0.5&&alpha>0.8)
        { detectedColor=colorType.RED;
        motor.setPower(-0.5);}
            else if(normred<0.5&&normblue>0.5&&normgreen<0.5&&alpha>0.8)
    {detectedColor=colorType.BLUE;
            motor.setPower(0.5);}
                else if (normred<0.5&&normblue<0.5&&normgreen>0.5&&alpha>0.8)
    { detectedColor=colorType.GREEN;
                    motor.setPower(1);}

        telemetry.addData("Alpha",alpha);
        telemetry.addData("Red",normred);
        telemetry.addData("Blue",normblue);
        telemetry.addData("Green",normgreen);
        telemetry.addData("Detected Colour",detectedColor);
        telemetry.update();
    }
 }
