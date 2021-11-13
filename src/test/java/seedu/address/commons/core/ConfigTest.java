package seedu.address.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Paths;
import java.util.logging.Level;

import org.junit.jupiter.api.Test;

public class ConfigTest {

    @Test
    public void toString_defaultObject_stringReturned() {
        String defaultConfigAsString = "Current log level : INFO\n"
                + "Preference file Location : preferences.json";

        assertEquals(defaultConfigAsString, new Config().toString());
    }

    @Test
    public void getLogLevel_defaultObject_levelInfoReturned() {
        assertEquals(Level.INFO, new Config().getLogLevel());
    }

    @Test
    public void getPath_defaultObject_preferencesReturned() {
        assertEquals(Paths.get("preferences.json"), new Config().getUserPrefsFilePath());
    }

    @Test
    public void equalsMethod() {
        Config defaultConfig = new Config();
        Config logConfig = new Config();
        logConfig.setLogLevel(Level.FINE);
        Config pathConfig = new Config();
        pathConfig.setUserPrefsFilePath(Paths.get("config.json"));
        assertNotNull(defaultConfig);

        // same object
        assertTrue(defaultConfig.equals(defaultConfig));

        // same values
        assertTrue(defaultConfig.equals(new Config()));
        assertEquals(defaultConfig.hashCode(), new Config().hashCode());

        // different class
        assertFalse(defaultConfig.equals("config"));
        assertFalse(defaultConfig.equals(null));

        // different log levels
        assertFalse(defaultConfig.equals(logConfig));

        // different paths
        assertFalse(defaultConfig.equals(pathConfig));
    }


}
