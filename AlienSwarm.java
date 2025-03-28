import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.*;

public class AlienSwarm {
    private List<Alien> Aliens;
    private final List<Alien> LargeAliens;
    private boolean moveDownNext = false;
    private long lastShotTime = 0;
    private final Random rand = new Random();
    private boolean spec = false;
    private long lastFrameSwitch = 0;
    private static final long FRAME_INTERVAL = 350000000;

    public AlienSwarm() {
        Aliens = new ArrayList<>();
        LargeAliens = new ArrayList<>();
        spawnAliens();

        AnimationTimer alienAnimation = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (now - lastFrameSwitch >= FRAME_INTERVAL) {
                    switchAllAlienFrames();
                    lastFrameSwitch = now;
                }
            }
        };
        alienAnimation.start();
    }

    public void update(List<Laser> lasers, long now) {
        alienPositionConstraint();
        spawnSpecialAlien();
        shootUpdate(lasers, now);
    }

    public void shootUpdate(List<Laser> lasers, long now) {
        long shoot_interval = 1000000000;
        ArrayList <Alien> newAliens = new ArrayList<>();

        for (Alien a: Aliens){
            a.move();
            
            if (now - lastShotTime >= shoot_interval){
                aliensShoot(lasers);
                lastShotTime = now;
            }
            
            if (!(a.getDead())){
                newAliens.add(a);
            }
            else{
                if (a instanceof SpecialAlien){
                    spec = false;
                }
            }
        }
        
        Aliens = newAliens;
    }

    public void spawnAliens(){
        int startX = 100;
        int startY = 150;
        int alienWidth = 70;
        int alienHeight = 60;
        int spacing = 20;

        for (int row = 0; row < 1; row++) {
            for (int col = 0; col < 8; col++) {
                int x = startX + col * (alienWidth + spacing);
                int y = startY;
                String[] alienFrames = {"file:./images/LargeAlien1.png", "file:./images/LargeAlien2.png"};
                Alien alien = new LargeAlien(x, y, alienFrames, alienHeight, alienWidth);
                alien.setRow(row);
                alien.setColumn(col);
                LargeAliens.add(alien);
                Aliens.add(alien);
            }
        }

        for (int row = 1; row < 3; row++) {
            for (int col = 0; col < 8; col++) {
                int x = startX + col * (alienWidth + spacing);
                int y = startY + row * (alienHeight + spacing);
                String[] alienFrames = {"file:./images/MediumAlien1.png", "file:./images/MediumAlien2.png"};
                Alien alien = new MediumAlien(x, y, alienFrames, alienHeight, alienWidth);
                alien.setRow(row);
                alien.setColumn(col);
                Aliens.add(alien);
            }
        }

        for (int row = 3; row < 5; row++) {
            for (int col = 0; col < 8; col++) {
                int x = startX + col * (alienWidth + spacing);
                int y = startY + row * (alienHeight + spacing);
                String[] alienFrames = {"file:./images/SmallAlien1.png", "file:./images/SmallAlien2.png"};
                Alien alien = new SmallAlien(x, y, alienFrames, alienHeight, alienWidth);
                alien.setRow(row);
                alien.setColumn(col);
                Aliens.add(alien);
            }
        }
    }
    
    private void spawnSpecialAlien(){
        if ((!(spec)) && rand.nextDouble() < 0.0005){
            String[] alienFrames = {"file:./images/SuperAlien.png", null};
            SpecialAlien alien = new SpecialAlien(1300, 50, alienFrames, 60, 80);
            Aliens.add(alien);
            spec = true;
        }
    }

    public List<Alien> getAliens() {
        return Aliens;
    }

    public void alienPositionConstraint() {
        for (Alien a : Aliens) {
            if ((a.getX() < 20 || a.getX() > 1100) && !(a instanceof SpecialAlien)) {
                moveDownNext = true;
                break;
            }
            
        }
        if (moveDownNext) {
            for (Alien a : Aliens) {
                if (!(a instanceof SpecialAlien)){
                    a.setY(a.getY() + 20);
                    a.setDirection(a.getDirection() * -1);    
                }
                
            }
            moveDownNext = false;
        }
    }

    private void aliensShoot(List<Laser> lasers){
        Alien a = LargeAliens.get(rand.nextInt(LargeAliens.size()));
        if (!a.getDead()) {
            Laser laser = new Laser(a.getX() + (double) a.getWidth() /2, a.getY() + 40, -9);
            laser.setPlayer();
            lasers.add(laser);
        }
    }

    private void switchAllAlienFrames() {
        for (Alien alien : this.getAliens()) {
            alien.switchFrames();
        }
    }
}
