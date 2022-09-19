package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.*;
import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
//import org.firstinspires.ftc.robotcore.*;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.Locale;

/**
 *   NOT AN OPMODE! ONLY FOR AUTONOMOUS!!!!!!!!!!!
 *   Put in ALL initilization/hardwareMaps that you need here !!
 */

public class HardwareAuto {

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

    public HardwareAuto() {
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

        motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        wrist.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        elbow.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
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
