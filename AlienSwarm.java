import java.util.*;

public class AlienSwarm {
    private List<Alien> Aliens;
    private boolean move_down_next = false;
    private long last_shot_time = 0;
    private final Random rand = new Random();
    private boolean spec = false;
    private SpecialAlien specAlien;


    public AlienSwarm() {
        Aliens = new ArrayList<>();
        spawnAliens();
    }

    public void update(List<Laser> lasers, long now) {
        alien_pos_checker();
        alien_move_down();
        spawnSpecialAlien();
        shoot_update(lasers, now);
    }

    public void shoot_update(List<Laser> lasers, long now) {
        long shoot_interval = 1000000000;
        ArrayList <Alien> newAliens = new ArrayList<>();

        for (Alien a: Aliens){
            a.move();
            if (now - last_shot_time >= shoot_interval){
                aliensShoot(lasers);
                last_shot_time = now;
            }
            if (!(a.getDead())){
                newAliens.add(a);
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
                Alien alien = new LargeAlien(x, y, "file:./images/LargeAlien1.png", alienHeight, alienWidth);
                alien.setRow(row);
                alien.setColumn(col);
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
                Aliens.add(alien);
            }
        }
    }
    
    private void spawnSpecialAlien(){
        if ((!(spec)) && rand.nextDouble() < 0.05){
            SpecialAlien alien = new SpecialAlien(1200, 50, "file:./images/SuperAlien.png", 60, 80);
            Aliens.add(alien);
            spec = true;
        }
    }

    public List<Alien> getAliens() {
        return Aliens;
    }

    public void alien_pos_checker() {
        for (Alien a : Aliens) {
            if ((a.getX() < 20 || a.getX() > 1100) && !(a instanceof SpecialAlien)) {
                move_down_next = true;
                break;
            }
        }

        if (move_down_next) {
            for (Alien a : Aliens) {
                a.setY(a.getY() + 20);
                a.setDirection(a.getDirection() * -1);
            }
            move_down_next = false;
        }
    }

    public void alien_move_down(){
        if (move_down_next){
            for (Alien a:Aliens){
                a.setY(a.getY() + 2.0);
            }
            move_down_next = false;
        }
    }

    private void aliensShoot(List<Laser> lasers){
        Alien a = Aliens.get(rand.nextInt(Aliens.size()));
        Laser laser = new Laser(a.getX() + (double) a.getWidth() /2, a.getY(), -9);
        laser.setPlayer();
        lasers.add(laser);
    }
}
