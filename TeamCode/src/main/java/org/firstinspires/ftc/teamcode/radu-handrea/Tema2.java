package teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(group = "opModes", name = "Tema2")
public class Tema2 extends LinearOpMode {

    DcMotor flywheel;
    Servo blockServo;

    private static final double SERVO_OPEN_POS = 1.0;
    private static final double SERVO_CLOSED_POS = 0.0;

    @Override
    public void runOpMode() throws InterruptedException {

        flywheel = hardwareMap.get(DcMotor.class, "flywheel");
        blockServo = hardwareMap.get(Servo.class, "blockServo");

        flywheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        flywheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {

            if (gamepad1.x) {
                flywheel.setVelocity(1500); 
            } else if (gamepad1.b) {
                flywheel.setPower(0);
            }

            if (gamepad1.left_trigger > 0.5) {
                blockServo.setPosition(SERVO_OPEN_POS);
            } else if (gamepad1.right_trigger > 0.5) {
                blockServo.setPosition(SERVO_CLOSED_POS);
            }

        }
    }
}
