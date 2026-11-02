package CristinaT;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="MainTeleOp")
public class MainTeleOp extends OpMode {
    DcMotor lf;
    DcMotor lr;
    DcMotor rf;
    DcMotor rr;
    @Override
    public void init() {
       lf=hardwareMap.get(DcMotor.class, "lf");
        lr=hardwareMap.get(DcMotor.class, "lr");
        rf=hardwareMap.get(DcMotor.class, "rf");
        rr=hardwareMap.get(DcMotor.class, "rr");
    }
    @Override
    public void loop(){

        double y= -gamepad1.left_stick_y;
        double x= gamepad1.left_stick_x;
        double r= gamepad1.right_stick_x;

        lf.setPower(y+x+r);
        lr.setPower(y-x+r);
        rf.setPower(y-x-r);
        rr.setPower(y+x-r);
    }

}



