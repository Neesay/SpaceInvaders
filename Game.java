import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * A Space Invaders simulator that renders the game on GameDisplay class.
 * This class handles the game logic and updates the game state.
 * 
 *
 * @author Yusuf Rahman
 * @version 1.1
 */
public class Game {

    private final Set <KeyCode> keysPressed;
    private final Player player;
    private final AlienSwarm alienSwarm;
    private final List <Laser> lasers;
    private final List<Barrier> barriers;
    private List<Rectangle> blocks;
    private static final int NUMBER_OF_BARRIERS = 3;
    private final Window window;
    private ArrayList <String> shape;

    /**
     * Constructor initialises the begining game state.
     */
    public Game(int canvasWidth, Window window) {
        this.window = window;
        keysPressed = new HashSet<>();
        player = new Player(600.0,900.0, "file:./images/player.png", 30,60);
        alienSwarm = new AlienSwarm();
        lasers = new ArrayList<>();
        barriers = createBarriers(canvasWidth, 700, "file:./images/barrier.png", 85, 125);
        this.shape = new ArrayList <> (Arrays.asList("  xxxxxxx"," xxxxxxxxx","xxxxxxxxxxx","xxxxxxxxxxx","xxxxxxxxxxx","xxx     xxx","xx       xx"));
    }

    /**
     * Allow player to move left and right and shoot lasers.
     * Handle keyboard input
     */
    public void handleKeyPress(KeyEvent e) {
        Player player = getPlayer();
        List<Laser> lasers = getLasers();

        keysPressed.add(e.getCode());
        if (e.getCode() == KeyCode.SPACE && player.getReady()){
            player.setReady(false);
            Laser laser = new Laser(player.getX() + ((double) player.getWidth() /2), player.getY(), 9);
            lasers.add(laser);
        }
    }
    
    /**
     * Stop player movement when key is released.
     */
    public void handleKeyRelease(KeyEvent e) { keysPressed.remove(e.getCode()); }

    /**
     * Create barriers evenly across the game landscape.
     */
    private List<Barrier> createBarriers(int canvasWidth, int y, String path, int height, int width) {
        List<Barrier> barriers = new ArrayList<>();
        double spacing = (double) (canvasWidth - (NUMBER_OF_BARRIERS * width)) / (NUMBER_OF_BARRIERS + 1);

        for (int i = 0; i < NUMBER_OF_BARRIERS; i++) {
            double x = spacing + i * (spacing + width);
            Barrier barrier = new Barrier(x, y, path, height, width);
            barriers.add(barrier);
        }
        return barriers;
    }
    
    /**
     * Check for collisions between the player, aliens and lasers.
     */
    public void collisionDetection() {
        Iterator<Laser> lIterator = lasers.iterator();

        while (lIterator.hasNext()) {
            Laser l = lIterator.next();
            Rectangle lRect = l.getLaser();

            // Check for collisions with barriers
            Iterator<Barrier> bIterator = barriers.iterator();
            while (bIterator.hasNext()) {
                Barrier barrier = bIterator.next();
                Rectangle barrierRect = barrier.getRect();

                if (lRect.intersects(barrierRect.getBoundsInLocal())) {
                    barrier.decrementDurability();
                    lIterator.remove();

                    // Remove barrier if it has no durability left  
                    if (barrier.getDurability() <= 0) {
                        bIterator.remove();
                    }
                    break;
                }
            }

            // If the laser is from the player, check for alien hits
            if (l.getStatus()) {  

                for (Alien a : alienSwarm.getAliens()) {
                    Rectangle alienRect = a.getRect();

                    if (lRect.intersects(alienRect.getBoundsInLocal())) {
                        a.setDead();
                        player.setScore(a.getPoints());
                        
                        lIterator.remove();
                        break;  
                    }
                }
            } 
            else {  
                // Check if laser from alien hits player
                Rectangle playerRect = player.getRect();

                if (lRect.intersects(playerRect.getBoundsInLocal())) {
                    player.decrementLives();
                    lIterator.remove();
                    if (player.getLives() <= 0) {
                        window.showGameOverScreen();
                        
                    }
                }
            }
        }
    }

    /**
     * Update the lasers in the game.
     */
    public void updateLasers() {
        List<Laser> lasers = getLasers();
        ArrayList <Laser> newLasers = new ArrayList<>();
        for (Laser l: lasers){
            l.update();
            if (!(l.get_outofscreen())){
                newLasers.add(l);
            }
        }
    }

    protected Set<KeyCode> getKeysPressed() { return keysPressed; }
    
    public Player getPlayer() { return player; }

    public AlienSwarm getAlienSwarm() { return alienSwarm; }

    public List<Laser> getLasers() { return lasers; }

    public List<Barrier> getBarriers() { return barriers; }
}