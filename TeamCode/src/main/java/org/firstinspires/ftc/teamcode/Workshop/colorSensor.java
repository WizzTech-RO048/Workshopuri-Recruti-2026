package org.firstinspires.ftc.teamcode.Workshop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

@TeleOp
public class colorSensor extends OpMode {
    NormalizedColorSensor colorSensor;
    DcMotor motor;
    public enum colorType {
        RED,
        BLUE,
        GREEN,
        UNKNOWN
    }
    colorType detectedColor;
    @Override
    public void init() {
        colorSensor = hardwareMap.get(NormalizedColorSensor.class, "colorSensor");
        colorSensor.setGain(10);
        motor = hardwareMap.get(DcMotor.class, "motor");
        motor.setPower(0);

    }
    @Override
    public void loop() {
        NormalizedRGBA colors = colorSensor.getNormalizedColors();
        float normRED,normGREEN,normBLUE,alpha;
        alpha = colors.alpha;
        normRED = colors.red/alpha;
        normGREEN = colors.green/alpha;
        normBLUE = colors.blue/alpha;
        detectedColor = colorType.UNKNOWN;
        if(alpha>0.8) {
            if (normRED > normBLUE && normRED > normGREEN) {
                detectedColor = colorType.RED;
                motor.setPower(-0.5);
            } else if (normBLUE > normRED && normBLUE > normGREEN) {
                detectedColor = colorType.BLUE;
                motor.setPower(0.5);
            } else if (normGREEN > normBLUE && normGREEN > normRED) {
                detectedColor = colorType.GREEN;
                motor.setPower(1);
            }
        }
        else detectedColor = colorType.UNKNOWN; motor.setPower(0);
        telemetry.addData("Alpha", alpha);
        telemetry.addData("Blue", normBLUE);
        telemetry.addData("Red", normRED);
        telemetry.addData("Green", normGREEN);
        telemetry.addData("Det Color", detectedColor);
        telemetry.update();
    }
}
