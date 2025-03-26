import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.animation.AnimationTimer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

/**
 * The game display and graphics will be handled here and rendered to the window class.
 *
 * @author 
 * @version (a version number or a date)
 */
public class GameDisplay {

    private final Canvas canvas;
    private final GraphicsContext gc;
    private final long shoot_interval = 1000000000;
    private Set <KeyCode> keysPressed = new HashSet<>();
    private Font gameFont;
    
    private Player player;
    private List <Laser> Lasers = new ArrayList<>();
    private List <Alien> Aliens = new ArrayList<>();
    private List <List<Alien>> AliensPerCol = new ArrayList<>();
    private AnimationTimer gameLoop;
    private Random random = new Random();
    private long last_shot_time = 0;

    /**
     * Constructor for objects of class GameDisplay
     */
    public GameDisplay(int width, int height) {
        
        for (int i = 0; i < 8; i++) {
            AliensPerCol.add(new ArrayList<>());
        }
        canvas = new Canvas(width, height);
        gc = canvas.getGraphicsContext2D();
        this.player = new Player(600.0,700.0, "file:./images/player.png", 30,60);
        spawnAliens();
        canvas.setFocusTraversable(true);
        
        try{
        gameFont = Font.loadFont(("file:./fonts/Pixels.ttf"), 60);
        }
        catch (Exception e){
            gameFont = Font.font("Arial",460);
        }

        canvas.setOnKeyPressed(e -> handleKeyPress(e));
        canvas.setOnKeyReleased(e -> handleKeyRelease(e));

        drawScene();
        
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update(now); 
            }
        };
        gameLoop.start();
        
    }

    /**
     * Draw the starting scene of the game.
     *
     */
    private void drawScene() {
        gc.clearRect(0,0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        
        displayScore();
        
        gc.drawImage(player.getImgView().getImage(), player.getX(), player.getY());
        
         for (Alien alien : Aliens) {
            gc.drawImage(alien.getImgView().getImage(), alien.getX(), alien.getY());
        }
        
        for (Laser laser: Lasers){
            gc.setFill(Color.RED);  
            gc.fillRect(laser.getLaser().getX(), laser.getLaser().getY(), laser.getLaser().getWidth(), laser.getLaser().getHeight());
        }
        
    }
    
    private void displayScore(){
        gc.setFill(Color.WHITE);
        gc.setFont(gameFont);
        gc.fillText("Score: ", 20, 30);
        gc.setFill(Color.CYAN);
        gc.fillText(String.valueOf(player.getScore()), 130, 30);
    }
    
    private void spawnAliens(){
        int startX = 100;
        int startY = 50;  
        int alienWidth = 70;
        int alienHeight = 60;
        int spacing = 20;

        for (int row = 0; row < 1; row++) {
            for (int col = 0; col < 8; col++) {
                int x = startX + col * (alienWidth + spacing);
                int y = startY;
                Alien alien = new LargeAlien(x, y, "file:./images/LargeAlien1.png", alienHeight, alienWidth);
                alien.setRow(row);
                alien.setColumn(col);
                AliensPerCol.get(col).add(alien);
                Aliens.add(alien);
            }
        }

        for (int row = 1; row < 3; row++) {
            for (int col = 0; col < 8; col++) {
                int x = startX + col * (alienWidth + spacing);
                int y = startY + row * (alienHeight + spacing);
                Alien alien = new MediumAlien(x, y, "file:./images/MediumAlien1.png", alienHeight, alienWidth);
                alien.setRow(row);
                alien.setColumn(col);
                AliensPerCol.get(col).add(alien);
                Aliens.add(alien);
            }
        }

        for (int row = 3; row < 5; row++) {
            for (int col = 0; col < 8; col++) {
                int x = startX + col * (alienWidth + spacing);
                int y = startY + row * (alienHeight + spacing);
                Alien alien = new SmallAlien(x, y, "file:./images/SmallAlien1.png", alienHeight, alienWidth);
                alien.setRow(row);
                alien.setColumn(col);
                AliensPerCol.get(col).add(alien);
                Aliens.add(alien);
            }
        }
    }
    
    public void CollisionDetection(){
        Iterator <Laser> lIterator = Lasers.iterator();        
        while (lIterator.hasNext()){
            Laser l = lIterator.next();
            Rectangle lRect = l.getLaser();
            // if laser from player, go over alien collisions
            if (l.getStatus()){
                for (Alien a: Aliens){
                    Rectangle AlienRect = a.getRect();
                    if (lRect.intersects(AlienRect.getBoundsInLocal())){
                        a.setDead(); 
                        int score = player.getScore();
                        player.setScore(a.getPoints());
                        lIterator.remove();
                    }
                }
            }
            // laser is from alien, go over player collision
            else{
                Rectangle playerRect = player.getRect();
                if (lRect.intersects(playerRect.getBoundsInLocal())){
                    player.decrementLives();
                    lIterator.remove();
                    // add game over stuff if lives < 0
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
            Laser laser = new Laser(player.getX() + (player.getWidth()/2), player.getY(), 9);
            Lasers.add(laser);
        }
    }
    
    private void aliensShoot(){
        Alien a = Aliens.get(random.nextInt(Aliens.size()));
        Laser laser = new Laser(a.getX() + a.getWidth()/2, a.getY(), -9);
        laser.setPlayer();
        Lasers.add(laser);
    }
    
    private void handleKeyRelease(KeyEvent e){
        keysPressed.remove(e.getCode());
    }
    
    public void update(long now) {
        if (keysPressed.contains(KeyCode.LEFT) && player.getX() > 0){
            player.setX(player.getX() - player.getSpeed());
        }
        else if (keysPressed.contains(KeyCode.RIGHT) && player.getX() + player.getWidth() < canvas.getWidth()){
            player.setX(player.getX() + player.getSpeed());            
        }
        
        player.update();
        CollisionDetection();
        ArrayList <Laser> newLasers = new ArrayList<>();
        
        for (Laser l: Lasers){
            l.update();
            if (!(l.get_outofscreen())){
                newLasers.add(l);
            }
        }
        
        Lasers = newLasers;
        
        ArrayList <Alien> newAliens = new ArrayList<>();
        for (Alien a: Aliens){
            a.update(AliensPerCol);
            if (now - last_shot_time >= shoot_interval){
                aliensShoot();
                last_shot_time = now;
            }
            
            if (!(a.getDead())){
                newAliens.add(a);
            }
        }


        Aliens = newAliens;
        
        drawScene();
        
    }
    
}