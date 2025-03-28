import javafx.animation.AnimationTimer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * A group of aliens that can move and shoot lasers.
 *
 * @author Yaseen Alam, Aditya Ranjan
 * @version 1.3
 */
public class AlienSwarm {
    private List<Alien> aliens;
    private final List<Alien> largeAliens;
    private boolean moveDownNext = false;
    private long lastShotTime = 0;
    private final Random rand = new Random();
    private boolean spec = false;
    private long lastFrameSwitch = 0;
    private static final long FRAME_INTERVAL = 350000000;

    /**
     * Constructor for AlienSwarm which spawns a list of aliens.
     */
    public AlienSwarm() {
        aliens = new ArrayList<>();
        largeAliens = new ArrayList<>();
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

    /**
     * Update the position of the aliens and check for collisions with lasers.
     *
     * @param lasers List of lasers to check for collisions
     * @param now Current time
     */
    public void update(List<Laser> lasers, long now) {
        alienPositionConstraint();
        spawnSpecialAlien();
        shootUpdate(lasers, now);
    }

    /**
     * Triggers the aliens to shoot lasers.
     */
    public void shootUpdate(List<Laser> lasers, long now) {
        long shoot_interval = 1000000000;
        ArrayList <Alien> newAliens = new ArrayList<>();

        for (Alien a: aliens){
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

        aliens = newAliens;
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
                largeAliens.add(alien);
                aliens.add(alien);
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
                aliens.add(alien);
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
                aliens.add(alien);
            }
        }
    }

    private void spawnSpecialAlien(){
        if ((!(spec)) && rand.nextDouble() < 0.0005){
            String[] alienFrames = {"file:./images/SuperAlien.png", null};
            SpecialAlien alien = new SpecialAlien(1300, 50, alienFrames, 60, 80);
            aliens.add(alien);
            spec = true;
        }
    }

    public List<Alien> getAliens() {
        return aliens;
    }

    public void alienPositionConstraint() {
        for (Alien a : aliens) {
            if ((a.getX() < 20 || a.getX() > 1100) && !(a instanceof SpecialAlien)) {
                moveDownNext = true;
                break;
            }

        }
        if (moveDownNext) {
            for (Alien a : aliens) {
                if (!(a instanceof SpecialAlien)){
                    a.setY(a.getY() + 20);
                    a.setDirection(a.getDirection() * -1);
                }

            }
            moveDownNext = false;
        }
    }

    private void aliensShoot(List<Laser> lasers){
        Alien a = largeAliens.get(rand.nextInt(largeAliens.size()));
        if (!a.getDead()) {
            Laser laser = new Laser(a.getX() + (double) a.getWidth() /2, a.getY() + 40, -9);
            laser.setPlayer();
            lasers.add(laser);
        }
    }

    private void switchAllAlienFrames() {
        for (Alien alien : this.getAliens()) {
            if (!(alien instanceof SpecialAlien)){
                alien.switchFrames();    
            }
        }
    }
}
