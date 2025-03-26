import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import java.util.List;

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
    public int column;
    public int row;
    protected double speed;

    /**
     * Constructor for objects of class Alien
     */
    public Alien(double x, double y, String path, int height, int width) {
        super(x,y,path,height,width);
        hp = 1;
        this.dead = false;
        this.speed = 0.25;
    }
    
    public int getPoints()
    {
        return this.points;
    }
    
    private void move(List<List<Alien>> AliensPerCol){
        if (AliensPerCol.get(this.getColumn()).isEmpty()) {
            if (this.getX() < 800){
                this.setX(this.getX() + (this.speed));
            }
        }
        
        this.setX(this.getX() + (this.speed));
    }

    public void update(List<List<Alien>> AliensPerCol) {
        move(AliensPerCol);
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

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }
}
