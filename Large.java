import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
/**
 * Write a description of class Large here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Large extends Alien
{
    /**
     * Constructor for objects of class Large
     */
    public Large()
    {
        points = 40;
        this.image = new Image(getClass().getResource("file:images/large.png").toExternalForm());
        ImageView imgview = new ImageView(image);
        imgview.setFitWidth(300);  
        imgview.setPreserveRatio(true);
        this.SpriteRect = new Rectangle(100,200);
    }
}
