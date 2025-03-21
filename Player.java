import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
/**
 * Write a description of class Player here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Player extends Sprite
{
    
    private int x;

    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        this.image = new Image(getClass().getResource("file:images/player.png").toExternalForm());
        ImageView imgview = new ImageView(image);
        imgview.setFitWidth(300);  
        imgview.setPreserveRatio(true);
        this.SpriteRect = new Rectangle(100,200);
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public int sampleMethod(int y)
    {
        // put your code here
        return x + y;
    }
}
