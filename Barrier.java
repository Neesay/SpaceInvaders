
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
  public Barrier(double x, double y, String path, int height, int width) {
    super(x, y, path, height,width);
  }

  public int getDurability() { return durability; }

  public void decrementDurability() { durability--; }
}
