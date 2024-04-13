package gc;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;

public class YlogX extends JPanel {

    /**
     * Paints the component by drawing the mathematical graph of y = log(x) along with its axes.
     * This method is responsible for rendering the graph, axes, and labels on the component.
     *
     * Steps performed in this method:
     * 1. Clears the background by filling it with white color.
     * 2. Draws the X and Y axes in black.
     * 3. Labels the axes with 'X' and 'Y' at appropriate positions.
     * 4. Plots the graph of y = log(x), scaling the graph to fit within the view. The graph is
     *    drawn in blue from x = 0.01 to x = 10 to avoid undefined values at x = 0.
     * 5. Labels the graph with the equation 'y = log(x)' in red at the top left of the component.
     *
     * The axes are centered, and the graph is scaled using scaleFactorX and scaleFactorY
     * to properly fit within the component bounds. The Y-axis is inverted (negative scale factor)
     * because the graphical y-axis increases downwards.
     *
     * @param g the Graphics object to protect. This is provided by the Swing framework and represents
     *          the drawing area of this component.
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

        // Drawing the graph of Y = log(X)
        g.setColor(Color.BLUE);
        double scaleFactorX = 50.0; // Scale factor for x-axis to make graph visible
        double scaleFactorY = -50.0;
        // Scale factor for y-axis to make graph visible (negative because y-axis is inverted)
        int originX = getWidth() / 2;
        int originY = getHeight() / 2;
        int prevX = originX;
        int prevY = originY;

        for (double x = 0.01; x <= 10; x += 0.01) { // Start slightly above 0 to avoid taking log(0)
            int scaledX = originX + (int) (x * scaleFactorX);
            int scaledY = originY + (int) (Math.log(x) * scaleFactorY);
            g.drawLine(prevX, prevY, scaledX, scaledY);
            prevX = scaledX;
            prevY = scaledY;
        }

        // Labeling the graph with the formula
        g.setColor(Color.RED);
        g.drawString("y = log(x)", 20, 20);
    }

    public static void main() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Graph of Y = log(X)");
            frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            frame.getContentPane().add(new YlogX());
            frame.setPreferredSize(new Dimension(400, 400));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
