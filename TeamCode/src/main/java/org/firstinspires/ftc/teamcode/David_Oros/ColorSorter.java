package org.firstinspires.ftc.teamcode.David_Oros;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp
public class ColorSorter extends OpMode {
    private static final float MIN_INTENSITY = 0.15f;
    private static final float DOMINANCE_MARGIN = 0.10f;
    private static final double DEBOUNCE_SECONDS=0.15;
    private static final double SORT_HOLD_SECONDS=0.40;
    private static final double SERVO_NEUTRAL=0.50;
    private static final double SERVO_RED=0.20;
    private static final double SERVO_BLUE=0.80;
    NormalizedColorSensor colorSensor;
    Servo sorterServo;
    public enum DetectedColor{
        RED,
        BLUE,
        NONE
    }
    public enum SorterState{
        WAITING,
        SORTING
    }
    DetectedColor rawColor=DetectedColor.NONE;
    DetectedColor candidateColor=DetectedColor.NONE;
    DetectedColor confirmedColor=DetectedColor.NONE;
    SorterState sorterState=SorterState.WAITING;
    ElapsedTime debounceTimer=new ElapsedTime();
    ElapsedTime sortTimer=new ElapsedTime();
    int redCount=0;
    int blueCount=0;

    boolean waitingForClear=false;
    boolean previousA=false;
    String lastAction="Nicio piesa sortata";
    @Override
    public void init(){
        colorSensor=hardwareMap.get(NormalizedColorSensor.class,"color_sensor");
        sorterServo=hardwareMap.get(Servo.class,"sorter_servo");

        colorSensor.setGain(10);
        sorterServo.setPosition(SERVO_NEUTRAL);

        debounceTimer.reset();
        sortTimer.reset();

        telemetry.addLine("Color Sorter pregatit");
        telemetry.update();

    }
@Override
    public void loop() {
    NormalizedRGBA colors = colorSensor.getNormalizedColors();

    float red = colors.red;
    float green = colors.green;
    float blue = colors.blue;
    float alpha = colors.alpha;

    rawColor = classifyColor(red, green, blue);

    updateConfirmedColor();
    handleSorter();
    resetCounters();

    telemetry.addData("Red", red);
    telemetry.addData("Green", green);
    telemetry.addData("Blue", blue);
    telemetry.addData("Alpha", alpha);
    telemetry.addData("Culoare brută", rawColor);
    telemetry.addData("Culoare confirmată", confirmedColor);
    telemetry.addData("Stare", sorterState);
    telemetry.addData("Piese roșii", redCount);
    telemetry.addData("Piese albastre", blueCount);
    telemetry.addData("Ultima acțiune", lastAction);
    telemetry.update();
}
    private DetectedColor classifyColor(float red,float green,float blue) {
        float intensity=red+green+blue;

        if(intensity<MIN_INTENSITY) {
            return DetectedColor.NONE;
        }

        if(red>blue+DOMINANCE_MARGIN&&red>green+DOMINANCE_MARGIN) {
            return DetectedColor.RED;
        }

        if(blue>red+DOMINANCE_MARGIN&&blue>green+DOMINANCE_MARGIN) {
            return DetectedColor.BLUE;
        }

        return DetectedColor.NONE;
    }

    private void updateConfirmedColor() {
        if(rawColor!=candidateColor) {
            candidateColor=rawColor;
            debounceTimer.reset();
        }

        if(debounceTimer.seconds()>=DEBOUNCE_SECONDS) {
            confirmedColor=candidateColor;
        }
    }

    private void handleSorter() {
        if(confirmedColor==DetectedColor.NONE) {
            waitingForClear=false;
        }

        if(sorterState==SorterState.WAITING) {
            if(!waitingForClear&&confirmedColor==DetectedColor.RED) {
                sorterServo.setPosition(SERVO_RED);
                redCount++;
                waitingForClear=true;
                sorterState=SorterState.SORTING;
                sortTimer.reset();
                lastAction="Piesa rosie deviata";
            } else if(!waitingForClear&&confirmedColor==DetectedColor.BLUE) {
                sorterServo.setPosition(SERVO_BLUE);
                blueCount++;
                waitingForClear=true;
                sorterState=SorterState.SORTING;
                sortTimer.reset();
                lastAction="Piesa albastra deviata";
            }
        }

        if(sorterState==SorterState.SORTING
                &&sortTimer.seconds()>=SORT_HOLD_SECONDS) {
            sorterServo.setPosition(SERVO_NEUTRAL);
            sorterState=SorterState.WAITING;
        }
    }

    private void resetCounters() {
        if(gamepad1.a&&!previousA) {
            redCount=0;
            blueCount=0;
            lastAction="Contoare resetate";
        }

        previousA=gamepad1.a;
    }
}

