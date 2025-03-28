import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 *  An alien is a sprite that moves across the screen.
 *  It can shoot lasers and will be destroyed if hit by a laser.
 *
 * @author Yaseen Alam, Aditya Ranjan, Kasim Morsel, Yusuf Rahman
 * @version 2.1
 */

public abstract class Alien extends Sprite {
    protected int points;
    protected int hp;
    protected boolean dead;
    private int column;
    private int row;
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

    /**
     * Returns points for relevant alien.
     * @return this.points 
     */
    public int getPoints()
    {
        return this.points;
    }

    /**
     * Moves the alien.
     */
    
    public void move() {
        this.setX(this.getX() + (direction*this.speed));
    }
    
    /**
     * Returns the status of alien.
     * @return dead Boolean for whether alien is dead or not.
     */

    public boolean getDead(){
        return dead;
    }
    
    /**
     * Kills alien.
     */

    public void setDead(){
        dead = true;
    }
    
    /**
     * Sets the column.
     */

    public void setColumn(int column) {
        this.column = column;
    }
    
    /**
     * Sets the row.
     */

    public void setRow(int row) {
        this.row = row;
    }
    
    /**
     * Sets direction for the alien.
     */

    public void setDirection(double n){
        this.direction = n;
    }
    
    /**
     * Gets the direction of alien.
     * @return direction Direction of the alien (either 1 or -1 for right and left).
     */

    public double getDirection(){
        return this.direction;
    }
    
    /**
     * Switches alien frames.
     */

    public void switchFrames() {
        String nextFrame = showingFrame1 ? getPathFrames()[1] : getPathFrames()[0];
        if (nextFrame != null && !nextFrame.isEmpty()) {
            setImg(nextFrame);
        }
        showingFrame1 = !showingFrame1;
    }
}
