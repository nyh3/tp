package gc;


import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;

public class YX extends JPanel {


    protected void paintXY(Graphics g) {
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
        g.drawString("y = -x", 20, 20);
    }



    protected void paintYlogX(Graphics g) {
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
            JFrame frame = new JFrame("Graph Drawing Example with Axes");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new YX());
            frame.setPreferredSize(new Dimension(400, 400));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

}
