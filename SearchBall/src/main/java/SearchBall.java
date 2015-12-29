/**
 * Created by Dominik on 22.12.2015.
 */
public class SearchBall extends AbstractController {

    private double WALL_TOLERANZ_MAX = 17.8;
    private double WALL_TOLERANZ_MIN = 5;

    public SearchBall (){
        super();
    }

    private double calcRightSpeed(){
        return (distanceSensors[S_FRONT_RIGHT].getValue() - distanceSensors[S_FRONT_LEFT].getValue());
    }

    private double calcLeftSpeed(){
        return (distanceSensors[S_FRONT_LEFT].getValue() - distanceSensors[S_FRONT_RIGHT].getValue());
    }

    private boolean isInFrontOfWall() {
        double xValue = Math.abs(accelerometer.getValues()[0]);
        double yValue = Math.abs(accelerometer.getValues()[1]);
        double zValue = Math.abs(accelerometer.getValues()[2]);
        double sum = xValue + yValue + zValue;
        if (sum > WALL_TOLERANZ_MAX || sum < WALL_TOLERANZ_MIN ||
                (distanceSensors[S_FRONT_LEFT].getValue() >= 2700 &&
                        distanceSensors[S_FRONT_RIGHT].getValue() >= 2700 &&
                        (distanceSensors[S_AVG_LEFT].getValue() >= 700 ||
                                distanceSensors[S_AVG_RIGHT].getValue() >= 700))){
            return true;
        }
        else {
            return false;
        }
    }

    private boolean isInFrontOfBall(){
        if (distanceSensors[S_FRONT_LEFT].getValue() >= 100 || distanceSensors[S_FRONT_RIGHT].getValue() >= 100){
            return true;
        }
        else {
            return false;
        }
    }

    private void searchBall(){
        double speedLeft = MAX_SPEED - calcLeftSpeed();
        double speedRight = MAX_SPEED - calcRightSpeed();
        setSpeed(Math.min(MAX_SPEED, speedLeft), Math.min(MAX_SPEED, speedRight));
    }

    private boolean isBallanceBall(){
        if (distanceSensors[S_FRONT_LEFT].getValue() <= 2800 && distanceSensors[S_FRONT_RIGHT].getValue() <= 2800){
            return true;
        } else {
            return false;
        }
    }


    private void turn(){
        int counter = 0;
        while (step(TIME_STEP) != 16 && counter < 30) {
            counter++;
            setSpeed(1000, -1000);
        }
    }
    /**
     * User defined function for initializing and running the
     * SearchBall class
     */
    public void run() {
        setSpeed(MAX_SPEED, MAX_SPEED);
        while (step(TIME_STEP) != 16) {
            String name = "";
            if (isInFrontOfWall()){
                name = "inFrontOfWall";
                turn();
            } else if (isInFrontOfBall() && !isInFrontOfWall() && isBallanceBall()){
                name = "isBallanceBall";
                ballanceBall();
            } else {
                name = "searchBall";
                searchBall();
            }
            printDistanceInfo(name);
        }
    }

    private void ballanceBall() {
        double speedLeft = MAX_SPEED - calcLeftSpeed();
        double speedRight = MAX_SPEED - calcRightSpeed();
        setSpeed(Math.min(MAX_SPEED, speedLeft), Math.min(MAX_SPEED, speedRight));
    }
}
