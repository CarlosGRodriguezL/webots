import com.cyberbotics.webots.controller.DifferentialWheels;
import com.cyberbotics.webots.controller.DistanceSensor;

/**
 * Created by Dominik on 17.12.2015.
 */
public class BangBangControllerC extends AbstractDistanceController {

    public BangBangControllerC() {
        super();
    }

    private boolean isFrontSensorInTolerance () {
        if ((Math.abs(sensors[S_FRONT_RIGHT].getValue() - sensors[S_FRONT_LEFT].getValue()) < DISTANCE_TOLERANCE)){
            return true;
        } else {
            return false;
        }
    }

    /**
     * User defined function for initializing and running the
     * BangBangControllerC class
     */
    public void run() {
        while (step(TIME_STEP) != -1) {
            if (isFrontSensorInTolerance()){
                printInfo("DriveStraightForward");
                driveForward();
            } else if (sensors[S_FRONT_LEFT].getValue() > sensors[S_FRONT_RIGHT].getValue()){
                printInfo("DriveLeft");
                driveLeft();
            } else if (sensors[S_FRONT_RIGHT].getValue() > sensors[S_FRONT_LEFT].getValue()){
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
        BangBangControllerC controller = new BangBangControllerC();
        controller.run();
    }


}
