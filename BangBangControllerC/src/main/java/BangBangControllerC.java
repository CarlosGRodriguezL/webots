/**
 * Created by Dominik on 17.12.2015.
 */
public class BangBangControllerC extends AbstractController {

    public BangBangControllerC() {
        super();
    }

    private boolean isFrontSensorInTolerance () {
        if ((Math.abs(distanceSensors[S_FRONT_RIGHT].getValue() - distanceSensors[S_FRONT_LEFT].getValue()) < DISTANCE_TOLERANCE)){
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
                printDistanceInfo("DriveStraightForward");
                driveForward();
            } else if (distanceSensors[S_FRONT_LEFT].getValue() > distanceSensors[S_FRONT_RIGHT].getValue()){
                printDistanceInfo("DriveLeft");
                driveLeft();
            } else if (distanceSensors[S_FRONT_RIGHT].getValue() > distanceSensors[S_FRONT_LEFT].getValue()){
                printDistanceInfo("DriveRight");
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
