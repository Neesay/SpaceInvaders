/**
 *  Player is a sprite and can move left, right and shoot aliens.
 *  This class manages the player.
 *
 * @author Yaseen Alam, Aditya Ranjan, Kasim Morsel, Yusuf Rahman
 * @version 2.1
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
    public Player(double x, double y, String[] pathFrames, int height, int width)
    {
        super(x,y,pathFrames,height,width);
        this.speed = 6;
        this.lives = 3;
        this.score = 0;
    }
    
    /**
     * Returns speed of player.
     * @return speed.
     */

    public int getSpeed(){
        return speed;
    }
    
    /**
     * Ensures a cooldown between lasers shot by the player.
     */

    private void recharge(){
        if (!(this.ready)){
            long current_time = System.currentTimeMillis();
            if (current_time - laser_time >= laser_cooldown){
                this.ready = true;
                laser_time = current_time;
            }
        }
    }

    /**
     * Updates player.
     */
    
    public void update(){
        recharge();
    }
    
    /**
     * Returns whether player is ready to shoot again.
     * @return ready.
     */

    public boolean getReady(){
        return ready;
    }

    /**
     * Sets whether the player is ready to shoot.
     */
    
    public void setReady(boolean b){
        ready = b;
    }

    /**
     * Reduces life of player by one.
     */
    
    public void decrementLives(){
        this.lives --;
    }

    /**
     * Returns how many lives the player has.
     * @return this.lives.
     */
    
    public int getLives(){
        return this.lives;
    }

    /**
     * Returns score of player.
     * @return this.score.
     */
    
    public int getScore(){
        return this.score;
    }

    /**
     * Adds score by a value
     * @param amount Amount of points to add on.
     */
    
    public void addScore(int amount){
        this.score += amount;
    }

    /**
     * Resets player lives.
     */
    
    public void resetLives() {
        this.lives = 3;
    }

    /**
     * Switches to other frame when player gets hit.
     */
    
    public void switchToDieFrame() {
        setImg(getPathFrames()[1]);
    }

    /**
     * Switches back to normal player frame.
     */
    
    public void switchToAliveFrame() {
        setImg(getPathFrames()[0]);
    }
}