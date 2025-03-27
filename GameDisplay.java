import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.input.*;
import javafx.scene.text.Font;
import javafx.animation.AnimationTimer;

import java.util.List;
import java.util.Set;

/**
 * The game display and graphics will be handled here and rendered to the window class.
 *
 * @author 
 * @version (a version number or a date)
 */
public class GameDisplay {

    private final Canvas canvas;
    private final int WIDTH;
    private final Window WINDOW;
    private final GraphicsContext gc;
<<<<<<< Updated upstream
    private final Game game;
=======
    private Game game;
>>>>>>> Stashed changes
    private Font gameFont;

    /**
     * Constructor for objects of class GameDisplay
     */
    public GameDisplay(int width, int height, Window window) {
        canvas = new Canvas(width, height);
        canvas.setFocusTraversable(true);
        gc = canvas.getGraphicsContext2D();

<<<<<<< Updated upstream
        game = new Game(width);
=======
        WINDOW = window;
        WIDTH = width;
>>>>>>> Stashed changes

        try{
            gameFont = Font.loadFont(("file:./fonts/Pixels.ttf"), 60);
        }
        catch (Exception e){
            gameFont = Font.font("Arial",460);
        }

        canvas.setOnKeyPressed(e -> game.handleKeyPress(e));
        canvas.setOnKeyReleased(e -> game.handleKeyRelease(e));

        drawScene();

        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (isGameNotNull()) {
                    update(now);
                }

            }
        };
        gameLoop.start();
    }

    /**
     * Draw the scene.
     *
     */
    private void drawScene() {
        // Background
        gc.clearRect(0,0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
<<<<<<< Updated upstream
        
        displayScoreAndLives();
        
        displayPlayer();
        displayAliens();
        displayLasers();
        displayBarriers();
=======

        if (isGameNotNull()) {
            displayScoreAndLives();
            displayPlayer();
            displayAliens();
            displayLasers();
            displayBarriers();
        }
>>>>>>> Stashed changes
    }

    private void displayScoreAndLives() {
        Player player = game.getPlayer();

        gc.setFill(Color.WHITE);
        gc.setFont(gameFont);
        gc.fillText("Score: ", 40, 30);
        gc.setFill(Color.CYAN);
        gc.fillText(String.valueOf(player.getScore()), 130, 30);
        
        gc.setFill(Color.WHITE);
        gc.fillText("Lives: ", 740,30);
        
        for (int i = 0; i < player.getLives(); i++){
            gc.drawImage(player.getImgView().getImage(), 830 + 70*i, 10);    
        }
        
    }

    /**
     * Draw the sprite on the canvas.
     * @param sprite can be Player, Alien, Barrier
     */ 
    private void drawSprite(Sprite sprite) {
        gc.drawImage(sprite.getImgView().getImage(), sprite.getX(), sprite.getY());
    } 

    private void displayPlayer() {
<<<<<<< Updated upstream
        Player player = game.getPlayer();
=======
        Player player = game.getPlayer();   
>>>>>>> Stashed changes
        drawSprite(player);
    }

    private void displayAliens() {
        AlienSwarm alienSwarm = game.getAlienSwarm();
        for (Alien alien : alienSwarm.getAliens()) {
            drawSprite(alien);
        }
    }

    private void displayLasers() {
        List<Laser> lasers = game.getLasers();
        for (Laser laser: lasers){
            gc.setFill(Color.RED);  
            gc.fillRect(laser.getLaser().getX(), 
<<<<<<< Updated upstream
                        laser.getLaser().getY(), 
                        laser.getLaser().getWidth(), 
                        laser.getLaser().getHeight());
=======
            laser.getLaser().getY(), 
            laser.getLaser().getWidth(), 
            laser.getLaser().getHeight());
>>>>>>> Stashed changes
        }
    }
    
    private void displayBarriers() {
        List<Barrier> barriers = game.getBarriers();
        for (Barrier barrier : barriers) {
            drawSprite(barrier);
        }
    }

    /**
     * Update the game state for a frame.
     * Checks for movement and collisions
     *
     * @param now The current time
     */
    public void update(long now) {
        Player player = game.getPlayer();
        AlienSwarm alienSwarm = game.getAlienSwarm();
        List<Laser> lasers = game.getLasers();
        Set<KeyCode> keysPressed = game.getKeysPressed();

        boolean withinBoundary = player.getX() + player.getWidth() < canvas.getWidth();

        if (keysPressed.contains(KeyCode.LEFT) && player.getX() > 40){
            player.setX(player.getX() - player.getSpeed());
        }
        else if (keysPressed.contains(KeyCode.RIGHT) && withinBoundary && player.getX() < 1100){
            player.setX(player.getX() + player.getSpeed());            
        }
        
        player.update();
        game.CollisionDetection();
        game.updateLasers();
        alienSwarm.update(lasers, now);
        drawScene();
    }    

<<<<<<< Updated upstream
    public Canvas getCanvas() { return canvas; }   
=======
    public Canvas getCanvas() { return canvas; }

    public void startGame() {
        game = new Game(WIDTH, WINDOW);
    }

    public void stopGame() {
        game = null;
    }

    public void restartGame() {
        game = new Game(WIDTH, WINDOW);
    }

    public boolean isGameNotNull() {
        return !(this.game == null);
    }
>>>>>>> Stashed changes
}