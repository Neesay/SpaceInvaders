import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.List;

/**
 * The game display and graphics will be handled here and rendered to the window class.
 *
 * @author Yusuf Rahman
 * @version (a version number or a date)
 */
public class GameDisplay {

    private final Canvas canvas;
    private final GraphicsContext gc;
    private Game game;

    /**
     * Constructor for objects of class GameDisplay
     */
    public GameDisplay(int width, int height) {
        canvas = new Canvas(width, height);
        gc = canvas.getGraphicsContext2D();
        game = new Game();
        drawInitialScene();
    }

    /**
     * Draw the starting scene of the game.
     *
     */
    private void drawInitialScene() {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Draw the barriers on the canvas
        drawBarriers();
    }

    /**
     * Draw the barriers evenly across the canvas.
     */
    private void drawBarriers() {
        List<Barrier> barriers = game.getBarriers();
        int barrierCount = barriers.size();
        double spacing = (canvas.getWidth() - (barrierCount * 100)) / (barrierCount + 1);
        double y = 400;

        for (int i=0; i<barrierCount; i++) {
            Barrier barrier = barriers.get(i);
            double x = spacing + i * (100 + spacing);
            barrier.place(x, y);
            barrier.render(gc);
        }
    }

    public Canvas getCanvas() { return canvas; }
}
