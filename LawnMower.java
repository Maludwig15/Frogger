import java.awt.*;

/**
 * Created by matthew_ludwig on 2/14/17.
 */
public class LawnMower extends Obstacle {

    public LawnMower(int x, int y, int direction) {
        super(x, y, EAST);
        setPic("lawnmower.gif", NORTH);
        setSpeed(30);


    }


    @Override
    public void update() {


        super.update();




    }
}




