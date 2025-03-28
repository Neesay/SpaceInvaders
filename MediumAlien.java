public class MediumAlien extends Alien {
    /**
     * Creates a medium alien with a specified position, image, and size.
     */
    public MediumAlien(int x, int y, String path, int height, int width) {
        super(x, y, path, height, width);

        super.points = 20;
    }
}
