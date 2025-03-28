/**
 * Large alien which has 40 points.
 *
 * @author Yaseen Alam, Aditya Ranjan, Kasim Morsel, Yusuf Rahman
 * @version 2.1
 */

public class LargeAlien extends Alien {
    /**
     * Creates a large alien with a specified position, image, and size.
     */
    public LargeAlien(int x, int y, String[] pathFrames, int height, int width) {
        super(x, y, pathFrames, height, width);

        super.points = 40;
    }
}