import java.util.Random;
public class SpecialAlien extends Alien{
    /**
     * Creates a special alien with a specified position, image, and size.
     */
    public SpecialAlien(double x, double y, String path, int height, int width) {
        super(x, y, path, height, width);
    }

    /**
     * Moves the special alien across the screen and sets a random points value.
     */
    @Override
    public void move() {
        this.setX(this.getX() - 1.2);
        Random rand = new Random();
        points = rand.nextInt(160) + 40;
        if (x<-60) {
            this.setDead();
        }
    }
}
