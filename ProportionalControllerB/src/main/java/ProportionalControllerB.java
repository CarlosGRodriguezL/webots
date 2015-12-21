/**
 * Created by Dominik on 17.12.2015.
 */
public class ProportionalControllerB extends AbstractController {

    public static boolean flag = true;

    public ProportionalControllerB() {
        super();
    }

    /**
     * User defined function for initializing and running the
     * ProportionalControllerA class
     */
    public void run() {
        while (step(TIME_STEP) != -1 ) {
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
            double speedLeft = (MAX_SPEED - calcLeftSpeed())*(2800 - distanceSensors[S_FRONT_LEFT].getValue());
            double speedRight = (MAX_SPEED - calcRightSpeed())*(2800 - distanceSensors[S_FRONT_LEFT].getValue());
            setSpeed(Math.min(MAX_SPEED, speedLeft), Math.min(MAX_SPEED, speedRight));
    }


    /**
     * Main method - in this method an instance of the controller is created and
     * the method to launch the robot is called.
     *
     * @param args
     */
    public static void main(String[] args) {
        ProportionalControllerB controller = new ProportionalControllerB();
        controller.run();
    }

}
