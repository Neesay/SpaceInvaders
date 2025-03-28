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
 * All the game objects will be drawn on the canvas.
 *
 * @author Aditya Ranjan, Yusuf Rahman
 * @version 1.0
 */
public class GameDisplay {

    private final Canvas canvas;
    private final int WIDTH;
    private final Window WINDOW;
    private final GraphicsContext gc;
    private Game game;
    private Font gameFont;

    /**
     * Constructor that creates a canvas and initializes the game.
     * Sets up the key event handlers for movement and laser shooting.
     * Creates an animation timer to update the game state and redraw the scene.
     * 
     * @param width The width of the canvas
     * @param height The height of the canvas
     */
    public GameDisplay(int width, int height, Window window) {
        canvas = new Canvas(width, height);
        canvas.setFocusTraversable(true);
        gc = canvas.getGraphicsContext2D();

        this.WINDOW = window;
        this.WIDTH = width;

        try{
            gameFont = Font.loadFont(("file:./fonts/Pixels.ttf"), 60);
        }
        catch (Exception e){
            gameFont = Font.font("Arial",460);
        }

        canvas.setOnKeyPressed(e -> game.handleKeyPress(e));
        canvas.setOnKeyReleased(e -> game.handleKeyRelease(e));

        // Draw the sprites and start the game loop to update the game state.
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
     * Set the background and draw the score and lives.
     * Draw the game objects/sprites on the canvas.
     *
     */
    private void drawScene() {
        // Background
        gc.clearRect(0,0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        if (isGameNotNull()) {
            displayScoreAndLives();
            displayPlayer();
            displayAliens();
            displayLasers();
            displayBarriers();
        }
    }

    /**
     * Draws the score and lives on the canvas.
     */
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
     * Draw the given sprite on the canvas.
     * 
     * @param sprite can be Player, Alien, Barrier
     */ 
    private void drawSprite(Sprite sprite) {
        gc.drawImage(sprite.getImgView().getImage(), sprite.getX(), sprite.getY());
    } 

    private void displayPlayer() {
        Player player = game.getPlayer();   
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
            laser.getLaser().getY(), 
            laser.getLaser().getWidth(), 
            laser.getLaser().getHeight());
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
    private void update(long now) {
        Player player = game.getPlayer();
        AlienSwarm alienSwarm = game.getAlienSwarm();
        List<Laser> lasers = game.getLasers();
        Set<KeyCode> keysPressed = game.getKeysPressed();

        // Handle player movement
        boolean withinBoundary = player.getX() + player.getWidth() < canvas.getWidth();

        if (keysPressed.contains(KeyCode.LEFT) && player.getX() > 40){
            player.setX(player.getX() - player.getSpeed());
        }
        else if (keysPressed.contains(KeyCode.RIGHT) && withinBoundary && player.getX() < 1100){
            player.setX(player.getX() + player.getSpeed());            
        }
        
        player.update();
        game.collisionDetection();
        game.updateLasers();
        alienSwarm.update(lasers, now);
        drawScene();
    }    

    public Canvas getCanvas() { return canvas; }

    public void startGame() {
        game = new Game(WIDTH, WINDOW);
    }

    public void stopGame() {
        game = null;
    }

    public boolean isGameNotNull() {
        return !(this.game == null);
    }
}