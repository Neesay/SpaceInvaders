import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * A trapezium-shaped barrier that absorbs laser shots.
 *
 * @author Yusuf Rahman
 * @version 1.0
 */
public class Barrier {
  private double x, y, widthTop, widthBottom, height;
  private Color color;
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
  public void place(double x, double y, double widthTop, double widthBottom, double height) {
    this.x = x;
    this.y = y;
    this.widthTop = widthTop;
    this.widthBottom = widthBottom;
    this.height = height;
  }

  /**
   * Render the barrier on the given canvas.
   */
  public void render(GraphicsContext gc) {
      double[] xPoints = {x, x + widthTop, x + widthBottom, x - (widthBottom - widthTop)};
      double[] yPoints = {y, y, y + height, y + height};
      
      gc.setFill(color);
      gc.fillPolygon(xPoints, yPoints, 4);
  }
}
