
/**
 * A barrier that absorbs laser shots.
 *
 * @author Yusuf Rahman
 * @version 1.1
 */
public class Barrier extends Sprite {

  private int durability = 5;
  
  /**
   * Create a barrier with a specified position, image, and size.
   */
  public Barrier(double x, double y, String path, int height, int width) {
    super(x, y, path, height,width);
  }

  protected int getDurability() { return durability; }

  protected void decrementDurability() { durability--; }
}
