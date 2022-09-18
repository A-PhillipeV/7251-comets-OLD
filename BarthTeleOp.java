package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="BarthTeleOp", group="Linear Opmode")
//@Disabled
public class BarthTeleOp extends LinearOpMode {

    // Declare OpMode members.
    HardwareTeleOp robot = new HardwareTeleOp();
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        FtcDashboard dashboard = FtcDashboard.getInstance();
        telemetry = dashboard.getTelemetry();

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        double handPower;
        double platformPower = 0.0;
        double elbowPower = 0.0;

        waitForStart();

        while (opModeIsActive()) {

            // Setup a variable for each drive wheel to save power level for telemetry
            double leftPower;
            double rightPower;
            double wristPower = 0;
            double outerPower = 1;


            //P1 (movement)
            //MARIO KART MODE
            //left stick soft-turns
            //a button moves forward, b button moves backward
            //left stick + right trigger drifts

            double high_speed = 1.0;
            double normal_speed = 0.75;
            double low_speed = 0.5;
            double lowest_speed = 0.1;

            double axisWheel;
            double turningWheel = high_speed;

            //moving forward
            if(gamepad1.b) // backwards
            {
                if(gamepad1.left_stick_x < 0 || gamepad1.dpad_left)//turning left while going forward
                {
                    leftPower = -normal_speed;
                    rightPower = -high_speed;
                }

                else if(gamepad1.left_stick_x > 0 || gamepad1.dpad_right) // turning right while going forward
                {
                    leftPower = -high_speed;
                    rightPower = -normal_speed;
                }

                else //going straight ahead
                {
                    leftPower =   -high_speed;
                    rightPower =  -high_speed;
                }
            }

            else if(gamepad1.a) //forward
            {
                if(gamepad1.left_stick_x < 0 || gamepad1.dpad_left)//turning left while going forward
                {
                    leftPower = normal_speed;
                    rightPower = high_speed;
                }

                else if(gamepad1.left_stick_x > 0 || gamepad1.dpad_right) // turning right while going forward
                {
                    leftPower = high_speed;
                    rightPower = normal_speed;
                }

                else //going straight ahead
                {
                    leftPower =   high_speed;
                    rightPower =  high_speed;
                }

            }//end of going forward

            else if(gamepad1.left_stick_x < 0 || gamepad1.dpad_left) //turn left in place
            {
                leftPower =  -low_speed;
                rightPower = low_speed;
            }

            else if(gamepad1.left_stick_x > 0 || gamepad1.dpad_right) //turn right in place
            {
                leftPower = low_speed;
                rightPower = -low_speed;
            }

            else //stop
            {
                leftPower = 0;
                rightPower = 0;
            }

            if(gamepad1.left_trigger != 0) {
                leftPower /= 1.7;
                rightPower /= 1.7;
            }
            robot.motorLeft.setPower(leftPower);
            robot.motorRight.setPower(rightPower);



            //P2 (grabbing/platform)

            //left stick = elbow

            if(!(gamepad2.left_stick_y == 0)) {
                elbowPower = -gamepad2.left_stick_y / 2.5;
                robot.elbow.setDirection(DcMotorSimple.Direction.REVERSE);
                robot.elbow.setPower(elbowPower);
            }

            //right stick = wrist
            if(!(gamepad2.right_stick_y == 0)) {
                wristPower = -gamepad2.right_stick_y / 2.5;
                robot.wrist.setPower(-wristPower);
            }
            //wrist.setTargetPosition(wrist.getCurrentPosition());

            //right trigger and left trigger = hand
            handPower = 0;
            if(gamepad2.right_trigger != 0) {
                handPower = 1;
            }
            else if(gamepad2.left_trigger != 0) {
                handPower = -1;
            }
            robot.hand.setPosition(handPower);

            //dpad = platform
            platformPower = 0.0;
            if(gamepad2.left_trigger != 0 || gamepad2.dpad_left)
                platformPower = -1;
            else if(gamepad2.left_bumper || gamepad2.dpad_right)
                platformPower = 1;
            robot.platform.setPower(platformPower);


            /* TELEMETRY */
            // Show the elapsed game time and wheel power.
            //telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
            telemetry.addData("Platform spinner", "spinner (%.2f),", platformPower);
            telemetry.addData("position Elbow ", robot.elbow.getCurrentPosition());
            telemetry.addData("position Wrist", robot.wrist.getCurrentPosition());
            telemetry.update();
        }
    }
}
