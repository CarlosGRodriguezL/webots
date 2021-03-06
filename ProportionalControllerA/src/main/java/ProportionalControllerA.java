/**
 * Created by Dominik on 17.12.2015.
 */
public class ProportionalControllerA extends AbstractController {

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
            printLightInfo("GoToLightSource");
        }
    }

    private double calcLeftSpeed(){
        return (lightSensors[S_RIGHT].getValue() - lightSensors[S_LEFT].getValue());
    }

    private double calcRightSpeed(){
        return (lightSensors[S_LEFT].getValue() - lightSensors[S_RIGHT].getValue());
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
