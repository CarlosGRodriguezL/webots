import com.cyberbotics.webots.controller.DifferentialWheels;
import com.cyberbotics.webots.controller.LightSensor;

/**
 * Created by Dominik on 17.12.2015.
 */
public class ProportionalControllerA extends AbstractLightController {

    public ProportionalControllerA() {
        super();
    }

    /**
     * User defined function for initializing and running the
     * ProportionalControllerA class
     */
    public void run() {
        while (step(TIME_STEP) != -1) {
            goToLightSource();
            printInfo("GoToLightSource");
        }
    }

    private double calcLeftSpeed(){
        return (sensors[S_RIGHT].getValue() - sensors[S_LEFT].getValue());
    }

    private double calcRightSpeed(){
        return (sensors[S_LEFT].getValue() - sensors[S_RIGHT].getValue());
    }

    private void goToLightSource(){
        double speedLeft = MAX_SPEED - calcLeftSpeed();
        double speedRight = MAX_SPEED - calcRightSpeed();
        setSpeed(Math.min(MAX_SPEED, speedLeft), Math.min(MAX_SPEED, speedRight));
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

}
