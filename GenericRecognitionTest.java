/*
    This is the opmode we'd use for the image recognition itself
    GenericDetector is the class that's using the tfrec folder with our
    downloaded tflite training models. Essentially, GenericDetector is the class
    has the methods (much like our HardwareTeleop Class)

    - I commented out anything that's not necessary for us
    - David logging off @ 11:40pm 9/17/22
        - I have work with my dad tmrw :(
 */


package org.firstinspires.ftc.teamcode;

//import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp(name = "GenericRecognitionTest", group = "Linear Opmode")
//@Disabled
public class GenericRecognitionTest extends LinearOpMode {

    // Declare OpMode members.
    private GenericDetector rf = null;
    private String result = "";

    @Override
    public void runOpMode() {
        try {
            try {
                //initialize the bot
                //bot.init(this, this.hardwareMap, telemetry);

                //initialize the detector. It will run on its own thread continuously
                rf = new GenericDetector(this.hardwareMap,  this,  telemetry);
                Thread detectThread = new Thread(rf);
                detectThread.start();
                telemetry.update();
            } catch (Exception ex) {
                telemetry.addData("Error", String.format("Unable to initialize Detector. %s", ex.getMessage()));
                telemetry.update();
                sleep(5000);
                return;
            }

            // Wait for the game to start (driver presses PLAY)
            telemetry.update();
            waitForStart();

            rf.stopDetection();

            result = rf.getResult();

            // run until the end of the match (driver presses STOP)
            while (opModeIsActive()) {
                //move the bot
                //double drive = gamepad1.left_stick_y;
                //bot.move(drive);

                //show recognition result
                telemetry.addData("Detection result", result);
                telemetry.update();
            }
        } catch (Exception ex) {
            telemetry.addData("Init Error", ex.getMessage());
            telemetry.update();
        } finally {
            if (rf != null) {
                rf.stopDetection();
            }
        }
    }
}