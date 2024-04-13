package gc;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;

public class YXsq extends JPanel {
    /**
     * Paints the component by drawing the graph of y = x^2 along with the coordinate axes.
     * This method handles all the graphics operations required to display the graph, axes, and labels
     * effectively on the component.
     *
     * Detailed steps in the method:
     * 1. Clears the background to white to ensure a clean canvas.
     * 2. Draws the X and Y axes in black, centrally positioned in the component.
     * 3. Labels the axes with 'X' at the near-end of the X-axis and 'Y' at the top of the Y-axis.
     * 4. Plots the quadratic graph of y = x^2 using a blue line. The graph spans for x values
     *    from -10 to 10. The X and Y values are scaled using predefined scale factors to ensure
     *    the graph fits well within the component boundaries.
     * 5. Labels the graph with the formula 'y = x^2' in red at the top left of the component,
     *    helping to identify the graph visually.
     *
     * The plotting uses a loop to calculate and connect points of the quadratic function. The graph is
     * drawn by iterating over a range of x values, calculating the corresponding y values, and drawing
     * lines between consecutive points.
     *
     * @param g the Graphics object provided by the Swing framework, used for all drawing operations
     *          within this component.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Drawing the background
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Setting up the axes
        g.setColor(Color.BLACK);
        g.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight()); // Y-axis
        g.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2); // X-axis

        // Labeling Axes
        g.drawString("Y", getWidth() / 2 + 5, 15);
        g.drawString("X", getWidth() - 15, getHeight() / 2 - 5);

        // Drawing the graph of Y = X^2
        g.setColor(Color.BLUE);
        double scaleFactorX = 50.0; // Scale factor for x-axis to make graph visible
        double scaleFactorY = -50.0;
        // Scale factor for y-axis to make graph visible (negative because y-axis is inverted)
        int originX = getWidth() / 2;
        int originY = getHeight() / 2;
        int prevX = originX;
        int prevY = originY;

        for (double x = -10; x <= 10; x += 0.01) {
            int scaledX = originX + (int) (x * scaleFactorX);
            int scaledY = originY + (int) (Math.pow(x, 2) * scaleFactorY);

            if (x == -10) { // Initialize prevX and prevY to the start of the curve
                prevX = scaledX;
                prevY = scaledY;
            }

            g.drawLine(prevX, prevY, scaledX, scaledY);
            prevX = scaledX;
            prevY = scaledY;
        }


        // Labeling the graph with the formula
        g.setColor(Color.RED);
        g.drawString("y = x^2", 20, 20);
    }

    public static void main() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Graph of Y = X^2");
            frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            frame.getContentPane().add(new YXsq());
            frame.setPreferredSize(new Dimension(400, 400));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
