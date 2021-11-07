package seedu.address.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Point;

import org.junit.jupiter.api.Test;

public class GuiSettingsTest {

    @Test
    public void toString_defaultObject_stringReturned() {
        GuiSettings defaultGuiSettings = new GuiSettings();
        assertNotNull(defaultGuiSettings);
        String expectedDefaultGuiSettings = "Width : 740.0\n"
                + "Height : 600.0\n"
                + "Position : null";
        assertEquals(expectedDefaultGuiSettings, defaultGuiSettings.toString());
    }

    @Test
    public void toString_parameterizedObject_stringReturned() {
        String expectedDefaultGuiSettings = "Width : 888.0\n"
                + "Height : 500.0\n"
                + "Position : java.awt.Point[x=0,y=1]";
        Point nonDefaultPoint = new Point(0, 1);
        GuiSettings nonDefaultGuiSettings = new GuiSettings(888.0, 500.0,
                nonDefaultPoint.x, nonDefaultPoint.y);
        System.out.println(nonDefaultGuiSettings.toString());
        assertEquals(expectedDefaultGuiSettings, nonDefaultGuiSettings.toString());
    }

    @Test
    public void getWindowWidth_defaultObject_success() {
        GuiSettings defaultGuiSettings = new GuiSettings();
        assertNotNull(defaultGuiSettings);
        double expectedWindowWidth = 740.0;
        assertEquals(expectedWindowWidth, defaultGuiSettings.getWindowWidth());
    }

    @Test
    public void getWindowWidth_nonDefaultObject_success() {
        Point nonDefaultPoint = new Point(0, 1);
        GuiSettings nonDefaultGuiSettings = new GuiSettings(888.0, 500.0,
                nonDefaultPoint.x, nonDefaultPoint.y);
        assertNotNull(nonDefaultGuiSettings);
        double expectedWindowWidth = 888.0;
        assertEquals(expectedWindowWidth, nonDefaultGuiSettings.getWindowWidth());
    }

    @Test
    public void getWindowHeight_defaultObject_success() {
        GuiSettings defaultGuiSettings = new GuiSettings();
        assertNotNull(defaultGuiSettings);
        double expectedWindowHeight = 600.0;
        assertEquals(expectedWindowHeight, defaultGuiSettings.getWindowHeight());
    }

    @Test
    public void getWindowHeight_nonDefaultObject_success() {
        Point nonDefaultPoint = new Point(0, 1);
        GuiSettings nonDefaultGuiSettings = new GuiSettings(888.0, 500.0,
                nonDefaultPoint.x, nonDefaultPoint.y);
        assertNotNull(nonDefaultGuiSettings);
        double expectedWindowHeight = 500.0;
        assertEquals(expectedWindowHeight, nonDefaultGuiSettings.getWindowHeight());
    }

    @Test
    public void getWindowCoordinates_defaultObject_success() {
        GuiSettings defaultGuiSettings = new GuiSettings();
        assertNotNull(defaultGuiSettings);
        Point expectedWindowCoordinates = null;
        assertEquals(expectedWindowCoordinates, defaultGuiSettings.getWindowCoordinates());
    }

    @Test
    public void getWindowCoordinates_nonDefaultObject_success() {
        Point nonDefaultPoint = new Point(0, 1);
        GuiSettings nonDefaultGuiSettings = new GuiSettings(888.0, 500.0,
                nonDefaultPoint.x, nonDefaultPoint.y);
        assertNotNull(nonDefaultGuiSettings);
        String expectedWindowCoordinates = "java.awt.Point[x=0,y=1]";
        assertEquals(expectedWindowCoordinates, nonDefaultGuiSettings.getWindowCoordinates().toString());
    }

    @Test
    public void equals_defaultObject_success() {
        GuiSettings defaultGuiSettings = new GuiSettings();
        assertNotNull(defaultGuiSettings);
        assertTrue(defaultGuiSettings.equals(defaultGuiSettings));
    }

