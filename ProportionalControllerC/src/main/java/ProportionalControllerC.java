/**
 * Created by Dominik on 17.12.2015.
 */
public class ProportionalControllerC extends AbstractController {

    public ProportionalControllerC() {
        super();
    }

    /**
     * User defined function for initializing and running the
     * ProportionalControllerC class
     */
    public void run() {
        while (step(TIME_STEP) != -1) {
            balanceBall();
            printDistanceInfo("BallBalance");
        }
    }

    private double calcRightSpeed(){
        return (distanceSensors[S_FRONT_RIGHT].getValue() - distanceSensors[S_FRONT_LEFT].getValue());
    }

    private double calcLeftSpeed(){
        return (distanceSensors[S_FRONT_LEFT].getValue() - distanceSensors[S_FRONT_RIGHT].getValue());
    }

    private void balanceBall(){
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
        ProportionalControllerC controller = new ProportionalControllerC();
        controller.run();
    }

}
