/**
 * Created by Dominik on 22.12.2015.
 */
public class SearchBall implements Behaviour {

    private BallCollector collector;

    public SearchBall (BallCollector collector){
        this.collector = collector;
    }


    @Override
    public boolean takeControl() {
        return true;
    }

    @Override
    public void action() {
        double speedLeft = collector.MAX_SPEED - collector.calcLeftSpeed();
        double speedRight = collector.MAX_SPEED - collector.calcRightSpeed();
        collector.setSpeed(Math.min(collector.MAX_SPEED, speedLeft), Math.min(collector.MAX_SPEED, speedRight));
    }
}
