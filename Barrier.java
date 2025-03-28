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
  public Barrier(double x, double y, String[] pathFrames, int height, int width) {
    super(x, y, pathFrames, height,width);
  }

  public int getDurability() { return durability; }

  public void decrementDurability() { durability--; }
}
