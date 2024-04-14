package gc;


import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;

public class YX extends JPanel {

    /**
     * Paints the component by drawing a line graph of the equation y = -x along with its axes.
     * This method is responsible for rendering the graph, axes, and labels on the component.
     *
     * Steps performed in this method:
     * 1. Clears the background by filling it with white color to ensure a clean drawing area.
     * 2. Draws the X and Y axes in black, placing them in the center of the component.
     * 3. Labels the axes with 'X' at the right end and 'Y' at the top, positioned near the axes.
     * 4. Plots the graph of y = -x as a blue solid line that spans across the visible area of the
     *    component. The line is drawn by connecting consecutive points from left to right.
     * 5. Labels the graph with the equation 'y = -x' in red at the top left of the component,
     *    making it easy to identify the graph being displayed.
     *
     * The method uses a loop to calculate and connect points along the graph based on the component's
     * width. This makes the graph adapt dynamically to changes in the component's size.
     *
     * @param g the Graphics object to protect. This is provided by the Swing framework and represents
     *          the drawing area of this component.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Drawing the graph background
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Setting up the axes
        g.setColor(Color.BLACK);
        // Y-axis
        g.drawLine(getWidth()/2, 0, getWidth()/2, getHeight());
        // X-axis
        g.drawLine(0, getHeight()/2, getWidth(), getHeight()/2);

        // Labeling Axes
        g.drawString("Y", getWidth()/2 + 5, 15);
        g.drawString("X", getWidth() - 15, getHeight()/2 - 5);

        // Drawing the graph (solid line example)
        g.setColor(Color.BLUE);
        int prevX = getWidth()/2;
        int prevY = getHeight()/2;
        for (int i = -getWidth()/2; i < getWidth()/2; i++) {
            int x = i + getWidth()/2; // Adjusting the origin
            int y = -i + getHeight()/2; // Example formula: y = -x (and adjusting the origin)
            g.drawLine(prevX, prevY, x, y);
            prevX = x;
            prevY = y;
        }

        // Labeling the graph with the formula
        g.setColor(Color.RED);
        g.drawString("y = x", 20, 20);
    }

    /**
     * Initializes and displays a JFrame showcasing a graphical representation of the function Y = X.
     *
     * <p>This method sets up the graphical user interface in the Event Dispatch Thread using
     * {@link SwingUtilities#invokeLater}.
     * It creates a JFrame titled "Graph of Y = X" and adds a custom component that
     * presumably plots the function Y = X.
     * The frame is set to close on operation HIDE_ON_CLOSE, meaning the window will be hidden but the
     * application will continue running when the window's close button is clicked.</p>
     *
     * <p>The content pane of the JFrame is set to include an instance of {@code YX}, which is a custom component
     * expected to handle the rendering of the specified mathematical function. The frame is sized to 400x400 pixels
     * and positioned to appear centered relative to the screen.</p>
     *
     * <p>This setup is ideal for demonstrating simple graphical applications or for educational purposes
     * to illustrate
     * the behavior of linear functions.</p>
     *
     * @see SwingUtilities#invokeLater(Runnable)  To understand the importance of invoking GUI updates on the EDT.
     * @see JFrame  For more details about JFrame functionalities and properties.
     */
    public static void main() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Graph of Y = X");
            frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            frame.getContentPane().add(new YX());
            frame.setPreferredSize(new Dimension(400, 400));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

}
