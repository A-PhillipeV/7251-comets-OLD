package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Config
@TeleOp(name="ArmTesting", group="Linear Opmode")
//@Disabled
public class ArmTesting extends LinearOpMode {

    // Declare OpMode members.
    HardwareTeleOp robot = new HardwareTeleOp();
    private ElapsedTime runtime = new ElapsedTime();

    double elbowPower = 0;
    double wristPower = 0;

    public static PIDCoefficients testPID = new PIDCoefficients(0,0,0);

    public static int elbowPos = -590;
    public static int wristPos = 490;

    double integralSum = 0;

    private double lastError = 0;

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        FtcDashboard dashboard = FtcDashboard.getInstance();
        telemetry = dashboard.getTelemetry();

        waitForStart();

        while(opModeIsActive()) {
            robot.elbow.setPower(PIDControl(-590, robot.elbow.getCurrentPosition()));
            robot.wrist.setPower(PIDControl(490, robot.wrist.getCurrentPosition()));
        }


            /* TELEMETRY */
            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("position Elbow ", robot.elbow.getCurrentPosition());
            telemetry.addData("position Wrist", robot.wrist.getCurrentPosition());
            telemetry.update();

    }

    public double PIDControl(double reference, double state) {
        double Kp = testPID.p, Ki = testPID.i, Kd = testPID.p;

        double error = reference - state;
        integralSum += error * runtime.seconds();
        double derivative = (error - lastError)/runtime.seconds();
        lastError = error;

        runtime.reset();
        double output = (error * Kp) + (derivative * Kp) + (integralSum * Ki);
        return output;
    }
}