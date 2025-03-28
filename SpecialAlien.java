import java.util.Random;


/**
 * Special alien which travels across the screen.
 *
 * @author Yaseen Alam, Aditya Ranjan, Kasim Morsel, Yusuf Rahman
 * @version 2.1
 */

public class SpecialAlien extends Alien{
    /**
     * Constructor for objects of class Alien
     *
     * @param x
     * @param y
     * @param pathFrames
     * @param height
     * @param width
     */
    public SpecialAlien(double x, double y, String[] pathFrames, int height, int width) {
        super(x, y, pathFrames, height, width);
    }

    /**
     * Overrides move() method in super class to ensure special alien moves to the left at a different speed.
     */
    
    @Override
    public void move(){
        this.setX(this.getX() - 1.2);
        Random rand = new Random();
        points = rand.nextInt(491) + 10;
        if (x<-60){
            this.setDead();
        }
    }
}
