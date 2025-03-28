public class SmallAlien extends Alien {
    /**
     * Constructor for objects of class Alien
     *
     * @param x
     * @param y
     * @param pathFrames
     * @param height
     * @param width
     */
    public SmallAlien(int x, int y, String[] pathFrames, int height, int width) {
        super(x, y, pathFrames, height, width);
        points = 10;
    }
    
}

