public class MediumAlien extends Alien {
    /**
     * Constructor for objects of class Alien
     *
     * @param x
     * @param y
     * @param path
     * @param height
     * @param width
     */
    public MediumAlien(int x, int y, String path, int height, int width) {
        super(x, y, path, height, width);

        points = 20;
    }
}
