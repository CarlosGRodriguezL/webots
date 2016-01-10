/**
 * Created by Dominik on 30.12.2015.
 */
public class TurnAwayFromWall implements Behaviour {

    public double WALL_TOLERANCE_MAX = 57;

    private BallCollector collector;

    public TurnAwayFromWall(BallCollector collector) {
        this.collector = collector;
    }

    @Override
    public boolean takeControl() {
        double xValue = Math.abs(collector.accelerometer.getValues()[0]);
        double yValue = Math.abs(collector.accelerometer.getValues()[1]);
        double zValue = Math.abs(collector.accelerometer.getValues()[2]);
        double sum = (xValue + yValue) * zValue;
        if (sum > WALL_TOLERANCE_MAX ||
                        (collector.distanceSensors[collector.S_AVG_LEFT].getValue() >= 1100 ||
                                collector.distanceSensors[collector.S_AVG_RIGHT].getValue() >= 1100) ||
                (collector.distanceSensors[collector.S_LEFT].getValue() >= 700 ||
                        collector.distanceSensors[collector.S_RIGHT].getValue() >= 700)){
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
        int counter2 = 0;
        while (collector.step(collector.TIME_STEP) != 16 && counter < 5) {
            while (collector.step(collector.TIME_STEP) != 16 && counter2 < 5) {
                counter2++;
                collector.setSpeed(-1000, -1000);
            }
            counter++;
            collector.setSpeed(1000, -1000);
        }
    }
}
