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
    
    private int speed;
    private int laser_cooldown = 600;
    private boolean ready = true;
    private long laser_time = System.currentTimeMillis();
    private int lives;
    private int score;
    
    /**
     * Constructor for objects of class Player
     */
    public Player(double x, double y, String path, int height, int width)
    {
        super(x,y,path,height,width);
        this.speed = 5;
        this.lives = 3;
        this.score = 0;
    }
    
    public int getSpeed(){
        return speed;
    }
    
    public void recharge(){
        if (!(this.ready)){
            long current_time = System.currentTimeMillis();
            if (current_time - laser_time >= laser_cooldown){
                this.ready = true;
                laser_time = current_time;
            }
        }
    }
    
    public void update(){
        recharge();
    }
    
    public boolean getReady(){
        return ready;
    }
    
    public void setReady(boolean b){
        ready = b;
    }
    
    public void decrementLives(){
        this.lives --;
    }
    
    public int getLives(){
        return this.lives;
    }
    
    public int getScore(){
        return this.score;
    }
    
    public void setScore(int amount){
        this.score += amount;
    }
}
