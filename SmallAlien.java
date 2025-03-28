public class SmallAlien extends Alien {
    /**
     * Creates a small alien with a specified position, image, and size.
     */
    public SmallAlien(int x, int y, String path, int height, int width) {
        super(x, y, path, height, width);
        
        super.points = 10;
    }
    
}

