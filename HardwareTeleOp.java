package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
//import org.firstinspires.ftc.robotcore.*;


/**
 *   NOT AN OPMODE! ONLY FOR TELEOP!!!!!!!!!!!!!!
 *   Put in ALL initilization/hardwareMaps that you need here !!
 */

public class HardwareTeleOp {

    /* Public Opmode Members */
    public DcMotor motorRight;
    public DcMotor motorLeft;
    public DcMotor elbow;
    public DcMotor wrist;

    public Servo hand;
    public CRServo platform;
    BNO055IMU imu;

    /* Local OpMode Members */
    HardwareMap hwMap = null;

    public HardwareTeleOp() {
    }

    public void init(HardwareMap ahwMap) {
        /* Motors */
        hwMap = ahwMap;
        motorLeft = hwMap.dcMotor.get("Left_Motor");
        motorRight = hwMap.dcMotor.get("Right_Motor");
        elbow = hwMap.dcMotor.get("Elbow");
        wrist = hwMap.dcMotor.get("Wrist");

        motorLeft.setDirection(DcMotor.Direction.REVERSE);
        motorRight.setDirection(DcMotor.Direction.FORWARD);

        motorRight.setPower(0);
        motorLeft.setPower(0);
        elbow.setPower(0);
        wrist.setPower(0);

        /*
         * TODO: Later might need to interchangably change from running with encoder to without
         */
        motorRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        wrist.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        elbow.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        wrist.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        elbow.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        /* Servos */
        hand = hwMap.servo.get("Hand");
        platform = hwMap.crservo.get("Platform");
        hand.setPosition(0.0);

        /* Gyros */
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled      = true;
        parameters.loggingTag          = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

    }

}
