/**
 * Created by Dominik on 16.12.2015.
 */
public class BangBangControllerA extends AbstractController {

    public BangBangControllerA() {
        super();
    }

    /**
     * User defined function for initializing and running the
     * BangBangControllerA class
     */
    public void run() {
        while (step(TIME_STEP) != -1) {
            if (lightSensors[S_LEFT].getValue() < lightSensors[S_RIGHT].getValue()) {
                printLightInfo("DriveLeft");
                driveLeft();
            } else if (lightSensors[S_RIGHT].getValue() < lightSensors[S_LEFT].getValue()) {
                printLightInfo("DriveRight");
                driveRight();
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
        BangBangControllerA controller = new BangBangControllerA();
        controller.run();
    }

}
