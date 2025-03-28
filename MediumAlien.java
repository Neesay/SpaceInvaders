/**
 * Medium alien which has 20 points.
 *
 * @author Yaseen Alam, Aditya Ranjan, Kasim Morsel, Yusuf Rahman
 * @version 2.1
 */

public class MediumAlien extends Alien {
    /**
     * Creates a medium alien with a specified position, image, and size.
     */
    public MediumAlien(int x, int y, String[] pathFrames, int height, int width) {
        super(x, y, pathFrames, height, width);

        super.points = 20;
    }
}