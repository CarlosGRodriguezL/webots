/**
 * Created by Dominik on 30.12.2015.
 */
public class TurnAwayFromWall implements Behaviour {

    public double WALL_TOLERANCE_MAX = 17.9;
    public double WALL_TOLERANCE_MIN = 5;

    private BallCollector collector;

    public TurnAwayFromWall(BallCollector collector) {
        this.collector = collector;
    }

    @Override
    public boolean takeControl() {
        double xValue = Math.abs(collector.accelerometer.getValues()[0]);
        double yValue = Math.abs(collector.accelerometer.getValues()[1]);
        double zValue = Math.abs(collector.accelerometer.getValues()[2]);
        double sum = xValue + yValue + zValue;
        if (sum > WALL_TOLERANCE_MAX || sum < WALL_TOLERANCE_MIN ||
                (collector.distanceSensors[collector.S_FRONT_LEFT].getValue() >= 2700 ||
                        collector.distanceSensors[collector.S_FRONT_RIGHT].getValue() >= 2700 &&
                        (collector.distanceSensors[collector.S_AVG_LEFT].getValue() >= 700 ||
                                collector.distanceSensors[collector.S_AVG_RIGHT].getValue() >= 700))){
            System.out.println("TurnAwayFromWall: true " + sum);
            return true;
        }
        else {
            System.out.println("TurnAwayFromWall: false " + sum);
            return false;
        }
    }

    @Override
    public void action() {
        int counter = 0;
        while (collector.step(collector.TIME_STEP) != 16 && counter < 30) {
            counter++;
            collector.setSpeed(1000, -1000);
        }
    }
}
