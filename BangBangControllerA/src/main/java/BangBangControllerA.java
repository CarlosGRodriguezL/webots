import com.cyberbotics.webots.controller.DifferentialWheels;
import com.cyberbotics.webots.controller.LightSensor;

/**
 * Created by Dominik on 16.12.2015.
 */
public class BangBangControllerA extends AbstractLightController {

    public BangBangControllerA() {
        super();
    }

    /**
     * User defined function for initializing and running the
     * BangBangControllerA class
     */
    public void run() {
        while (step(TIME_STEP) != -1) {
            if (sensors[S_LEFT].getValue() < sensors[S_RIGHT].getValue()) {
                printInfo("DriveLeft");
                driveLeft();
            } else if (sensors[S_RIGHT].getValue() < sensors[S_LEFT].getValue()) {
                printInfo("DriveRight");
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
