import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class FroggerMain extends JPanel {



    //MATT LUDWIG


    //instance fields for the general environment
    public static final int FRAMEWIDTH = 1000, FRAMEHEIGHT = 600;
    private Timer timer;
    private boolean[] keys;
    public static final int NORTH = 90, SOUTH = 270, WEST = 180, EAST = 0, NE = 45, NW = 135, SW = 225, SE = 315;


    private Sprite frog;
    private LawnMower Steve;
    private logLarge Joe;
    private logLarge Jessica;
    private logLarge Mom;
    private logLarge Dad;
    private logLarge Dog;
    private logShort Cat;
    private logLarge Lateef;


    private logMedium Rob;
    private logShort Andrew;
    private truck Henrey;
    private car1 Tony;
    private Turtle Derek;
    private car2 Billy;
    private car3 Amanda;
    private otter otter;
    private oger George;
    private snake andy;


    private ArrayList<Sprite> obstacles;
    private ArrayList<Log> fullofLogs;

    private int Lives;
    private int Level;

    private int counter;
    private double rand;
    private int x;
    private int y;




    public FroggerMain(){

        keys = new boolean[512]; //should be enough to hold any key code.


        Level = 0;
        Lives = 50;
        frog = new Frog();




        x = 1000* (int) Math.random();
        y = 1000* (int) Math.random();


        otter = new otter(100, 100);


        Dog = new logLarge(200, 0, WEST);
        Cat = new logShort(50, 25, WEST);
        Lateef = new logLarge(400, 25, WEST);
        Jessica = new logLarge(250, 50, WEST);
        Joe = new logLarge(240, 75 , WEST);
        Rob = new logMedium(230, 100, WEST);
        Andrew = new logShort(280, 125, WEST);
        Mom = new logLarge(300, 150, WEST);
        Dad = new logLarge(210, 178, WEST);





        Steve = new LawnMower(150, 250, EAST);
        Henrey = new truck(405, 275, NORTH);
        Derek = new Turtle(120, 100, EAST);
        Tony = new car1(100, 325, SOUTH);
        Billy = new car2(200, 350, SOUTH);
        Amanda = new car3 (50, 370, SOUTH);

        andy = new snake(0, 400);
        George = new oger(0, 200, NORTH);



        fullofLogs = new ArrayList<Log>();

        fullofLogs.add(Joe);
        fullofLogs.add(Rob);
        fullofLogs.add(Andrew);
        fullofLogs.add(Jessica);
        fullofLogs.add(Mom);
        fullofLogs.add(Dad);
        fullofLogs.add(Dog);
        fullofLogs.add(Cat);
        fullofLogs.add(Lateef);



        obstacles = new ArrayList<Sprite>();

        obstacles.add(Tony);
        obstacles.add(otter);
        obstacles.add(Billy);
        obstacles.add(Derek);
        obstacles.add(Steve);
        obstacles.add(Henrey);
        obstacles.add(Amanda);





        timer = new Timer(40, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {


                //move the frog up
                if(keys[KeyEvent.VK_W]){
                    frog.setDir(Sprite.NORTH);
                    frog.update();
                    keys[KeyEvent.VK_W] = false; //probably.
                }

                //update each obstacle
                //check for collisions

                for(Sprite o: obstacles){
                    o.update();
//                    System.out.println("check");

                    if(frog.intersects(o) == true){
                        frog.setLoc(new Point(400, 400));
                        System.out.println("hit");
                        Lives--;
                    }


                }


                if(Lives == 0 || Lives < 0){
                    timer.stop();
                }

                boolean dead = false;
                if(frog.getLoc().getY() < 180)
                    dead  = true;


                for(Sprite h: fullofLogs) {
                    h.update();
                    if (frog.intersects(h) == true) {
                        System.out.println("on log");
                        frog.setLoc(new Point((int) (frog.getLoc().getX() + -h.getSpeed()), (int) (frog.getLoc().getY())));
                        dead = false;
                    }
                }

                   if(dead){

                            Lives--;
                            frog.setLoc(new Point(400, 400));

                    }



                    //if frog hits the water and is not on a log



                if(frog.getLoc().getY() < 10 ){
                    Level++;
                    frog.setLoc(new Point(400, 400));
                }


//                //bounds
//                if(frog.getLoc().getX() < 0 || frog.getLoc().getX() > 1000 ){
//                    Lives--;
//                    frog.setLoc(new Point(400, 400));
//                }



                if(Level == 1){
                    obstacles.add(andy);
                    if(frog.intersects((andy))){
                        andy.setLoc( new Point(1000,1000));
                        obstacles.remove(andy);
                    }

                }

                if(Level == 2){
                    obstacles.add(George);
                    if(frog.intersects((George))){
                        George.setLoc( new Point(1000,1000));
                        obstacles.remove(George);
                    }

                }

                if(Level == 3){

                    if(otter.getLoc().getX() > 1005){
                        otter.setSpeed(-15);
                    }

                    otter.setSpeed(15);
                    if(otter.getLoc().getX() < 0 ){
                        otter.setSpeed(-15);
                    }





                }



                //move the frog down
                if(keys[KeyEvent.VK_S]){
                    frog.setDir(Sprite.SOUTH);
                    frog.update();
                    keys[KeyEvent.VK_S] = false; //probably.
                }


                //move the frog Left
                if(keys[KeyEvent.VK_A]){
                    frog.setDir(Sprite.WEST);
                    frog.update();
                    keys[KeyEvent.VK_A] = false; //probably.
                }


                //move the frog Right
                if(keys[KeyEvent.VK_D]){
                    frog.setDir(Sprite.EAST);
                    frog.update();
                    keys[KeyEvent.VK_D] = false; //probably.
                }



                //logs bounce back

                for(Sprite h: fullofLogs){
                    if(h.getLoc().getX() < -5){
                        h.setSpeed(-h.getSpeed());
                    }

                    if(h.getLoc().getX() > 1005 ){
                        h.setSpeed(-h.getSpeed());
                    }
                }


                //obstacles bounce back

                for(Sprite o: obstacles){
                    if(o.getLoc().getX() < -5){
                        o.setSpeed(-o.getSpeed());
                    }

                    if(o.getLoc().getX() > 975 ){
                        o.setSpeed(-o.getSpeed());
                    }


                }


                repaint();
            }
        });
        timer.start();

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                keys[keyEvent.getKeyCode()] = true;
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                keys[keyEvent.getKeyCode()] = false;
            }
        });

    }

    //Our paint method.
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.BLUE);
        g2.fillRect(0, 0, 1000, 200);

        g2.setColor(Color.GREEN);
        g2.fillRect(0, 200, 1000, 600);

        g2.setColor(Color.GRAY);
        g2.fillRect(0, 250, 1000, 150);



        otter.draw(g2);

        for(Sprite o: obstacles){ //for each of the Sprites in the arrayList obstacles draw it
            o.draw(g2);
        }


        for(Log h: fullofLogs){
            h.draw(g2);
        }


        frog.draw(g2);


        g2.setColor(Color.BLUE);
        g2.drawString("Lives:" + Lives, 300, 300 );
        g2.drawString("Level:" + Level, 300, 320 );

        if(Level == 3 && frog.intersects(George)){
            frog.update();
            g2.setColor(Color.RED);
            g2.drawString("GAME OVER!", 500, 500 );
        }

        if(Lives == 0){
            frog.update();
            g2.setColor(Color.RED);
            g2.drawString("GAME OVER!", 500, 500 );
        }


    }

    //sets ups the panel and frame.
    public static void main(String[] args) {
        JFrame window = new JFrame("Frogger Project: Matt Ludwig");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBounds(0, 0, FRAMEWIDTH, FRAMEHEIGHT + 22); //(x, y, w, h) 22 due to title bar.

        //(width = 1000, height = 600)

        FroggerMain panel = new FroggerMain();
        panel.setSize(FRAMEWIDTH, FRAMEHEIGHT);



        panel.setFocusable(true);
        panel.grabFocus();

        window.add(panel);
        window.setVisible(true);
        window.setResizable(false);
    }
}