import com.cyberbotics.webots.controller.DifferentialWheels;
import com.cyberbotics.webots.controller.LightSensor;

/**
 * Created by Dominik on 17.12.2015.
 */
public class ProportionalControllerA extends DifferentialWheels {
    private static int TIME_STEP = 16;

    private static int MAX_LIGHT = 4096;

    private static int S_LEFT = 0; // Sensor left
    private static int S_FRONT_LEFT = 1; // Sensor front left
    private static int S_FRONT_RIGHT = 2; // Sensor front right
    private static int S_RIGHT = 3; // Sensor left
    private static int S_BACKWARD_LEFT = 4; // Sensor front left
    private static int S_BACKWARD_RIGHT = 5; // Sensor front right
    private static int MIN_SPEED = 0; // min. motor speed
    private static int AVG_SPEED = 500;
    private static int MAX_SPEED = 1000; // max. motor speed

    private static int LIGHT_TOLERANCE = 200;

    private LightSensor[] sensors; // Array with all distance sensors

    public ProportionalControllerA() {
        super();
        // get distance sensors and save them in array
        sensors = new LightSensor[] { getLightSensor("ls5"),
                getLightSensor("ls7"), getLightSensor("ls0"),
                getLightSensor("ls2"), getLightSensor("ls4"), getLightSensor("ls3")};
        for (int i=0; i< sensors.length ; i++)
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
     * Robot drives forward
     */
    private void driveForward() {
        setSpeed(MAX_SPEED, MAX_SPEED);
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

    private boolean isFrontSensorInTolleranz () {
        if ((Math.abs(sensors[S_FRONT_RIGHT].getValue() - sensors[S_FRONT_LEFT].getValue()) < LIGHT_TOLERANCE)){
            return true;
        } else {
            return false;
        }
    }

    /**
     * User defined function for initializing and running the
     * ProportionalControllerA class
     */
    public void run() {
        while (step(TIME_STEP) != -1) {
            if (sensors[S_BACKWARD_LEFT].getValue() + sensors[S_BACKWARD_RIGHT].getValue() < sensors[S_FRONT_LEFT].getValue() + sensors[S_FRONT_RIGHT].getValue()) {
                //lightsource is behind the roboter
                printInfo("DriveAvgRight");
                driveAvgRight();
            } else if (isFrontSensorInTolleranz()){
                printInfo("StraigtForward");
                driveForward();
            } else if (sensors[S_LEFT].getValue() < sensors[S_RIGHT].getValue()) {
                printInfo("DriveAVGLeft");
                driveAvgLeft();

            } else if (sensors[S_RIGHT].getValue() < sensors[S_LEFT].getValue()) {
                printInfo("DriveAVGRight");
                driveAvgRight();
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
        ProportionalControllerA controller = new ProportionalControllerA();
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
