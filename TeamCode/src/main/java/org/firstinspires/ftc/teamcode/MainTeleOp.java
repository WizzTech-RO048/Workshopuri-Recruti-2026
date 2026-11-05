import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Control Robot")
public class ControlRobot extends LinearOpMode {

    DcMotor fataStanga;
    DcMotor fataDreapta;
    DcMotor spateStanga;
    DcMotor spateDreapta;

    @Override
    public void runOpMode() {

        fataStanga = hardwareMap.get(DcMotor.class, "fataStanga");
        fataDreapta = hardwareMap.get(DcMotor.class, "fataDreapta");
        spateStanga = hardwareMap.get(DcMotor.class, "spateStanga");
        spateDreapta = hardwareMap.get(DcMotor.class, "spateDreapta");

        fataStanga.setDirection(DcMotor.Direction.REVERSE);
        spateStanga.setDirection(DcMotor.Direction.REVERSE);

        fataDreapta.setDirection(DcMotor.Direction.FORWARD);
        spateDreapta.setDirection(DcMotor.Direction.FORWARD);

        fataStanga.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fataDreapta.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        spateStanga.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        spateDreapta.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();

        while (opModeIsActive()) {

            double fataSpate = modificareInput(-gamepad1.left_stick_y);
            double stangaDreapta = modificareInput(gamepad1.left_stick_x);
            double rotire = modificareInput(gamepad1.right_stick_x);

            double putereFS = fataSpate + stangaDreapta + rotire;
            double putereFD = fataSpate - stangaDreapta - rotire;
            double putereSS = fataSpate - stangaDreapta + rotire;
            double putereSD = fataSpate + stangaDreapta - rotire;

            double maxim = Math.abs(putereFS);

            if (Math.abs(putereFD) > maxim) {
                maxim = Math.abs(putereFD);
            }

            if (Math.abs(putereSS) > maxim) {
                maxim = Math.abs(putereSS);
            }

            if (Math.abs(putereSD) > maxim) {
                maxim = Math.abs(putereSD);
            }

            if (maxim > 1) {
                putereFS = putereFS / maxim;
                putereFD = putereFD / maxim;
                putereSS = putereSS / maxim;
                putereSD = putereSD / maxim;
            }

            fataStanga.setPower(putereFS);
            fataDreapta.setPower(putereFD);
            spateStanga.setPower(putereSS);
            spateDreapta.setPower(putereSD);
        }
    }

    public double modificareInput(double input) {

        if (Math.abs(input) < 0.05) {
            return 0;
        }

        return input * input * input;
    }
}
