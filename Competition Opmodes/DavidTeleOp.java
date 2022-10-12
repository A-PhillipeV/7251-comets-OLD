package org.firstinspires.ftc.teamcode.Comp;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.HardwareRobot;

@TeleOp(name="DavidTeleOp", group="Comp")
//@Disabled
public class DavidTeleOp extends LinearOpMode {

    // Declare OpMode members.
    HardwareRobot robot = new HardwareRobot(this);
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
        robot.init();
        FtcDashboard dashboard = FtcDashboard.getInstance();
        telemetry = dashboard.getTelemetry();

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        double handPower;
        double platformPower = 0.0;
        double elbowPower = 0.0;
        double speed = 0;
        waitForStart();

        while (opModeIsActive()) {

            robot.motorRight(rmPower);
            robot.motorLeft(lm);
            robot.wrist.setPower(wristPower);
            robot.elbow.setPower(handPower);
            robot.platform.setPower(platformPower);
            robot.hand.setPosition(handPower);


            /* TELEMETRY */
            // Show the elapsed game time and wheel power.
            //telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
            telemetry.addData("Platform spinner", "spinner (%.2f),", platformPower);
            telemetry.addData("position Elbow ", robot.elbow.getCurrentPosition());
            telemetry.addData("position Wrist", robot.wrist.getCurrentPosition());
            telemetry.addData("Please work :) speed:", speed);
            telemetry.update();
        }
    }

    class Speed{
        private double vel;

        public Speed() {
            vel = 0.5;
        }

        public double getVel() {
            return vel;
        }

        public void downVel() {
            if (vel > 0) {
                vel -= 0.01;
            }
        }

        public void upVel() {
            if (vel < 1) {
                vel += 0.01;
            }
        }
    }
}
