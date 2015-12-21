/**
 * Created by Carlos on 07.12.2015.
 */
public class BangBangControllerB extends AbstractController {
    private static int AVG_SPEED = 500;
    private static int MAX_SPEED = 1000; // max. motor speed

    private static boolean flag = true;

    public BangBangControllerB() {
        super();
    }

    private void driveAvgRight(){
        setSpeed(MAX_SPEED, AVG_SPEED);
    }

    private void driveAvgLeft(){
        setSpeed(AVG_SPEED,MAX_SPEED);
    }

    private void pause(){
        setSpeed(MIN_SPEED, MIN_SPEED);
    }

    /**
     * User defined function for initializing and running the
     * BangBangFollowTheWall class
     */
    public void run() {
        while (step(TIME_STEP) != -1 && flag) {
            if((distanceSensors[S_FRONT_LEFT].getValue() < -10 || distanceSensors[S_FRONT_RIGHT].getValue() < -10) &&
                    (lightSensors[S_FRONT_LEFT].getValue() <= 2700 || lightSensors[S_FRONT_RIGHT].getValue() <= 2700)){
                flag = false;
                printDistanceInfo("Distance");
                setSpeed(MIN_SPEED, MIN_SPEED);
            }
            if(flag) {
                if (lightSensors[S_LEFT].getValue() < lightSensors[S_RIGHT].getValue()) {
                    if (lightSensors[S_LEFT].getValue() > lightSensors[S_FRONT_LEFT].getValue()) {
                        printLightInfo("driveLeft");
                        driveLeft();
                    } else {
                        printLightInfo("driveLeft");
                        driveAvgLeft();
                    }
                } else if (lightSensors[S_RIGHT].getValue() < lightSensors[S_LEFT].getValue()) {
                    if (lightSensors[S_RIGHT].getValue() > lightSensors[S_FRONT_RIGHT].getValue()) {
                        printLightInfo("driveRight");
                        driveRight();
                    } else {
                        printLightInfo("driveRight");
                        driveAvgRight();
                    }
                }
            }
        }
    }

    /**
     * Main method - in this method an instance of the controller is created and
     * the method to launch the robot is called.
     *
     * @param args
     */
    public static void main(String[] args) {
        BangBangControllerB controller = new BangBangControllerB();
        controller.run();
    }
}
