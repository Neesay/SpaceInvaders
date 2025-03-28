/**
 * Small alien which has 10 points.
 *
 * @author Yaseen Alam, Aditya Ranjan, Kasim Morsel, Yusuf Rahman
 * @version 2.1
 */

public class SmallAlien extends Alien {
    /**
     * Creates a small alien with a specified position, image, and size.
     */
    public SmallAlien(int x, int y, String[] pathFrames, int height, int width) {
        super(x, y, pathFrames, height, width);

        super.points = 10;
    }
}