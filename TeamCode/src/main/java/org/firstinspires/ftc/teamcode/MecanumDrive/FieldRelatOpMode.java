package org.firstinspires.ftc.teamcode.MecanumDrive;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
@Disabled
@TeleOp(name = "Mecanum Drive TeleOp")
public class FieldRelatOpMode extends OpMode {
    MecanumDrive drive = new MecanumDrive();
    double fwd, strf, rot;

    @Override
    public void init() {
        drive.init(hardwareMap)
    }

    @Override
    public void loop() {
        fwd = gamepad1.left_stick_y;
        strf = gamepad1.left_stick_x;
        rot = gamepad1.right_stick_x;

        drive.driveFieldRelat(fwd,strf,rot);
    }
}
