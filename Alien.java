import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 * Write a description of class Alien here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class Alien extends Sprite {
    protected int points;
    protected int hp;
    protected boolean dead;
    public int column;
    public int row;
    protected double speed;
    protected double direction;
    protected boolean showingFrame1;

    /**
     * Constructor for objects of class Alien
     */
    public Alien(double x, double y, String[] pathFrames, int height, int width) {
        super(x,y,pathFrames,height,width);
        hp = 1;
        this.dead = false;
        this.speed = 0.25;
        this.direction = 1;
    }
    
    public int getPoints()
    {
        return this.points;
    }

    public void move() {
        this.setX(this.getX() + (direction*this.speed));
    }
    
    public boolean getDead(){
        return dead;
    }
    
    public void setDead(){
        dead = true;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setRow(int row) {
        this.row = row;
    }
    
    public void setDirection(double n){
        this.direction = n;
    }
    
    public double getDirection(){
        return this.direction;
    }

    public void switchFrames() {
        String nextFrame = showingFrame1 ? getPathFrames()[1] : getPathFrames()[0];
        if (nextFrame != null && !nextFrame.isEmpty()) {
            setImg(nextFrame);
        }
        showingFrame1 = !showingFrame1;
    }
}
