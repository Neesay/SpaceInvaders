import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

/**
 * A game entity that can be drawn on the screen.
 *
 * @author Aditya Ranjan
 * @version 1.0
 */
public class Sprite {
    
    protected ImageView image;
    protected double x,y;
    protected int width, height;
    private Rectangle rect;
    
    /**
     * Constructor for a Sprite with a specified position, image, and size.
     */
    public Sprite(double x, double y, String path, int height, int width) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;

        this.image = new ImageView(new Image(path, this.width, this.height, false, true));
        image.setFitWidth(width);
        image.setFitHeight(height);
        image.setPreserveRatio(true);  
        image.setX(x);
        image.setY(y);
        
        // Create a rectangle for collision detection
        this.rect = new Rectangle(x, y, width, height);
    }
    
    public double getX() { return this.x; }
    
    public double getY() { return this.y; }
    
    /**
     * Set the x coordinates of the sprite and update the rectangle's position.
     */
    public void setX(double x) {
        this.x = x;
        this.rect.setX(x);
        image.setX(x);
    }
    
    /**
     * Set the y coordinates of the sprite and update the rectangle's position.
     */
    public void setY(double y) {
        this.y = y;
        this.rect.setY(y);
        image.setY(y);
    }

    public int getHeight() { return this.height; }
    
    public int getWidth() { return this.width; }
    
    public ImageView getImgView() { return this.image; }
    
    public Rectangle getRect() { return this.rect; }

}
