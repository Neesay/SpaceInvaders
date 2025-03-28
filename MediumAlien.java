public class MediumAlien extends Alien {
    /**
     * Creates a medium alien with a specified position, image, and size.
     */
    public MediumAlien(int x, int y, String[] pathFrames, int height, int width) {
        super(x, y, pathFrames, height, width);

        super.points = 20;
    }
}