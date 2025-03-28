import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * A barrier that absorbs laser shots.
 *
 * @author Yusuf Rahman
 * @version 1.1
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

  public int getDurability() { return durability; }

  public void decrementDurability() { durability--; }

  public void switchFrames() {
    String nextFrame = getPathFrames()[durability + 1];
    if (nextFrame != null && !nextFrame.isEmpty()) {
      setImg(nextFrame);
    }
  }
}