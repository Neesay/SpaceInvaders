import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The game display and graphics will be handled here and rendered to the window class.
 *
 * @author Yusuf Rahman
 * @version (a version number or a date)
 */
public class GameDisplay {

    private final Canvas canvas;
    private final GraphicsContext gc;
    

    /**
     * Constructor for objects of class GameDisplay
     */
    public GameDisplay(int width, int height) {
        canvas = new Canvas(width, height);
        gc = canvas.getGraphicsContext2D();
        drawInitialScene();
    }

    /**
     * Draw the starting scene of the game.
     *
     */
    private void drawInitialScene() {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // TESTING - Draw a single triangle in the center to see how rendering works
        gc.setFill(Color.BLUE);
        double[] xPoints = {400, 350, 450};
        double[] yPoints = {200, 350, 350};
        gc.fillPolygon(xPoints, yPoints, 3);
    }


    public Canvas getCanvas() { return canvas; }
}