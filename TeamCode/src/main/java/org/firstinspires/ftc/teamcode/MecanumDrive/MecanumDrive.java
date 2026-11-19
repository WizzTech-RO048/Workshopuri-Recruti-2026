package org.firstinspires.ftc.teamcode.MecanumDrive;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class MecanumDrive {
    private DcMotor fLeftMotor, bLeftMotor, fRightMotor, bRightMotor;
    private IMU imu;
    public void init(HardwareMap hwMap) {
        fLeftMotor = hwMap.get(DcMotor.class, "fLeft");
        bLeftMotor = hwMap.get(DcMotor.class, "bLeft");
        fRightMotor = hwMap.get(DcMotor.class, "fRight");
        bRightMotor = hwMap.get(DcMotor.class, "bRight");

        fRightMotor.setDirection(DcMotor.Direction.REVERSE);
        fLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        bRightMotor.setDirection(DcMotor.Direction.REVERSE);
        bLeftMotor.setDirection(DcMotor.Direction.REVERSE);

        fRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        imu = hwMap.get(IMU.class, "imu");
        RevHubOrientationOnRobot RevOrientation = new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD);
        imu.initialize(new IMU.Parameters(RevOrientation));
    }
    public void drive(double forward, double strafe, double rotate){
        double fLpow = forward+strafe+rotate;
        double bLpow = forward-strafe+rotate;
        double fRpow = forward-strafe-rotate;
        double bRpow = forward+strafe-rotate;

        double maxPow = 1.0;
        maxPow = Math.max(maxPow, Math.abs(fLpow));
        maxPow = Math.max(maxPow, Math.abs(bLpow));
        maxPow = Math.max(maxPow, Math.abs(fRpow));
        maxPow = Math.max(maxPow, Math.abs(bRpow));

        fLeftMotor.setPower(fLpow / maxPow);
        bLeftMotor.setPower(bLpow / maxPow);
        fRightMotor.setPower(fRpow / maxPow);
        bRightMotor.setPower(bRpow / maxPow);
    }
    public void driveFieldRelat(double forward, double strafe, double rotate){
        double theta = Math.atan2(forward, strafe);
        double r = Math.hypot(strafe, forward);

        theta = AngleUnit.normalizeRadians(theta - imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS));
        double newFwd = r*Math.sin(theta);
        double newStrf = r*Math.cos(theta);

        this.drive(newFwd, newStrf, rotate);
    }
}
