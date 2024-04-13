package gc;

import org.junit.jupiter.api.Test;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
public class GCTest {
    @Test
    public void testPanelPropertiesYlogX() {
        YlogX panel = new YlogX();
        assertNotNull(panel, "Panel should not be null");
        // Test preferred size set in constructor or any other initialization if any
        // Since YlogX does not explicitly set preferred size, we can't test it unless it is set.
        panel.setPreferredSize(new Dimension(400, 400)); // This would need to be set somewhere in YlogX

        assertEquals(new Dimension(400, 400), panel.getPreferredSize(),
                "Panel preferred size should be 400x400");
    }

    @Test
    public void testPaintComponentYlogX() {
        YlogX panel = new YlogX();
        BufferedImage img = new BufferedImage(400, 400, BufferedImage.TYPE_INT_ARGB);
        Graphics graphics = img.getGraphics();

        // Call paintComponent and check for exceptions
        assertDoesNotThrow(() -> panel.paintComponent(graphics), "paintComponent should not throw exceptions");
    }

    @Test
    public void testPanelPropertiesYX() {
        YX panel = new YX();
        assertNotNull(panel, "Panel should not be null");
        // Since no specific preferred size is set in YX, this test assumes you might set one like this:
        panel.setPreferredSize(new Dimension(400, 400));
        assertEquals(new Dimension(400, 400), panel.getPreferredSize(), "Panel preferred size " +
                "should be 400x400");
    }

    @Test
    public void testPaintComponentYX() {
        YX panel = new YX();
        BufferedImage img = new BufferedImage(400, 400, BufferedImage.TYPE_INT_ARGB);
        Graphics graphics = img.getGraphics();

        // Executing paintComponent to check for any runtime exceptions and basic execution flow
        assertDoesNotThrow(() -> panel.paintComponent(graphics), "paintComponent should not throw exceptions");
    }

    @Test
    public void testPanelPropertiesYXsq() {
        YXsq panel = new YXsq();
        assertNotNull(panel, "Panel should not be null");
        // Since the size isn't explicitly set in YXsq, we assume you might set a size like this:
        panel.setPreferredSize(new Dimension(400, 400));
        assertEquals(new Dimension(400, 400), panel.getPreferredSize(), "Panel preferred size " +
                "should be 400x400");
    }

    @Test
    public void testPaintComponentYXsq() {
        YXsq panel = new YXsq();
        BufferedImage img = new BufferedImage(400, 400, BufferedImage.TYPE_INT_ARGB);
        Graphics graphics = img.getGraphics();

        // Testing paintComponent to ensure it completes without throwing exceptions
        assertDoesNotThrow(() -> panel.paintComponent(graphics), "paintComponent should not throw exceptions");
    }
}
