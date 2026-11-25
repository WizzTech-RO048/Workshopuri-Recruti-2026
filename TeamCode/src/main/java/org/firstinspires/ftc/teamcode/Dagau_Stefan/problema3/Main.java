package org.firstinspires.ftc.teamcode.Dagau_Stefan.problema3;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;


@TeleOp
public class Main extends OpMode {

    private StateManager stateManager = new StateManager();

    @Override
    public void init() {
        stateManager.init(hardwareMap);
    }
    @Override
    public void loop() {
        stateManager.checkIfNeededSorting();
        stateManager.checkIfReseting(gamepad1.a);
        stateManager.startAction();

        NormalizedRGBA colors = Components.getColor();
        float nR = colors.red/colors.alpha;
        float nG = colors.green/colors.alpha;
        float nB = colors.blue/colors.alpha;

        telemetry.addData("blue:", stateManager.getBlueCount());
        telemetry.addData("red:", stateManager.getRedCount());
        telemetry.addData("state:", stateManager.getState());
        telemetry.addData("color red:", nR);
        telemetry.addData("color green:", nG);
        telemetry.addData("color blue:", nB);
        telemetry.addData("confirmed Color:", stateManager.getConfirmedColor());
        telemetry.addData("raw Color:", stateManager.getRawColor());

        telemetry.update();
    }
}
