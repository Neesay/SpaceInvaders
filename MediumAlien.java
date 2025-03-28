public class MediumAlien extends Alien {
    /**
     * Constructor for objects of class Alien
     *
     * @param x
     * @param y
     * @param pathFrames
     * @param height
     * @param width
     */
    public MediumAlien(int x, int y, String[] pathFrames, int height, int width) {
        super(x, y, pathFrames, height, width);

        points = 20;
    }
}
