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
        while (step(TIME_STEP) != -1 && flag) {
            flag = !((distanceSensors[S_FRONT_LEFT].getValue() < -10 || distanceSensors[S_FRONT_RIGHT].getValue() < -10) &&
                    (lightSensors[S_FRONT_LEFT].getValue() <= 2700 || lightSensors[S_FRONT_RIGHT].getValue() <= 2700));
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
        if(flag) {
            double speedLeft = MAX_SPEED - calcLeftSpeed();
            double speedRight = MAX_SPEED - calcRightSpeed();
            setSpeed(Math.min(MAX_SPEED, speedLeft), Math.min(MAX_SPEED, speedRight));
        }else{
            setSpeed(MIN_SPEED,MIN_SPEED);
        }
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
