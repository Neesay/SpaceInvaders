import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

/**
 * Write a description of class Sprite here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Sprite
{
    
    protected ImageView image;
    protected Rectangle SpriteRect;
    protected int x,y;
    protected int width, height;
    private Rectangle rect;
    
    public Sprite(int x, int y, String path, int height, int width)
    {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        image = new ImageView(new Image(path, this.width, this.height, false, true));
        
        image.setFitWidth(width);
        image.setFitHeight(height);
        image.setPreserveRatio(true);  

        image.setX(x);
        image.setY(y);
        
        this.rect = new Rectangle(x, y, width, height);
    }
    
    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }
    
    public int getHeight(){
        return this.height;
    }
    
    public int getWidth(){
        return this.width;
    }
    
    public void setX(int x){
        this.x = x;
        this.rect.setX(x);
        image.setX(x);
    }
    
    public void setY(int y){
        this.y = y;
        this.rect.setX(y);
        image.setY(y);
    }
    
    public ImageView getImgView(){
        return this.image;
    }
    
    public Rectangle getRect(){
        return this.rect;
    }

}
