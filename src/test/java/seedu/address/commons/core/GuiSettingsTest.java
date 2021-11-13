package seedu.address.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Point;

import org.junit.jupiter.api.Test;

public class GuiSettingsTest {
    private double windowWidth = 1000;
    private double windowHeight = 800;
    private int xPosition = 200;
    private int yPosition = 200;
    private Point windowCoordinates = new Point(xPosition, yPosition);

    @Test
    public void getFields() {
        GuiSettings guiSettings = new GuiSettings(windowWidth, windowHeight, xPosition, yPosition);

        assertEquals(windowWidth, guiSettings.getWindowWidth());
        assertEquals(windowHeight, guiSettings.getWindowHeight());
        assertEquals(windowCoordinates, guiSettings.getWindowCoordinates());
    }

    @Test
    public void equals() {
        GuiSettings defaultSettings = new GuiSettings();
        GuiSettings guiSettings = new GuiSettings(windowWidth, windowHeight, xPosition, yPosition);

        // same object
        assertTrue(defaultSettings.equals(defaultSettings));

        // different class and null
        assertFalse(defaultSettings.equals(null));
        assertFalse(defaultSettings.equals("guiSettings"));

        // different value
        assertFalse(defaultSettings.equals(guiSettings));

        // same value
        assertTrue(defaultSettings.equals(new GuiSettings()));
    }
}
