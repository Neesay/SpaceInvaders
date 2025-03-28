import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * A barrier that absorbs laser shots.
 *
 * @author Yaseen Alam, Aditya Ranjan, Kasim Morsel, Yusuf Rahman
 * @version 2.1
 */
public class Barrier extends Sprite {

  private int durability = 8;

  /**
   * Create a barrier with a specified position, image, and size.
   */
  public Barrier(double x, double y, String[] pathFrames, int height, int width) {
    super(x, y, pathFrames, height,width);

    this.image = new ImageView(new Image(pathFrames[0], this.width, this.height, false, true));

    image.setFitWidth(width);
    image.setFitHeight(height);
    image.setPreserveRatio(true);

    image.setX(x);
    image.setY(y);
  }

  /**
  * Returns durability of the barrier.
  * @return durability.
  */
 
  public int getDurability() { return durability; }

  /**
  * Reduces durability of barriers.
  */
 
  public void decrementDurability() { durability--; }

  /**
  * Switches frame of barriers.
  */
 
  public void switchFrames() {
    String nextFrame = getPathFrames()[durability + 1];
    if (nextFrame != null && !nextFrame.isEmpty()) {
      setImg(nextFrame);
    }
  }
}