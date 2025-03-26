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
        System.out.println("Alien moved!");
    }
}
