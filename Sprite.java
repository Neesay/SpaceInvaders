import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

/**
 * Write a description of class Sprite here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Sprite {
    protected ImageView image;
    protected double x,y;
    protected int width, height;
    private final Rectangle rect;
    private final String[] pathFrames;
    
    public Sprite(double x, double y, String[] pathFrames, int height, int width)
    {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.pathFrames = pathFrames;
        image = new ImageView(new Image(pathFrames[0], this.width, this.height, false, true));
        
        image.setFitWidth(width);
        image.setFitHeight(height);
        image.setPreserveRatio(true);  

        image.setX(x);
        image.setY(y);
        
        this.rect = new Rectangle(x, y, width, height);
    }
    
    public double getX(){
        return this.x;
    }
    
    public double getY(){
        return this.y;
    }
    
    public int getHeight(){
        return this.height;
    }
    
    public int getWidth(){
        return this.width;
    }
    
    public void setX(double x){
        this.x = x;
        this.rect.setX(x);
        image.setX(x);
    }
    
    public void setY(double y){
        this.y = y;
        this.rect.setY(y);
        image.setY(y);
    }
    
    public ImageView getImgView(){
        return this.image;
    }
    
    public Rectangle getRect(){
        return this.rect;
    }

    public String[] getPathFrames() {
        return pathFrames;
    }

    public void setImg(String image) {
        this.image = new ImageView(new Image(image, this.width, this.height, false, true));
    }
}
