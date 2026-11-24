package org.firstinspires.ftc.teamcode.Dagau_Stefan.problema3;

public class Settings {
    //sensor
    public static final int GAIN = 4;

    //thresholds
    public static final double MIN_INTENSITY_RED1 = 350;
    public static final double MIN_INTENSITY_RED2 = 10;

    public static final double MIN_INTENSITY_BLUE = 190;
    public static final double MAX_INTENSITY_BLUE = 245;
    public static final double MIN_SATURATION = 65;
    public static final double MIN_VALUE = 60;

    //color types
    public enum DetectedColors {
        RED,
        BLUE,
        UNKNOWN
    }
    //servo
    public static final double SERVO_NEUTRAL = 0;
    public static final double SERVO_RED = 0.5;
    public static final double SERVO_BLUE = 1;

    //time
    public static final double DEBOUNCE_SECONDS = 0.5;
    public static final double SORT_HOLD_SECONDS = 1;
}
