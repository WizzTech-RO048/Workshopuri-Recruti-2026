package org.firstinspires.ftc.teamcode.Dagau_Stefan;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class MainTeleOp extends LinearOpMode{
    //front
    private DcMotor FLmotor;
    private DcMotor FRmotor;
    //back
    private DcMotor RLmotor;
    private DcMotor RRmotor;


    @Override
    public void runOpMode() {
        FLmotor = hardwareMap.get(DcMotor.class, "motorname");
        FRmotor = hardwareMap.get(DcMotor.class, "motorname");
        RLmotor = hardwareMap.get(DcMotor.class, "motorname");
        RRmotor = hardwareMap.get(DcMotor.class, "motorname");

        telemetry.addData("Status", "sunt cool :)");
        telemetry.update();

        waitForStart();
        while (opModeIsActive()) {
            double fwd = -gamepad1.left_stick_y;
            double strafe = gamepad1.left_stick_x;
            double rot = gamepad1.right_stick_x;

            double FL = fwd + strafe + rot;
            double RL = fwd + strafe - rot;
            double FR = fwd - strafe - rot;
            double RR = fwd - strafe + rot;

            double deliniarizare = Math.max(Math.max(FL, RL), Math.max(FR, RR));
            deliniarizare = deliniarizare > 1 ? deliniarizare : 1;

            FLmotor.setPower(FL/deliniarizare);
            FRmotor.setPower(FR/deliniarizare);
            RLmotor.setPower(RL/deliniarizare);
            RRmotor.setPower(RR/deliniarizare);
        }
    }

}
