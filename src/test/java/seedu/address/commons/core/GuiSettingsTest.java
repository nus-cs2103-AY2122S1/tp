package seedu.address.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Point;

import org.junit.jupiter.api.Test;

public class GuiSettingsTest {
    private final GuiSettings defaultGuiSettings = new GuiSettings(1000.00, 600.00, 10, 20);

    @Test
    public void getWindowWidth_defaultGuiSettings_returnsWidth() {
        assertEquals(1000.00, defaultGuiSettings.getWindowWidth());
    }

    @Test
    public void getWindowHeight_defaultGuiSettings_returnsHeight() {
        assertEquals(600.00, defaultGuiSettings.getWindowHeight());
    }

    @Test
    public void getWindowCoordinates_defaultGuiSettings_returnsCoordinates() {
        assertEquals(new Point(10, 20), defaultGuiSettings.getWindowCoordinates());
    }

    @Test
    public void equals() {
        // same object -> return true
        assertTrue(defaultGuiSettings.equals(defaultGuiSettings));

        // null -> return false
        assertFalse(defaultGuiSettings.equals(null));

        // different width -> return false
        assertFalse(defaultGuiSettings.equals(new GuiSettings(500.00, 600.00, 10, 20)));

        // different height -> return false
        assertFalse(defaultGuiSettings.equals(new GuiSettings(1000.00, 300.00, 10, 20)));

        // different x-coordinate -> return false
        assertFalse(defaultGuiSettings.equals(new GuiSettings(1000.00, 600.00, 5, 20)));

        // different y-coordinate -> return false
        assertFalse(defaultGuiSettings.equals(new GuiSettings(1000.00, 600.00, 10, 10)));

        // same settings -> return true
        assertTrue(defaultGuiSettings.equals(new GuiSettings(1000.00, 600.00, 10, 20)));
    }

}
