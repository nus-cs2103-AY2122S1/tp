package seedu.address.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Paths;
import java.util.logging.Level;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Phone;

public class ConfigTest {

    @Test
    public void toString_defaultObject_stringReturned() {
        String defaultConfigAsString = "Current log level : INFO\n"
                + "Preference file Location : preferences.json";

        assertEquals(defaultConfigAsString, new Config().toString());
    }

    @Test
    public void equalsMethod() {
        Config defaultConfig1 = new Config();
        Config defaultConfig2 = new Config();
        defaultConfig2.setUserPrefsFilePath(Paths.get("profbook.json"));
        assertNotNull(defaultConfig1);
        assertTrue(defaultConfig1.equals(defaultConfig1));
        assertFalse(defaultConfig1.equals(new Phone("12345678")));
        assertFalse(defaultConfig1.equals(defaultConfig2));
    }

    @Test
    public void getLogLevelTest() {
        Config defaultConfig = new Config();
        assertEquals(defaultConfig.getLogLevel(), Level.INFO);
    }

    @Test
    public void getUserPrefsFilePathTest() {
        Config defaultConfig = new Config();
        assertEquals(defaultConfig.getUserPrefsFilePath(), Paths.get("preferences.json"));
    }

    @Test
    public void hashCodeTest() {
        Config defaultConfig1 = new Config();
        Config defaultConfig2 = new Config();
        assertEquals(defaultConfig1.hashCode(), defaultConfig2.hashCode());
    }
}
