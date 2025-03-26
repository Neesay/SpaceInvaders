import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

/**
 * Write a description of class Alien here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Alien extends Sprite {
    protected int points;
    protected int hp;
    protected boolean dead;

    /**
     * Constructor for objects of class Alien
     */
    public Alien(int x, int y, String path, int height, int width) {
        super(x,y,path,height,width);
        hp = 1;
        this.dead = false;
    }
    
    public int getPoints()
    {
        return points;
    }
    
    private void move(){
        this.setX(this.getX() + 3);  
    }

    public void update() {
        //move()
        ;
    }
    
    public boolean getDead(){
        return dead;
    }
    
    public void setDead(){
        dead = true;
    }
}
