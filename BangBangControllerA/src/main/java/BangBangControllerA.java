import com.cyberbotics.webots.controller.DifferentialWheels;
import com.cyberbotics.webots.controller.LightSensor;

/**
 * Created by Dominik on 16.12.2015.
 */
public class BangBangControllerA extends DifferentialWheels {
    private static int TIME_STEP = 16;

    private static int S_LEFT = 0; // Sensor left
    private static int S_FRONT_LEFT = 1; // Sensor front left
    private static int S_FRONT_RIGHT = 2; // Sensor front right
    private static int S_RIGHT = 3; // Sensor left
    private static int MIN_SPEED = 0; // min. motor speed
    private static int MAX_SPEED = 1000; // max. motor speed


    private LightSensor[] sensors; // Array with all distance sensors

    public BangBangControllerA() {
        super();
        // get distance sensors and save them in array
        sensors = new LightSensor[] { getLightSensor("ls5"),
                getLightSensor("ls7"), getLightSensor("ls0"),
                getLightSensor("ls2")};
        for (int i=0; i<4; i++)
            sensors[i].enable(10);
    }

    /**
     * Robot drives to the right
     */
    private void driveRight() {
        setSpeed(MAX_SPEED, MIN_SPEED);
    }

    /**
     * Robot drives to the left
     */
    private void driveLeft() {
        setSpeed(MIN_SPEED, MAX_SPEED);
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

    public void printInfo(String name){
        System.out.println(name + " " +
                "LEFT: "+ sensors[S_LEFT].getValue()  + "; " +
                "RIGHT: "+ sensors[S_RIGHT].getValue() + "; " +
                "LEFT_FRONT: "+ sensors[S_FRONT_LEFT].getValue() + "; " +
                "RIGHT_FRONT: "+ sensors[S_FRONT_RIGHT].getValue() + "; "
        );
    }
}
