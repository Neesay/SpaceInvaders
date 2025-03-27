import java.util.Random;
public class SpecialAlien extends Alien{
    /**
     * Constructor for objects of class Alien
     *
     * @param x
     * @param y
     * @param path
     * @param height
     * @param width
     */
    public SpecialAlien(double x, double y, String path, int height, int width) {
        super(x, y, path, height, width);
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
