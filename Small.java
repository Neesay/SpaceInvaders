import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
/**
 * Write a description of class Small here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Small extends Alien
{

    /**
     * Constructor for objects of class Small
     */
    public Small()
    {
        points = 10;
        this.image = new Image(getClass().getResource("file:images/small.png").toExternalForm());
        ImageView imgview = new ImageView(image);
        imgview.setFitWidth(300);  
        imgview.setPreserveRatio(true);
        this.SpriteRect = new Rectangle(100,200);
    }

}
