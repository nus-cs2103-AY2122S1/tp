package seedu.address.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    public void equalsMethod() {
        Config defaultConfig = new Config();
        Config otherConfig = new Config();
        assertNotNull(defaultConfig);
        assertTrue(defaultConfig.equals(defaultConfig));

        assertTrue(defaultConfig.hashCode() == otherConfig.hashCode());

        assertFalse(defaultConfig.equals("String"));

        assertFalse(defaultConfig.equals(5));
    }

    @Test
    public void getters_notNull() {
        Config defaultConfig = new Config();

        assertNotNull(defaultConfig.getUserPrefsFilePath());

        assertTrue(defaultConfig.getLogLevel() == Level.INFO);
    }
}
