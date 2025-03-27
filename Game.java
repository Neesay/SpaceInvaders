import java.util.List;
import java.util.ArrayList;
import javafx.scene.paint.Color;

/**
 * A Space Invaders simulator that renders the game on GameDisplay class.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Game {

    private static final int NUMBER_OF_BARRIERS = 3;
    private List<Barrier> barriers;

    /**
     * Constructor initialises the begining game state.
     */
    public Game() {
        initialiseBarriers(NUMBER_OF_BARRIERS);
    }

    /**
     * Initialise the number of barriers at the beginning of the game.
     */
    public void initialiseBarriers(int numberOfBarriers) {
        List<Barrier> barriers = new ArrayList<>();
        for (int i = 0; i < numberOfBarriers; i++) {
            barriers.add(new Barrier(Color.GREEN));
        }
        this.barriers = barriers;
    }

    public List<Barrier> getBarriers() { return barriers; }
}