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
 * @author Yusuf Rahman, Yaseen Alam, Aditya Ranjan, Kasim Morsel
 * @version 2.1
 */
public class Game {

    private final Set <KeyCode> keysPressed;
    private Player player;
    private AlienSwarm alienSwarm;
    private List <Laser> lasers;
    private List<Barrier> barriers;
    private static final int NUMBER_OF_BARRIERS = 3;
    private final Window window;
    private boolean GAME_OVER;
    private int canvasWidth;

    /**
     * A Space Invaders simulator that renders the game on GameDisplay class.
     */
    public Game(Window window) {
        this.window = window;
        this.keysPressed = new HashSet<>();
        this.player = null;
        this.alienSwarm = null;
        this.lasers = null;
        this.GAME_OVER = false;
    }

    public void startGame(int canvasWidth) {
        String[] playerFrames = {"file:./images/player.png", "file:./images/player_hit.png"};
        this.player = new Player(580.0,900.0, playerFrames, 30,60);
        this.alienSwarm = new AlienSwarm();
        this.lasers = new ArrayList<>();
        String[] barrierFrames = {
                "file:./images/barrier1.png",
                "file:./images/barrier8.png",
                "file:./images/barrier7.png",
                "file:./images/barrier6.png",
                "file:./images/barrier5.png",
                "file:./images/barrier4.png",
                "file:./images/barrier3.png",
                "file:./images/barrier2.png",
                "file:./images/barrier1.png"
        };
        this.barriers = createBarriers(canvasWidth, 700, barrierFrames, 85, 120);
        this.canvasWidth = canvasWidth;
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
    public void handleKeyRelease(KeyEvent e) {
        keysPressed.remove(e.getCode());
    }

    /**
     * Create barriers evenly across the game landscape.
     */
    private List<Barrier> createBarriers(int canvasWidth, int y, String[] pathFrames, int height, int width) {
        List<Barrier> barriers = new ArrayList<>();
        double spacing = (double) (canvasWidth - (NUMBER_OF_BARRIERS * width)) / (NUMBER_OF_BARRIERS + 1);

        for (int i = 0; i < NUMBER_OF_BARRIERS; i++) {
            double x = spacing + i * (spacing + width);
            Barrier barrier = new Barrier(x, y, pathFrames, height, width);
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
                    if (barrier.getDurability() > 0) {
                        barrier.switchFrames();
                    }
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
                        player.addScore(a.getPoints());

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
                        if (isItGameOver()) {
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
        
        // Alien collision with player and with barriers.
        
        for (Alien a:alienSwarm.getAliens()){
            Rectangle alienRect = a.getRect();
            if (alienRect.intersects(player.getRect().getBoundsInLocal())){
                setGameOver();
                player.switchToDieFrame();
                window.showGameOverScreen();
            }
            
            Iterator<Barrier> bIterator = barriers.iterator();
            while (bIterator.hasNext()){
                Rectangle barrierRect = bIterator.next().getRect();
                if (alienRect.intersects(barrierRect.getBoundsInLocal())){
                    bIterator.remove();
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

    public void nextRound() {
        this.alienSwarm = new AlienSwarm();
        this.lasers = new ArrayList<>();
        String[] barrierFrames = {
                "file:./images/barrier1.png",
                "file:./images/barrier8.png",
                "file:./images/barrier7.png",
                "file:./images/barrier6.png",
                "file:./images/barrier5.png",
                "file:./images/barrier4.png",
                "file:./images/barrier3.png",
                "file:./images/barrier2.png",
                "file:./images/barrier1.png"
        };
        this.barriers = createBarriers(canvasWidth, 700, barrierFrames, 85, 120);
        this.player.resetLives();
    }

    /**
     * Returns set of keys pressed.
     */
    
    public Set<KeyCode> getKeysPressed() { return keysPressed; }

    /**
     * Returns player
     */
    
    public Player getPlayer() { return player; }

    /**
     * Returns alien swarm.
     */
    
    public AlienSwarm getAlienSwarm() { return alienSwarm; }

    /**
     * Returns lasers.
     */
    
    public List<Laser> getLasers() { return lasers; }

    /**
     * Returns barriers.
     */
    
    
    public List<Barrier> getBarriers() { return barriers; }

    /**
     * Returns whether game is over or not.
     */
    
    public void setGameOver() {
        GAME_OVER = true;
    }

    /**
     * Update the lasers in the game.
     */
    
    public boolean isItGameOver() {
        return !GAME_OVER;
    }

    /**
     * Returns if game is null or not..
     */
    
    public boolean isGameNotNull() {
        return this.player != null || this.alienSwarm != null || this.lasers != null;
    }

    /**
     * Returns if round is over or not.
     */
    
    public boolean isRoundOver() {
        return alienSwarm.getAliens().isEmpty();
    }
}