package gc;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;

public class YlogX extends JPanel {

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
        double scaleFactorY = -50.0; // Scale factor for y-axis to make graph visible (negative because y-axis is inverted)
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
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new YX());
            frame.setPreferredSize(new Dimension(400, 400));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
