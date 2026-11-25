package org.firstinspires.ftc.teamcode.Dagau_Stefan.problema3;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.Set;

public class StateManager {
    public enum States {
        NEUTRAL,
        SORTING,
        DETECTING,
        RESETING
    }

    private final ElapsedTime sortingTimer = new ElapsedTime();
    private final ElapsedTime debounceTimer = new ElapsedTime();
    private States state = States.NEUTRAL;
    private Settings.DetectedColors previousColor = Settings.DetectedColors.UNKNOWN;
    private Settings.DetectedColors newColor = Settings.DetectedColors.UNKNOWN;

    //asta este complet optional si este doar de vazut ce culoare a fost confirmata
    //--------------------------------------------------------------------------------------
    private Settings.DetectedColors confirmedColor = Settings.DetectedColors.UNKNOWN;
    //--------------------------------------------------------------------------------------

    private boolean reseted = false;


    private int redCount = 0;
    private int blueCount = 0;

    public void init(HardwareMap hwmap) {
        Components.init(hwmap);
    }
    public void checkIfNeededSorting() {
        NormalizedRGBA rawcolor = Components.getColor();
        newColor = Components.classifyColor(rawcolor);

        if (newColor != Settings.DetectedColors.UNKNOWN) {
            state = States.DETECTING;
        }

        if (newColor != previousColor || state == States.SORTING) {
            previousColor = newColor;
            debounceTimer.reset();
        }

        if (debounceTimer.seconds() >= Settings.DEBOUNCE_SECONDS && state == States.DETECTING) {
            state = States.SORTING;
            addColorObject(newColor);
            confirmedColor = newColor;
            sortingTimer.reset();
        }
    }

    public void checkIfReseting(boolean event) {
        if (event && !reseted) {
            state = States.RESETING;
            reseted = true;
        }
        //if it has reseted, check until the event is 'false' and turn it to true to turn back reseted into false
        if (!event) {
            state = States.NEUTRAL;
            reseted = false;
        }
    }

    public void startAction() {
        //does an action based on its state
        switch (state) {
            case RESETING: {
                blueCount = 0;
                redCount = 0;
                break;
            }
            case SORTING: {
                if (sortingTimer.seconds() >= Settings.SORT_HOLD_SECONDS) {
                    state = States.NEUTRAL;
                    Components.changeServoPos(Settings.DetectedColors.UNKNOWN);
                }
                break;
            }
        }
    }

    private void addColorObject(Settings.DetectedColors color) {
        if (color == Settings.DetectedColors.RED) redCount++;
        else blueCount++;
        Components.changeServoPos(color);
    }

    //telemetry stuff
    public int getRedCount(){
        return redCount;
    }

    public int getBlueCount() {
        return blueCount;
    }
    public States getState() {
        return state;
    }

    public Settings.DetectedColors getRawColor() {
        return newColor;
    }

    public Settings.DetectedColors getConfirmedColor() {
        return confirmedColor;
    }
}
