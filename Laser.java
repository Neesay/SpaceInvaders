import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.image.ImageView;

public class Laser {
    private Rectangle laser;  
    private int speed;
    private int maxYConstraint;
    private boolean outofscreen;
    private boolean fromPlayer;
    
    public Laser(double x, double y, int speed) {
        this.laser = new Rectangle(4, 20, Color.RED);  
        this.laser.setX(x - laser.getWidth() / 2); 
        this.laser.setY(y - laser.getHeight() / 2);  
        this.speed = speed;
        this.maxYConstraint = 800;
        this.fromPlayer = true;
    }
    
    private void destroy() {
        if (laser.getY() <= -50 || laser.getY() >= maxYConstraint + 50) {
            outofscreen = true;
        }
    }
    
    public void update() {
        laser.setY(laser.getY() - speed);  
        destroy();
    }

    
    public Rectangle getLaser() {
        return laser;
    }
    
    public boolean get_outofscreen(){
        return outofscreen;
    }
    
    public void setPlayer(){
        this.fromPlayer = false;
    }
    
    public boolean getStatus(){
        return this.fromPlayer;
    }
    
}
