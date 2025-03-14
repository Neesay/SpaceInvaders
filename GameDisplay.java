import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * The game display and graphics will be handled here and rendered to the window class.
 *
 * @author Yusuf Rahman
 * @version (a version number or a date)
 */
public class GameDisplay {

    private final Canvas canvas;
    private final GraphicsContext gc;
    private AnimationTimer gameLoop;

    /**
     * Constructor for objects of class GameDisplay
     */
    public GameDisplay(int width, int height) {
        canvas = new Canvas(width, height);
        gc = canvas.getGraphicsContext2D();
        drawInitialScene();
    }

    /**
     * An example of a method - replace this comment with your own
     *
     */
    private void drawInitialScene() {
        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // TESTING - Draw a single triangle in the center
        gc.setFill(Color.BLUE);
        double[] xPoints = {400, 350, 450};
        double[] yPoints = {200, 350, 350};
        gc.fillPolygon(xPoints, yPoints, 3);
    }

    public void startAnimation() {
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
                render();
            }
        };
        gameLoop.start();
    }

    private void update() {
        // Game logic updates go here (e.g., move objects, check collisions)
    }

    private void render() {
        // Redraw game elements here (currently re-draws the triangle)
        drawInitialScene();
    }

    public Canvas getCanvas() {
        return canvas;
    }
}
