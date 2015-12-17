import com.cyberbotics.webots.controller.DifferentialWheels;
import com.cyberbotics.webots.controller.DistanceSensor;

/**
 * Created by Dominik on 17.12.2015.
 */
public class ProportionalControllerC extends DifferentialWheels {
    private static int TIME_STEP = 16;

    private static int S_LEFT = 0; // Sensor left
    private static int S_FRONT_LEFT = 1; // Sensor front left
    private static int S_FRONT_RIGHT = 2; // Sensor front right
    private static int S_RIGHT = 3; // Sensor left
    private static int AVG_SPEED = 500; // avg. motor speed
    private static int MAX_SPEED = 1000; // max. motor speed

    private static int DISTANCE_TOLERANCE = 1000;

    private DistanceSensor[] sensors; // Array with all distance sensors

    public ProportionalControllerC() {
        super();
        // get distance sensors and save them in array
        sensors = new DistanceSensor[] { getDistanceSensor("ps5"),
                getDistanceSensor("ps7"), getDistanceSensor("ps0"),
                getDistanceSensor("ps2")};
        for (int i=0; i< sensors.length ; i++)
            sensors[i].enable(10);
    }

    /**
     * Robot drives to the right
     */
    private void driveRight() {
        setSpeed(MAX_SPEED, AVG_SPEED);
    }

    /**
     * Robot drives to the left
     */
    private void driveLeft() {
        setSpeed(AVG_SPEED, MAX_SPEED);
    }

    /**
     * Robot drives forward
     */
    private void driveForward() {
        setSpeed(MAX_SPEED, MAX_SPEED);
    }

    private boolean isFrontSensorInTolleranz () {
        if ((Math.abs(sensors[S_FRONT_RIGHT].getValue() - sensors[S_FRONT_LEFT].getValue()) < DISTANCE_TOLERANCE)){
            return true;
        } else {
            return false;
        }
    }

    /**
     * User defined function for initializing and running the
     * ProportionalControllerC class
     */
    public void run() {
        while (step(TIME_STEP) != -1) {
            if (isFrontSensorInTolleranz()){
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
        ProportionalControllerC controller = new ProportionalControllerC();
        controller.run();
    }

    public void printInfo(String name){
        System.out.println(name + " " +
                "LEFT: "+ sensors[S_LEFT].getValue()  + "; " +
                "RIGHT: "+ sensors[S_RIGHT].getValue() + "; " +
                "LEFT_FRONT: "+ sensors[S_FRONT_LEFT].getValue() + "; " +
                "RIGHT_FRONT: "+ sensors[S_FRONT_RIGHT].getValue() + "; "
        );
    }
}
