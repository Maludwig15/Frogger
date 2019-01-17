public class Frog extends Sprite {

    public Frog(){
        super(500, 400, NORTH);
        setPic("frog1.png", NORTH);
        //moves the height of the image.
        setSpeed(this.getBoundingRectangle().height);

    }


    //@Override some methods...


    @Override
    public void update(){


        super.update();


    }




}
