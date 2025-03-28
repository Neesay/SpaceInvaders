import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.input.*;
import javafx.scene.text.Font;
import javafx.animation.AnimationTimer;

import javafx.scene.image.Image;
import java.util.List;
import java.util.Set;

/**
 * The game display and graphics will be handled here and rendered to the window class.
 * All the game objects will be drawn on the canvas.
 *
 * @author Yaseen Alam, Aditya Ranjan, Kasim Morsel, Yusuf Rahman
 * @version 2.1
 */

public class GameDisplay {

    private final Canvas canvas;
    private final int WIDTH;
    private final Window WINDOW;
    private final GraphicsContext gc;
    private Game game;
    private Font gameFont;
    private int bestScore;

    /**
     * Constructor that creates a canvas and initializes the game.
     * Sets up the key event handlers for movement and laser shooting.
     * Creates an animation timer to update the game state and redraw the scene.
     *
     * @param width The width of the canvas
     * @param height The height of the canvas
     */
    public GameDisplay(int width, int height, Window window, int bestScore) {
        canvas = new Canvas(width, height);
        canvas.setFocusTraversable(true);
        gc = canvas.getGraphicsContext2D();
        this.bestScore = bestScore;
        this.game = new Game(window);
        this.WINDOW = window;
        this.WIDTH = width;

        try{
            gameFont = Font.loadFont(("file:./fonts/Pixels.ttf"), 60);
        }
        catch (Exception e){
            gameFont = Font.font("Arial",460);
        }

        canvas.setOnKeyPressed(e -> {
            if (game.isGameNotNull()) {
                game.handleKeyPress(e);
            }
        });

        canvas.setOnKeyReleased(e -> {
            if (game.isGameNotNull()) {
                game.handleKeyRelease(e);
            }
        });

        // Draw the sprites and start the game loop to update the game state.
        drawScene();

        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update(now);
                if (game.isGameNotNull() && game.isRoundOver()) {
                    game.nextRound();
                }
            }
        };
        gameLoop.start();
    }

    /**
     * Set the background and draw the score and lives.
     * Draw the game objects/sprites on the canvas.
     */
    private void drawScene() {
        // Background
        gc.clearRect(0,0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        if (game.isGameNotNull()) {
            displayScoreAndLives();
            displayPlayer();
            displayBarriers();
            displayAliens();
            displayLasers();
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
        gc.fillText("High Score: ", 400, 30);
        gc.setFill(Color.CYAN);
        gc.fillText(String.valueOf(bestScore), 600, 30);


        gc.setFill(Color.WHITE);
        gc.fillText("Lives: ", 740,30);

        for (int i = 0; i < player.getLives(); i++){
            Image lifeImage = new Image("file:images/player.png");
            gc.drawImage(lifeImage, 830 + 70 * i, 10);
        }
    }

    /**
     * Draw the sprite on the canvas.
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

    /**
     * Displays all the aliens
     */
    
    private void displayAliens() {
        AlienSwarm alienSwarm = game.getAlienSwarm();
        for (Alien alien : alienSwarm.getAliens()) {
            drawSprite(alien);
        }
    }

    /**
     * Displays the lasers
     */
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

    /**
     * Displays barriers.
     */
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
        if (game.isGameNotNull() && game.isItGameOver()) {
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
    }

    /**
     * Returns canvas.
     */
    
    public Canvas getCanvas() { return canvas; }

    /**
     * Starts the game.
     */
    
    public void startGame() {
        game = new Game(WINDOW);
        game.startGame(WIDTH);
    }

    /**
     * Stops the game.
     */
    
    public void stopGame() {
        game.setGameOver();
    }
    
    /**
     * Gets current score
     * @param game.getPlayer().getScore().
     */
    
    public int getCurrentScore() {
        return game.getPlayer().getScore();
    }
    
    /**
     * Sets best score.
     * @param score.
     */
    
    public void setBestScore(int score) {
        this.bestScore = score;
    }

}