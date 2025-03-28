import java.util.ArrayList;
import java.util.Arrays;
/**
 * A barrier that absorbs laser shots.
 *
 * @author Yusuf Rahman
 * @version 1.1
 */
public class Barrier extends Sprite {

  private int durability = 5;
  ArrayList <String> shape;
  
  /**
   * Create a barrier with a specified position, image, and size.
   */
  public Barrier(double x, double y, String path, int height, int width) {
    super(x, y, path, height,width);
    this.shape = new ArrayList <> (Arrays.asList("  xxxxxxx"," xxxxxxxxx","xxxxxxxxxxx","xxxxxxxxxxx","xxxxxxxxxxx","xxx     xxx","xx       xx"));
  }

  public int getDurability() { return durability; }

  public void decrementDurability() { durability--; }
        
  protected ArrayList <String> getShape() { return this.shape; }
  
}
