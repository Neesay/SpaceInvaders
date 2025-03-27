import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

/**
 * A Space Invaders simulator that renders the game on GameDisplay class.
 *
 * @author Yusuf Rahman
 * @version 1.1
 */
public class Game {

    private final Set <KeyCode> keysPressed;
    private final Player player;
    private final AlienSwarm alienSwarm;
    private List <Laser> lasers;
    private List<Barrier> barriers;
    private static final int NUMBER_OF_BARRIERS = 3;

    /**
     * Constructor initialises the begining game state.
     */
    public Game() {
        keysPressed = new HashSet<>();
        player = new Player(600.0,700.0, "file:./images/player.png", 30,60);
        alienSwarm = new AlienSwarm();
        lasers = new ArrayList<>();
        initialiseBarriers();
    }

    protected void handleKeyPress(KeyEvent e) {
        Player player = getPlayer();
        List<Laser> lasers = getLasers();

        keysPressed.add(e.getCode());
        if (e.getCode() == KeyCode.SPACE && player.getReady()){
            player.setReady(false);
            Laser laser = new Laser(player.getX() + ((double) player.getWidth() /2), player.getY(), 9);
            lasers.add(laser);
        }
    }
    
    protected void handleKeyRelease(KeyEvent e) {
        keysPressed.remove(e.getCode());
    }

    /**
     * Initialise the number of barriers at the beginning of the game.
     */
    public void initialiseBarriers() {
        List<Barrier> barriers = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_BARRIERS; i++) {
            barriers.add(new Barrier(Color.GREEN));
        }
        this.barriers = barriers;
    }


    /**
     * Check for collisions between the player, aliens and lasers.
     */
    public void CollisionDetection() {
        Iterator<Laser> lIterator = lasers.iterator();

        while (lIterator.hasNext()) {
            Laser l = lIterator.next();
            Rectangle lRect = l.getLaser();

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
            } else {  
                Rectangle playerRect = player.getRect();

                if (lRect.intersects(playerRect.getBoundsInLocal())) {
                    player.decrementLives();
                    lIterator.remove();

                    
                    if (player.getLives() <= 0) {
                        System.out.println("Game Over!");
                        
                    }
                }
            }
        }
    }

    /**
     * Update the lasers in the game.
     */
    protected void updateLasers() {
        List<Laser> lasers = getLasers();
        ArrayList <Laser> newLasers = new ArrayList<>();
        for (Laser l: lasers){
            l.update();
            if (!(l.get_outofscreen())){
                newLasers.add(l);
            }
        }
        lasers = newLasers;
    }

    protected Set<KeyCode> getKeysPressed() { return (Set<KeyCode>) keysPressed; }
    
    protected Player getPlayer() { return player; }

    protected AlienSwarm getAlienSwarm() { return alienSwarm; }

    protected List<Laser> getLasers() { return lasers; }

    protected List<Barrier> getBarriers() { return barriers; }
}