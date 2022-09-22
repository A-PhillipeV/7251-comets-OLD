## Hi Programmers !!
This GitHub repository is the heart of 7251-Comets. 

## Using This Repo
Download the current FTC-Robot Controller SDK and go to the FtcRobotController/TeamCode/src/main/java/org/firstinspires/ftc/teamcode/ folder and copypaste all of the given files in this rep.

Build FTC Dash inside:
- build.dependencies.gradle

## Downloads:
In addition, to wirelessly upload code (__make sure you are connected to RC wifi__), download the android platform_tools sdk:   
https://developer.android.com/studio/releases/platform-tools   
Click on the bar in platform_tools <img width="185" alt="platform_tool" src="https://user-images.githubusercontent.com/87776842/188527100-bea97838-59f2-486e-880b-9ba4bf24e8b9.png"> (where you see the path) and type **cmd**, and then type in the following:   

`abd tcpip 5555`    
`adb connect 192.168.43.1:5555 (5555 is the "dock" for connection)`   

To access FTC dash, type in:   
http://192.168.43.1:8080/dash

7251 Handbook:   
https://docs.google.com/document/d/1x8hSlBrLIcQmxfHnqMxywp3bEHRAU8E4cPc_SarHzqw/edit?usp=sharing

## TensorFlow Additions 
Follow the https://teachablemachine.withgoogle.com/ instructons and get your samples. One finished, download the file as a floating point. The most important part is your .tfile (it has the actual recognition
In `FtcRobotController-master\FtcRobotController\src\main\assets` add in your .tfile.
Our vuforia key should be inside our opmodes. If it changes for any reason, go ahead and make a new one in https://developer.vuforia.com/license-manager.





