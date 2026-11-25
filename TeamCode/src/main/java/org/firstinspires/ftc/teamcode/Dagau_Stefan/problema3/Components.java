package org.firstinspires.ftc.teamcode.Dagau_Stefan.problema3;

import android.graphics.Color;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.Servo;

public class Components {
    private static NormalizedColorSensor sensor;
    private static Servo servo;
    public static void init(HardwareMap hwmap) {
        sensor = hwmap.get(NormalizedColorSensor.class, "color_sensor");
        servo = hwmap.get(Servo.class, "sorter_servo");
        sensor.setGain(Settings.GAIN);
    }

    public static NormalizedRGBA getColor() {
        //return only the color
        return sensor.getNormalizedColors();
    }

    public static Settings.DetectedColors classifyColor(NormalizedRGBA rgb) {
        int color = rgb.toColor();
        final float[] hsv = new float[3];

        Color.colorToHSV(color, hsv);

        float hue = hsv[0];
        float saturation = hsv[1];
        float value = hsv[2];

        //check if its bright enough or pure enough using saturation and value
        if (saturation <= Settings.MIN_SATURATION) return Settings.DetectedColors.UNKNOWN;
        if (value <= Settings.MIN_VALUE) return Settings.DetectedColors.UNKNOWN;

        //return the color
        if (hue >= Settings.MIN_INTENSITY_RED1) return Settings.DetectedColors.RED;
        else if (Settings.MIN_INTENSITY_RED2 >= hue) return Settings.DetectedColors.RED;
        else if (Settings.MIN_INTENSITY_BLUE <= hue && hue <= Settings.MAX_INTENSITY_BLUE) {
            return Settings.DetectedColors.BLUE;
        }
        else return Settings.DetectedColors.UNKNOWN;
    }

    public static void changeServoPos(Settings.DetectedColors color) {
        if (color == Settings.DetectedColors.BLUE) servo.setPosition(Settings.SERVO_BLUE);
        else if (color == Settings.DetectedColors.RED) servo.setPosition(Settings.SERVO_RED);
        else servo.setPosition(Settings.SERVO_NEUTRAL);
    }
}
