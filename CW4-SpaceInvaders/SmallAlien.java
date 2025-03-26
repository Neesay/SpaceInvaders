public class SmallAlien extends Alien {
    /**
     * Constructor for objects of class Alien
     *
     * @param x
     * @param y
     * @param path
     * @param height
     * @param width
     */
    public SmallAlien(int x, int y, String path, int height, int width) {
        super(x, y, path, height, width);
        points = 10;
    }
    
}
