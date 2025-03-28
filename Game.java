import javafx.animation.PauseTransition;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

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
    private static final int NUMBER_OF_BARRIERS = 3;
    private final Window window;
    private boolean GAME_OVER;

    /**
     * Constructor initialises the begining game state.
     */
    public Game(int canvasWidth, Window window) {
        this.window = window;
        keysPressed = new HashSet<>();
        String[] playerFrames = {"file:./images/player.png", "file:./images/player_hit.png"};
        player = new Player(580.0,900.0, playerFrames, 30,60);
        alienSwarm = new AlienSwarm();
        lasers = new ArrayList<>();
        barriers = createBarriers(canvasWidth);
        GAME_OVER = false;
    }

    /**
     * Allow player to move left and right and shoot lasers.
     * Handle keyboard input
     */
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
    
    /**
     * Stop player movement when key is released.
     */
    protected void handleKeyRelease(KeyEvent e) {
        keysPressed.remove(e.getCode());
    }

    /**
     * Create barriers evenly across the game landscape.
     */
    private List<Barrier> createBarriers(int canvasWidth) {
        List<Barrier> barriers = new ArrayList<>();
        double spacing = (double) (canvasWidth - (NUMBER_OF_BARRIERS * 100)) / (NUMBER_OF_BARRIERS + 1);
        double y = 700;

        for (int i = 0; i < NUMBER_OF_BARRIERS; i++) {
            double x = spacing + i * (spacing + 100);
            String[] barrierFrames = {"file:./images/barrier1.png", null};
            Barrier barrier = new Barrier(x, y, barrierFrames, 85, 125);
            barriers.add(barrier);
        }
        return barriers;
    }

    /**
     * Check for collisions between the player, aliens and lasers.
     */
    public void CollisionDetection() {
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
                    //help with this
                    player.switchToDieFrame();

                    // Pause before switching back to normal frame
                    PauseTransition pause = new PauseTransition(Duration.millis(200));
                    pause.setOnFinished(e -> {
                        if (!isItGameOver()) {
                            player.switchToAliveFrame();
                        }
                    });
                    pause.play();

                    if (player.getLives() <= 0) {
                        setGameOver();
                        player.switchToDieFrame();
                        window.showGameOverScreen();
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
    }

    protected Set<KeyCode> getKeysPressed() { return keysPressed; }
    
    protected Player getPlayer() { return player; }

    protected AlienSwarm getAlienSwarm() { return alienSwarm; }

    protected List<Laser> getLasers() { return lasers; }

    protected List<Barrier> getBarriers() { return barriers; }

    public void setGameOver() {
        GAME_OVER = true;
    }

    public boolean isItGameOver() {
        return GAME_OVER;
    }
}