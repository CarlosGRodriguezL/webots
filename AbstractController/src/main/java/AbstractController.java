import com.cyberbotics.webots.controller.Accelerometer;
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
    protected static int S_AVG_LEFT = 4;
    protected static int S_AVG_RIGHT = 5;
    protected static int MIN_SPEED = 0; // min. motor speed
    protected static int AVG_SPEED = 500; // avg. motor speed
    protected static int MAX_SPEED = 1000; // max. motor speed

    protected static int DISTANCE_TOLERANCE = 100;

    protected DistanceSensor[] distanceSensors; // Array with all distance sensors
    protected LightSensor[] lightSensors; // Array with all distance sensors
    protected Accelerometer accelerometer;

    public AbstractController() {
        super();
        init();
    }

    private void init(){

        accelerometer = new Accelerometer("accelerometer");
        accelerometer.enable(1);

        // get distance sensors and save them in array
        distanceSensors = new DistanceSensor[] { getDistanceSensor("ps5"),
                getDistanceSensor("ps7"), getDistanceSensor("ps0"),
                getDistanceSensor("ps2"), getDistanceSensor("ps6"), getDistanceSensor("ps1")};
        for (int i=0; i< distanceSensors.length ; i++)
            distanceSensors[i].enable(10);

        // get light sensors and save them in array
        lightSensors = new LightSensor[] { getLightSensor("ls5"),
                getLightSensor("ls7"), getLightSensor("ls0"),
                getLightSensor("ls2"), getLightSensor("ls6"), getLightSensor("ls1")};
        for (int i=0; i< lightSensors.length; i++)
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
        double xValue = Math.abs(accelerometer.getValues()[0]);
        double yValue = Math.abs(accelerometer.getValues()[1]);
        double zValue = Math.abs(accelerometer.getValues()[2]);
        double sum = (xValue + yValue) * zValue;
        System.out.println(name + "; " + "Accelerometer sum: " + sum + "; " +
                "LEFT: " + distanceSensors[S_LEFT].getValue()  + "; " +
                "RIGHT: " + distanceSensors[S_RIGHT].getValue() + "; " +
                "AVG_LEFT: " + distanceSensors[S_AVG_LEFT].getValue()  + "; " +
                "AVG_RIGHT: " + distanceSensors[S_AVG_RIGHT].getValue() + "; " +
                "LEFT_FRONT: " + distanceSensors[S_FRONT_LEFT].getValue() + "; " +
                "RIGHT_FRONT: " + distanceSensors[S_FRONT_RIGHT].getValue() + "; "
        );
    }

    protected void printLightInfo(String name){
        System.out.println(name + "; " +
                "LEFT: " + lightSensors[S_LEFT].getValue()  + "; " +
                "RIGHT: " + lightSensors[S_RIGHT].getValue() + "; " +
                "AVG_LEFT: " + lightSensors[S_AVG_LEFT].getValue()  + "; " +
                "AVG_RIGHT: " + lightSensors[S_AVG_RIGHT].getValue() + "; " +
                "LEFT_FRONT: " + lightSensors[S_FRONT_LEFT].getValue() + "; " +
                "RIGHT_FRONT: " + lightSensors[S_FRONT_RIGHT].getValue() + "; "
        );
    }

}
