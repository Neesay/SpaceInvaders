public class LargeAlien extends Alien {
    /**
     * Creates a large alien with a specified position, image, and size.
     */
    public LargeAlien(int x, int y, String path, int height, int width) {
        super(x, y, path, height, width);

        super.points = 40;
    }
}
