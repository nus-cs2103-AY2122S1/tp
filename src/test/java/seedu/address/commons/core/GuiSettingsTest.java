package seedu.address.commons.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class GuiSettingsTest {
    @Test
    public void equals() {
        GuiSettings guiSettings = new GuiSettings();
        assertFalse(guiSettings.equals(null));
    }
}
