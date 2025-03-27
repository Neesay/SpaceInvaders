<<<<<<< Updated upstream

=======
import java.util.ArrayList;
import java.util.Arrays;
>>>>>>> Stashed changes
/**
 * A barrier that absorbs laser shots.
 *
 * @author Yusuf Rahman
 * @version 1.1
 */
public class Barrier extends Sprite {

  private int durability = 5;
<<<<<<< Updated upstream
  
=======
  ArrayList <String> shape;
>>>>>>> Stashed changes
  /**
   * Create a barrier with a specified position, image, and size.
   */
  public Barrier(double x, double y, String path, int height, int width) {
    super(x, y, path, height,width);
<<<<<<< Updated upstream
=======
    this.shape = new ArrayList <> (Arrays.asList("  xxxxxxx"," xxxxxxxxx","xxxxxxxxxxx","xxxxxxxxxxx","xxxxxxxxxxx","xxx     xxx","xx       xx"));
>>>>>>> Stashed changes
  }

  protected int getDurability() { return durability; }

<<<<<<< Updated upstream
  protected void decrementDurability() { durability--; }
=======
  protected void decrementDurability() { 
      durability--; 
    }
        
  protected ArrayList <String> getShape(){
      return this.shape;
  }
  
>>>>>>> Stashed changes
}
