/**
 * Created by Dominik on 30.12.2015.
 */
public class BalanceBall implements Behaviour {

    public double WALL_TOLERANCE_MAX = 18;
    public double WALL_TOLERANCE_MIN = 5;

    private BallCollector collector;

    public BalanceBall (BallCollector collector){
        this.collector = collector;
    }

    @Override
    public boolean takeControl() {
        double xValue = Math.abs(collector.accelerometer.getValues()[0]);
        double yValue = Math.abs(collector.accelerometer.getValues()[1]);
        double zValue = Math.abs(collector.accelerometer.getValues()[2]);
        double sum = xValue + yValue + zValue;
        if(
            (collector.distanceSensors[collector.S_FRONT_LEFT].getValue() <= 2800 &&
                collector.distanceSensors[collector.S_FRONT_RIGHT].getValue() <= 2800)
            && (collector.distanceSensors[collector.S_FRONT_LEFT].getValue() >= 100 ||
                    collector.distanceSensors[collector.S_FRONT_RIGHT].getValue() >= 100)
            && !(sum > WALL_TOLERANCE_MAX || sum < WALL_TOLERANCE_MIN)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void action() {
        double speedLeft = collector.MAX_SPEED - collector.calcLeftSpeed();
        double speedRight = collector.MAX_SPEED - collector.calcRightSpeed();
        collector.setSpeed(Math.min(collector.MAX_SPEED, speedLeft), Math.min(collector.MAX_SPEED, speedRight));
    }
}
