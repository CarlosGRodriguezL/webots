import com.cyberbotics.webots.controller.DifferentialWheels;
import com.cyberbotics.webots.controller.DistanceSensor;
import com.cyberbotics.webots.controller.LightSensor;

/**
 * Created by Dominik on 20.12.2015.
 */
public class AbstractController extends DifferentialWheels {

    protected static int TIME_STEP = 16;

    protected static int S_LEFT = 0; // Sensor left
    protected static int S_FRONT_LEFT = 1; // Sensor front left
    protected static int S_FRONT_RIGHT = 2; // Sensor front right
    protected static int S_RIGHT = 3; // Sensor left
    protected static int MIN_SPEED = 0; // min. motor speed
    protected static int MAX_SPEED = 1000; // max. motor speed

    protected static int DISTANCE_TOLERANCE = 100;

    protected DistanceSensor[] distanceSensors; // Array with all distance sensors
    protected LightSensor[] lightSensors; // Array with all distance sensors

    public AbstractController() {
        super();
        init();
    }

    private void init(){
        // get distance sensors and save them in array
        distanceSensors = new DistanceSensor[] { getDistanceSensor("ps5"),
                getDistanceSensor("ps7"), getDistanceSensor("ps0"),
                getDistanceSensor("ps2")};
        for (int i=0; i< distanceSensors.length ; i++)
            distanceSensors[i].enable(10);

        lightSensors = new LightSensor[] { getLightSensor("ls5"),
                getLightSensor("ls7"), getLightSensor("ls0"),
                getLightSensor("ls2")};
        for (int i=0; i<lightSensors.length; i++)
            lightSensors[i].enable(10);
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

    protected void printDistanceInfo(String name){
        System.out.println(name + " " +
                "LEFT: "+ distanceSensors[S_LEFT].getValue()  + "; " +
                "RIGHT: "+ distanceSensors[S_RIGHT].getValue() + "; " +
                "LEFT_FRONT: "+ distanceSensors[S_FRONT_LEFT].getValue() + "; " +
                "RIGHT_FRONT: "+ distanceSensors[S_FRONT_RIGHT].getValue() + "; "
        );
    }

    protected void printLightInfo(String name){
        System.out.println(name + " " +
                "LEFT: "+ lightSensors[S_LEFT].getValue()  + "; " +
                "RIGHT: "+ lightSensors[S_RIGHT].getValue() + "; " +
                "LEFT_FRONT: "+ lightSensors[S_FRONT_LEFT].getValue() + "; " +
                "RIGHT_FRONT: "+ lightSensors[S_FRONT_RIGHT].getValue() + "; "
        );
    }

}
