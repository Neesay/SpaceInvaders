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
    private Game game;
    private final Set <KeyCode> keysPressed;
    private Font gameFont;
    private final Player player;
    private final AlienSwarm alienSwarm;
    private List <Laser> Lasers;

    /**
     * Constructor for objects of class GameDisplay
     */
    public GameDisplay(int width, int height) {
        canvas = new Canvas(width, height);
        keysPressed = new HashSet<>();
        Lasers = new ArrayList<>();


        canvas.setFocusTraversable(true);
        gc = canvas.getGraphicsContext2D();
        game = new Game();

        this.player = new Player(600.0,700.0, "file:./images/player.png", 30,60);

        alienSwarm = new AlienSwarm();
        
        try{
            gameFont = Font.loadFont(("file:./fonts/Pixels.ttf"), 60);
        }
        catch (Exception e){
            gameFont = Font.font("Arial",460);
        }

        canvas.setOnKeyPressed(this::handleKeyPress);
        canvas.setOnKeyReleased(this::handleKeyRelease);

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
        gc.clearRect(0,0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        
        displayScoreAndLives();
        
        gc.drawImage(player.getImgView().getImage(), player.getX(), player.getY());
        
        for (Alien alien : alienSwarm.getAliens()) {
            gc.drawImage(alien.getImgView().getImage(), alien.getX(), alien.getY());
        }
        
        for (Laser laser: Lasers){
            gc.setFill(Color.RED);  
            gc.fillRect(laser.getLaser().getX(), laser.getLaser().getY(), laser.getLaser().getWidth(), laser.getLaser().getHeight());
        }
        
    }
    
    private void displayScoreAndLives(){
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
    
    public void CollisionDetection(){
        Iterator<Laser> lIterator = Lasers.iterator();

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

    public Canvas getCanvas() { 
        return canvas; 
    }
    
    private void handleKeyPress(KeyEvent e){
        keysPressed.add(e.getCode());
        if (e.getCode() == KeyCode.SPACE && player.getReady()){
            player.setReady(false);
            Laser laser = new Laser(player.getX() + ((double) player.getWidth() /2), player.getY(), 9);
            Lasers.add(laser);
        }
    }
    
    private void handleKeyRelease(KeyEvent e){
        keysPressed.remove(e.getCode());
    }

    private void updateLasers() {
        ArrayList <Laser> newLasers = new ArrayList<>();
        for (Laser l: Lasers){
            l.update();
            if (!(l.get_outofscreen())){
                newLasers.add(l);
            }
        }
        Lasers = newLasers;
    }
    
    public void update(long now) {
        if (keysPressed.contains(KeyCode.LEFT) && player.getX() > 40){
            player.setX(player.getX() - player.getSpeed());
        }
        else if (keysPressed.contains(KeyCode.RIGHT) && player.getX() + player.getWidth() < canvas.getWidth() && player.getX() < 1100){
            player.setX(player.getX() + player.getSpeed());            
        }
        
        player.update();
        CollisionDetection();
        updateLasers();
        alienSwarm.update(Lasers, now);
        drawScene();
    }
}