import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * A trapezium-shaped barrier that absorbs laser shots.
 *
 * @author Yusuf Rahman
 * @version 1.0
 */
public class Barrier {
  private Color color;
  private final double WIDTH_TOP = 70;
  private final double WIDTH_BOTTOM = 110;
  private final double HEIGHT = 60;
  private double x, y;
  private int durability = 3;
  
  /**
   * Create a barrier with the given color.
   */
  public Barrier(Color color) {
    this.color = color;
  }
  
  /**
   * Place the barrier at the given position with the given dimensions.
   */
  public void place(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Render the barrier on the given canvas.
   */
  public void render(GraphicsContext gc) {
      double[] xPoints = {x, x + WIDTH_TOP, x + WIDTH_BOTTOM, x - (WIDTH_BOTTOM - WIDTH_TOP)};
      double[] yPoints = {y, y, y + HEIGHT, y + HEIGHT};
      
      gc.setFill(color);
      gc.fillPolygon(xPoints, yPoints, 4);
  }
}
