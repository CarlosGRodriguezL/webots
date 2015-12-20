import com.cyberbotics.webots.controller.DifferentialWheels;
import com.cyberbotics.webots.controller.DistanceSensor;

/**
 * Created by Dominik on 20.12.2015.
 */
public class AbstractDistanceController extends DifferentialWheels {

    protected static int TIME_STEP = 16;

    protected static int S_LEFT = 0; // Sensor left
    protected static int S_FRONT_LEFT = 1; // Sensor front left
    protected static int S_FRONT_RIGHT = 2; // Sensor front right
    protected static int S_RIGHT = 3; // Sensor left
    protected static int MIN_SPEED = 0; // min. motor speed
    protected static int MAX_SPEED = 1000; // max. motor speed

    protected static int DISTANCE_TOLERANCE = 100;

    protected DistanceSensor[] sensors; // Array with all distance sensors

    public AbstractDistanceController() {
        super();
        init();
    }

    private void init(){
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
    protected void driveRight() {
        setSpeed(MAX_SPEED, MIN_SPEED);
    }

    /**
     * Robot drives to the left
     */
    protected void driveLeft() {
        setSpeed(MIN_SPEED, MAX_SPEED);
    }

    /**
     * Robot drives forward
     */
    protected void driveForward() {
        setSpeed(MAX_SPEED, MAX_SPEED);
    }

    protected void printInfo(String name){
        System.out.println(name + " " +
                "LEFT: "+ sensors[S_LEFT].getValue()  + "; " +
                "RIGHT: "+ sensors[S_RIGHT].getValue() + "; " +
                "LEFT_FRONT: "+ sensors[S_FRONT_LEFT].getValue() + "; " +
                "RIGHT_FRONT: "+ sensors[S_FRONT_RIGHT].getValue() + "; "
        );
    }
}
