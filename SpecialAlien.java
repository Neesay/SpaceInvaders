import java.util.Random;
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

    @Override
    public void move(){
        this.setX(this.getX() - 1.2);
        Random rand = new Random();
        points = rand.nextInt(160) + 40;
        if (x<-60){
            this.setDead();
        }
    }
}
