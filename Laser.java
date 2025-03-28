import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.image.ImageView;

/**
 *  Creates lasers which aliens and player shoot.
 *  This class manages the lasers.
 *
 * @author Yaseen Alam, Aditya Ranjan, Kasim Morsel, Yusuf Rahman
 * @version 2.1
 */

public class Laser {
    private Rectangle laser;  
    private int speed;
    private int maxYConstraint;
    private boolean outofscreen;
    private boolean fromPlayer;
    
    /**
     * Constructor for objects of class Laser.
     */
    
    public Laser(double x, double y, int speed) {
        this.laser = new Rectangle(4, 20, Color.RED);  
        this.laser.setX(x - laser.getWidth() / 2); 
        this.laser.setY(y - laser.getHeight() / 2);  
        this.speed = speed;
        this.maxYConstraint = 800;
        this.fromPlayer = true;
    }
    
    /**
     * Destroys laser object.
     */
    
    private void destroy() {
        if (laser.getY() <= -50 || laser.getY() >= maxYConstraint + 50) {
            outofscreen = true;
        }
    }
    
    /**
     * Updates the laser.
     */
    
    public void update() {
        laser.setY(laser.getY() - speed);  
        destroy();
    }

    /**
     * Returns the laser.
     * @return laser.
     */
    
    public Rectangle getLaser() {
        return laser;
    }
    
    /**
     * Returns whether laser is out of screen.
     * @return outofscreen.
     */
    
    public boolean get_outofscreen(){
        return outofscreen;
    }
    
    /**
     * Sets whether the player shot the laser or the alien.
     */
    
    public void setPlayer(){
        this.fromPlayer = false;
    }
    
    /**
     * Returns whether the laser is shot by alien or player.
     * @return this.fromPlayer.
     */
    
    public boolean getStatus(){
        return this.fromPlayer;
    }
    
}
