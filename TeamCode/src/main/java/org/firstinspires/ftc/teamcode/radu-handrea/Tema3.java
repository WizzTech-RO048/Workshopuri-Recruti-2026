package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "Color Sorter", group = "opMode")
public class ColorSenzor extends OpMode {

    public enum ColorType {
        RED,
        BLUE,
        GREEN,
        UNKNOWN
    }

    private enum SorterState {
        IDLE,
        HOLDING
    }

    float SENSOR_GAIN = 10f;
    float MIN_ALPHA = 0.7f;
    float DOMINANCE_MARGIN = 0.15f;

    double DEBOUNCE_SECONDS = 0.08;
    double SORT_HOLD_SECONDS = 0.60;

    double POWER_NEUTRAL = 0.0;
    ouble POWER_RED = -0.5;
    double POWER_GREEN = 1.0;
    double POWER_BLUE = 0.5;

    NormalizedColorSensor colorSensor;
    DcMotorEx motor;

    ColorType detectedColor = ColorType.UNKNOWN;
    ColorType candidateColor = ColorType.UNKNOWN;
    ColorType confirmedColor = ColorType.UNKNOWN;
    SorterState state = SorterState.IDLE;

    private final ElapsedTime debounceTimer = new ElapsedTime();
    private final ElapsedTime holdTimer = new ElapsedTime();

    int redCount = 0;
    int blueCount = 0;
    boolean prevResetPressed = false;

    @Override
    public void init() {
        colorSensor = hardwareMap.get(NormalizedColorSensor.class, "ColorSensor");
        colorSensor.setGain(SENSOR_GAIN);

        motor = hardwareMap.get(DcMotorEx.class, "motor");
        motor.setPower(POWER_NEUTRAL);
        debounceTimer.reset();
    }

    @Override
    public void loop() {
        if (gamepad1.a && !prevResetPressed) {
            redCount = 0;
            blueCount = 0;
        }
        prevResetPressed = gamepad1.a;

        NormalizedRGBA colors = colorSensor.getNormalizedColors();
        float alpha = colors.alpha;

        float normRed = 0f;
        float normGreen = 0f;
        float normBlue = 0f;

        if (alpha > 0f) {
            normRed = colors.red / alpha;
            normGreen = colors.green / alpha;
            normBlue = colors.blue / alpha;
        }

        ColorType rawColor = classifyColor(alpha, normRed, normGreen, normBlue);
        updateDebounce(rawColor);

        switch (state) {
            case IDLE:
                motor.setPower(POWER_NEUTRAL);
                if (confirmedColor == ColorType.RED) {
                    redCount++;
                    motor.setPower(POWER_RED);
                    holdTimer.reset();
                    state = SorterState.HOLDING;
                } else if (confirmedColor == ColorType.BLUE) {
                    blueCount++;
                    motor.setPower(POWER_BLUE);
                    holdTimer.reset();
                    state = SorterState.HOLDING;
                } else if (confirmedColor == ColorType.GREEN) {
                    motor.setPower(POWER_GREEN);
                    holdTimer.reset();
                    state = SorterState.HOLDING;
                }
                break;

            case HOLDING:
                if (holdTimer.seconds() >= SORT_HOLD_SECONDS) {
                    motor.setPower(POWER_NEUTRAL);
                    state = SorterState.IDLE;
                }
                break;
        }

        detectedColor = confirmedColor;

        telemetry.addData("State", state);
        telemetry.addData("DetectedColor", detectedColor);
        telemetry.addData("Alpha", alpha);
        telemetry.addData("Norm R/G/B"", normRed, normGreen, normBlue);
        telemetry.addData("Counts R/B", "%d / %d", redCount, blueCount);
    }

    private ColorType classifyColor(float alpha, float r, float g, float b) {
        if (alpha < MIN_ALPHA) {
            return ColorType.UNKNOWN;
        }
        if (r > g + DOMINANCE_MARGIN && r > b + DOMINANCE_MARGIN) {
            return ColorType.RED;
        }
        if (b > r + DOMINANCE_MARGIN && b > g + DOMINANCE_MARGIN) {
            return ColorType.BLUE;
        }
        if (g > r + DOMINANCE_MARGIN && g > b + DOMINANCE_MARGIN) {
            return ColorType.GREEN;
        }
        return ColorType.UNKNOWN;
    }

    private void updateDebounce(ColorType rawColor) {
        if (rawColor != candidateColor) {
            candidateColor = rawColor;
            debounceTimer.reset();
        } else if (debounceTimer.seconds() >= DEBOUNCE_SECONDS) {
            confirmedColor = candidateColor;
        }
    }
}
