/**
 * Created by Dominik on 17.12.2015.
 */
public class BangBangControllerD extends AbstractController {

    public static boolean flag = true;

    public static double prevLeft = 0;

    public BangBangControllerD() {
        super();
    }


    /**
     * User defined function for initializing and running the
     * BangBangControllerC class
     */
    public void run() {
        while (step(TIME_STEP) != -1) {
            // flag sagt wann der puck an der wand ist.
            if(flag){
                if(distanceSensors[S_FRONT_LEFT].getValue() < 200 &&
                        distanceSensors[S_FRONT_RIGHT].getValue() < 200 ){
                    printDistanceInfo("drive");
                    prevLeft = distanceSensors[S_LEFT].getValue();
                    driveForward();
                }else{
                    flag= false;
                }
            }else{
                if(!(distanceSensors[S_LEFT].getValue() > 100 )){
                    if(distanceSensors[S_LEFT].getValue() < prevLeft
                            || distanceSensors[S_LEFT].getValue() > 50){
                        printDistanceInfo("turnLeft:");
                        driveLeft();
                        prevLeft = distanceSensors[S_LEFT].getValue();
                    }else {
                        printDistanceInfo("turnLeft:");
                        setSpeed(MAX_SPEED, MAX_SPEED * -1);
                        prevLeft = distanceSensors[S_LEFT].getValue();
                    }
                }else if(distanceSensors[S_FRONT_LEFT].getValue() < 200 &&
                        distanceSensors[S_FRONT_RIGHT].getValue() < 200 ){
                    printDistanceInfo("drive");
                    driveForward();
                    prevLeft = distanceSensors[S_LEFT].getValue();
                }else{
                    printDistanceInfo("turnLeft: ");
                    setSpeed(MAX_SPEED, MAX_SPEED * -1);
                    prevLeft = distanceSensors[S_LEFT].getValue();
                }
            }
        }
    }

    /**
     * Main method - in this method an instance of the controller is created and
     * the method to launch the robot is called.
     *
     * @param args
     */
    public static void main(String[] args) {
        BangBangControllerD controller = new BangBangControllerD();
        controller.run();
    }


}
