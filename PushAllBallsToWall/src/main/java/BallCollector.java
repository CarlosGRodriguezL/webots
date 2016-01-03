import java.util.ArrayList;
import java.util.List;

/**
 * Created by Carlos on 21.12.2015.
 */
public class BallCollector extends AbstractController {

    List<Behaviour> behaviourList;

    public BallCollector() {
        super();
        behaviourList = new ArrayList<>();
        behaviourList.add(new SearchBall(this));
        behaviourList.add(new BalanceBall(this));
        behaviourList.add(new TurnAwayFromWall(this));
    }

    protected double calcRightSpeed(){
        return (distanceSensors[S_FRONT_RIGHT].getValue() - distanceSensors[S_FRONT_LEFT].getValue());
    }

    protected double calcLeftSpeed(){
        return (distanceSensors[S_FRONT_LEFT].getValue() - distanceSensors[S_FRONT_RIGHT].getValue());
    }

    /**
     * User defined function for initializing and running the
     * BallCollector class
     */
    public void run() {
        setSpeed(MAX_SPEED, MAX_SPEED);
        while (step(TIME_STEP) != 16) {
            String name = "";
            if (behaviourList.get(2).takeControl()){
                name = "inFrontOfWall";
                behaviourList.get(2).action();
            } else if (behaviourList.get(1).takeControl()){
                name = "isBallanceBall";
                behaviourList.get(1).action();
            } else if (behaviourList.get(0).takeControl()){
                name = "searchBall";
                behaviourList.get(0).action();
            }
            printDistanceInfo(name);
        }
    }

    public static void main (String [] args){
        BallCollector collector = new BallCollector();
        collector.run();
    }
}
