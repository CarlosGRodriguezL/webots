import java.util.ArrayList;

/**
 * Created by Carlos on 21.12.2015.
 */
public class BallCollector extends AbstractController {

    public BallCollector() {
        super();
    }

    private static void startToCollectBalls(){

        SearchBall searchBall = new SearchBall();

        searchBall.run();
    }

    public static void main (String [] args){
        startToCollectBalls();
    }
}
