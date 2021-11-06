package seedu.address.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

public class ConfigTest {

    @Test
    public void toString_defaultObject_stringReturned() {
        String defaultConfigAsString = "Current log level : INFO\n"
                + "Preference file Location : preferences.json";

        assertEquals(defaultConfigAsString, new Config().toString());
    }

    @Test
    public void getLogLevel_defaultObject() {
        Config defaultConfig = new Config();
        assertNotNull(defaultConfig);
        String expectedLogLevel = "INFO";
        assertEquals(expectedLogLevel, defaultConfig.getLogLevel().toString());
    }

    @Test
    public void getLogLevel_twoDefaultObject() {
        Config defaultConfig = new Config();
        Config defaultConfig2 = new Config();
        assertNotNull(defaultConfig);
        assertNotNull(defaultConfig2);
        assertEquals(defaultConfig.getLogLevel(), defaultConfig.getLogLevel());
    }

    @Test
    public void getLogLevel_twoDifferentObject() {
        Config defaultConfig = new Config();
        Config defaultConfig2 = new Config();
        assertNotNull(defaultConfig);
        assertNotNull(defaultConfig2);
        Path newPath = Paths.get("userprofile.json");
        defaultConfig.setUserPrefsFilePath(newPath);
        assertEquals(defaultConfig.getLogLevel(), defaultConfig.getLogLevel());
    }

    @Test
    public void setLogLevel_twoDefaultObject() {
        Config defaultConfig = new Config();
        Config defaultConfig2 = new Config();
        assertNotNull(defaultConfig);
        assertNotNull(defaultConfig2);
        defaultConfig.setLogLevel(defaultConfig2.getLogLevel());
        String expectedLogLevel = "INFO";
        System.out.println(expectedLogLevel + defaultConfig.getLogLevel().toString());
    }

    @Test
    public void getUserPrefsFilePath_defaultObject() {
        Config defaultConfig = new Config();
        assertNotNull(defaultConfig);
        String expectedUserPrefsFilePath = "preferences.json";
        assertEquals(expectedUserPrefsFilePath, defaultConfig.getUserPrefsFilePath().toString());
    }

    @Test
    public void setUserPrefsFilePath_twoDefaultObject() {
        Config defaultConfig = new Config();
        Config defaultConfig2 = new Config();
        assertNotNull(defaultConfig);
        assertNotNull(defaultConfig2);
        defaultConfig.setUserPrefsFilePath(defaultConfig2.getUserPrefsFilePath());
        String expectedUserPrefsFilePath = "preferences.json";
        assertEquals(expectedUserPrefsFilePath, defaultConfig.getUserPrefsFilePath().toString());
    }

    @Test
    public void setUserPrefsFilePath_twoDifferentObject() {
        Config defaultConfig = new Config();
        Config defaultConfig2 = new Config();
        assertNotNull(defaultConfig);
        assertNotNull(defaultConfig2);
        Path newPath = Paths.get("userprofile.json");
        defaultConfig.setUserPrefsFilePath(newPath);
        String expectedUserPrefsFilePath = "preferences.json";
        assertNotEquals(expectedUserPrefsFilePath, defaultConfig.getUserPrefsFilePath().toString());
    }

    @Test
    public void hashCode_defaultObject() {
        Config defaultConfig = new Config();
        assertNotNull(defaultConfig);
        assertNotNull(defaultConfig.hashCode());
    }

    @Test
    public void hashCode_twoDefaultObject() {
        Config defaultConfig = new Config();
        Config defaultConfig2 = new Config();
        assertNotNull(defaultConfig);
        assertNotNull(defaultConfig2);
        assertEquals(defaultConfig.hashCode(), defaultConfig2.hashCode());
    }

    @Test
    public void hashCode_twoDifferentObject() {
        Config defaultConfig = new Config();
        Config defaultConfig2 = new Config();
        assertNotNull(defaultConfig);
        assertNotNull(defaultConfig2);
        Path newPath = Paths.get("userprofile.json");
        defaultConfig.setUserPrefsFilePath(newPath);
        assertNotEquals(defaultConfig.hashCode(), defaultConfig2.hashCode());
    }

    @Test
    public void toString_twoDefaultObject() {
        Config defaultConfig = new Config();
        Config defaultConfig2 = new Config();
        assertNotNull(defaultConfig);
        assertNotNull(defaultConfig2);
        assertEquals(defaultConfig.toString(), defaultConfig2.toString());
    }

    @Test
    public void toString_twoDifferentObject() {
        Config defaultConfig = new Config();
        Config defaultConfig2 = new Config();
        assertNotNull(defaultConfig);
        assertNotNull(defaultConfig2);
        Path newPath = Paths.get("userprofile.json");
        defaultConfig.setUserPrefsFilePath(newPath);
        assertNotEquals(defaultConfig.toString(), defaultConfig2.toString());
    }

    @Test
    public void equalsMethod() {
        Config defaultConfig = new Config();
        assertNotNull(defaultConfig);
        assertTrue(defaultConfig.equals(defaultConfig));
    }

    @Test
    public void equalsMethod_twoDifferentObject() {
        Config defaultConfig = new Config();
        Config defaultConfig2 = new Config();
        assertNotNull(defaultConfig);
        assertNotNull(defaultConfig2);
        Path newPath = Paths.get("userprofile.json");
        defaultConfig.setUserPrefsFilePath(newPath);
        assertFalse(defaultConfig.equals(defaultConfig2));
    }

    @Test
    public void equalsMethod_twoDifferentInstanceObject() {
        Config defaultConfig = new Config();
        GuiSettings guiSettings = new GuiSettings();
        assertNotNull(defaultConfig);
        assertNotNull(guiSettings);
        assertFalse(defaultConfig.equals(guiSettings));
    }

}
