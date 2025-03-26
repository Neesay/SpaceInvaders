import java.util.*;

public class AlienSwarm {
    private List<Alien> Aliens;
    private List<List<Alien>> AliensPerCol;

    public void AlienSwarm() {
        spawnAliens();
    }

    public void update() {

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

    public List<Alien> getAliens() {
        return Aliens;
    }
}
