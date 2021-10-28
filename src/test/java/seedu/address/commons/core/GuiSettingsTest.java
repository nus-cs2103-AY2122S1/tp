package seedu.address.commons.core;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class GuiSettingsTest {
    @Test
    public void equals() {
        GuiSettings guiSettings = new GuiSettings();
        assertNotEquals(null, guiSettings);
    }
}