    @Test
    public void equals_twoDefaultObject_success() {
        GuiSettings defaultGuiSettings = new GuiSettings();
        GuiSettings defaultGuiSettings2 = new GuiSettings();
        assertNotNull(defaultGuiSettings);
        assertNotNull(defaultGuiSettings2);
        assertTrue(defaultGuiSettings.equals(defaultGuiSettings2));
    }

    @Test
    public void equals_nonDefaultObject_success() {
        Point nonDefaultPoint = new Point(0, 1);
        GuiSettings nonDefaultGuiSettings = new GuiSettings(888.0, 500.0,
                nonDefaultPoint.x, nonDefaultPoint.y);
        assertNotNull(nonDefaultGuiSettings);
        assertTrue(nonDefaultGuiSettings.equals(nonDefaultGuiSettings));
    }

    @Test
    public void equals_twoDifferentObject_failure() {
        GuiSettings defaultGuiSettings = new GuiSettings();
        Point nonDefaultPoint = new Point(0, 1);
        GuiSettings nonDefaultGuiSettings = new GuiSettings(888.0, 500.0,
                nonDefaultPoint.x, nonDefaultPoint.y);
        assertNotNull(defaultGuiSettings);
        assertNotNull(nonDefaultGuiSettings);
        assertFalse(defaultGuiSettings.equals(nonDefaultGuiSettings));
    }

    @Test
    public void equalsMethod_twoDifferentInstanceObject() {
        Config defaultConfig = new Config();
        Point nonDefaultPoint = new Point(0, 1);
        GuiSettings nonDefaultGuiSettings = new GuiSettings(888.0, 500.0,
                nonDefaultPoint.x, nonDefaultPoint.y);
        assertNotNull(defaultConfig);
        assertNotNull(nonDefaultGuiSettings);
        assertFalse(nonDefaultGuiSettings.equals(defaultConfig));
    }

    @Test
    public void hashCode_twoDefaultObject_success() {
        GuiSettings defaultGuiSettings = new GuiSettings();
        GuiSettings defaultGuiSettings2 = new GuiSettings();
        assertNotNull(defaultGuiSettings);
        assertNotNull(defaultGuiSettings2);
        assertEquals(defaultGuiSettings.hashCode(), defaultGuiSettings2.hashCode());
    }

    @Test
    public void hashCode_twoDifferentObject_failure() {
        GuiSettings defaultGuiSettings = new GuiSettings();
        Point nonDefaultPoint = new Point(0, 1);
        GuiSettings nonDefaultGuiSettings = new GuiSettings(888.0, 500.0,
                nonDefaultPoint.x, nonDefaultPoint.y);
        assertNotNull(defaultGuiSettings);
        assertNotNull(nonDefaultGuiSettings);
        assertNotEquals(defaultGuiSettings.hashCode(), nonDefaultGuiSettings.hashCode());
    }

    @Test
    public void toString_twoDefaultObject_success() {
        GuiSettings defaultGuiSettings = new GuiSettings();
        GuiSettings defaultGuiSettings2 = new GuiSettings();
        assertNotNull(defaultGuiSettings);
        assertNotNull(defaultGuiSettings2);
        assertEquals(defaultGuiSettings.toString(), defaultGuiSettings2.toString());
    }

    @Test
    public void toString_twoDifferentObject_failure() {
        GuiSettings defaultGuiSettings = new GuiSettings();
        Point nonDefaultPoint = new Point(0, 1);
        GuiSettings nonDefaultGuiSettings = new GuiSettings(888.0, 500.0,
                nonDefaultPoint.x, nonDefaultPoint.y);
        assertNotNull(defaultGuiSettings);
        assertNotNull(nonDefaultGuiSettings);
        assertNotEquals(defaultGuiSettings.toString(), nonDefaultGuiSettings.toString());
    }
}
