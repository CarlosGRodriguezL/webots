/**
 * Created by Dominik on 17.12.2015.
 */
public class ProportionalControllerD extends AbstractController {

    public ProportionalControllerD() {
        super();
    }

    /**
     * User defined function for initializing and running the
     * ProportionalControllerD class
     */
    public void run() {
        while (step(TIME_STEP) != -1) {
            balanceBall();
            printDistanceInfo("BallBalance");
        }
    }

    private double calcRightSpeed(){
        return (distanceSensors[S_FRONT_LEFT].getValue() + distanceSensors[S_LEFT].getValue())/2;
    }

    private double calcLeftSpeed(){
        return distanceSensors[S_FRONT_LEFT].getValue() + distanceSensors[S_LEFT].getValue();
    }

    private void balanceBall(){
        double speedLeft = MAX_SPEED + calcLeftSpeed();
        double speedRight = MAX_SPEED ;
        setSpeed(Math.min(MAX_SPEED, speedLeft), Math.min(MAX_SPEED, speedRight));
    }

    /**
     * Main method - in this method an instance of the controller is created and
     * the method to launch the robot is called.
     *
     * @param args
     */
    public static void main(String[] args) {
        ProportionalControllerD controller = new ProportionalControllerD();
        controller.run();
    }

}
