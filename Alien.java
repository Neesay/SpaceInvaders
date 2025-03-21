import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

/**
 * Write a description of class Alien here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Alien extends Sprite
{
    protected int points;
    protected int hp;

    /**
     * Constructor for objects of class Alien
     */
    public Alien()
    {
        hp = 1;
    }
    
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public int getPoints()
    {
        return points;
    }
}
