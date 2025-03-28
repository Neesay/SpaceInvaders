public class SpecialAlien extends Alien{
    /**
     * Creates a special alien with a specified position, image, and size.
     */
    public SpecialAlien(double x, double y, String path, int height, int width) {
        super(x, y, path, height, width);
    }

    @Override
    public void move() {
        double currentX = super.getX();
        super.setX(currentX - 1.2);
    }
}
