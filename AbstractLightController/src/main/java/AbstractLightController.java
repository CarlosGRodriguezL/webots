import com.cyberbotics.webots.controller.DifferentialWheels;
import com.cyberbotics.webots.controller.LightSensor;

/**
 * Created by Dominik on 20.12.2015.
 */
public class AbstractLightController extends DifferentialWheels {

    protected static int TIME_STEP = 16;

    protected static int S_LEFT = 0; // Sensor left
    protected static int S_FRONT_LEFT = 1; // Sensor front left
    protected static int S_FRONT_RIGHT = 2; // Sensor front right
    protected static int S_RIGHT = 3; // Sensor left
    protected static int MIN_SPEED = 0; // min. motor speed
    protected static int MAX_SPEED = 1000; // max. motor speed

    protected static int LIGHT_TOLERANCE = 200;

    protected LightSensor[] sensors; // Array with all distance sensors

    protected AbstractLightController() {
        super();
        init();
    }

    protected void init(){
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
    protected void driveRight() {
        setSpeed(MAX_SPEED, MIN_SPEED);
    }

    /**
     * Robot drives to the left
     */
    protected void driveLeft() {
        setSpeed(MIN_SPEED, MAX_SPEED);
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
