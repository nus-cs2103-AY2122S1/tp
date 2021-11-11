package seedu.address.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
    public void getLogLevel_defaultObject_levelReturned() {
        assertEquals(Level.INFO, new Config().getLogLevel());
    }

    @Test
    public void getUserPrefsFilePath_defaultObject_pathReturned() {
        assertEquals(Paths.get("preferences.json"), new Config().getUserPrefsFilePath());
    }

    @Test
    public void equalsMethod() {
        Config defaultConfig = new Config();
        assertNotNull(defaultConfig);

        // same object -> returns true
        assertEquals(defaultConfig, defaultConfig);

        // null -> returns false
        assertNotEquals(defaultConfig, null);
    }


}
