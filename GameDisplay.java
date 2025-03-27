import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.input.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.animation.AnimationTimer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Iterator;
import java.util.Random;

/**
 * The game display and graphics will be handled here and rendered to the window class.
 *
 * @author 
 * @version (a version number or a date)
 */
public class GameDisplay {

    private final Canvas canvas;
    private final GraphicsContext gc;
    private final Game game;
    private Font gameFont;

    /**
     * Constructor for objects of class GameDisplay
     */
    public GameDisplay(int width, int height) {
        canvas = new Canvas(width, height);
        canvas.setFocusTraversable(true);
        gc = canvas.getGraphicsContext2D();

        game = new Game();

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
                update(now);
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
        
        displayScoreAndLives();
        
        displayPlayer();
        displayAliens();
        displayLasers();
    }

    private void displayPlayer() {
        Player player = game.getPlayer();
        gc.drawImage(player.getImgView().getImage(), player.getX(), player.getY());
    }

    private void displayAliens() {
        AlienSwarm alienSwarm = game.getAlienSwarm();
        for (Alien alien : alienSwarm.getAliens()) {
            gc.drawImage(alien.getImgView().getImage(), alien.getX(), alien.getY());
        }
    }

    private void displayLasers() {
        List<Laser> lasers = game.getLasers();
        for (Laser laser: lasers){
            gc.setFill(Color.RED);  
            gc.fillRect(laser.getLaser().getX(), laser.getLaser().getY(), laser.getLaser().getWidth(), laser.getLaser().getHeight());
        }
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
        
        for (int i=0;i<player.getLives();i++){
            gc.drawImage(player.getImgView().getImage(), 830 + 70*i, 10);    
        }
        
    }

    public void update(long now) {
        Player player = game.getPlayer();
        AlienSwarm alienSwarm = game.getAlienSwarm();
        List<Laser> lasers = game.getLasers();
        Set<KeyCode> keysPressed = game.getKeysPressed();

        if (keysPressed.contains(KeyCode.LEFT) && player.getX() > 40){
            player.setX(player.getX() - player.getSpeed());
        }
        else if (keysPressed.contains(KeyCode.RIGHT) && player.getX() + player.getWidth() < canvas.getWidth() && player.getX() < 1100){
            player.setX(player.getX() + player.getSpeed());            
        }
        
        player.update();
        game.CollisionDetection();
        game.updateLasers();
        alienSwarm.update(lasers, now);
        drawScene();
    }    

    public Canvas getCanvas() { 
        return canvas; 
    }   

}