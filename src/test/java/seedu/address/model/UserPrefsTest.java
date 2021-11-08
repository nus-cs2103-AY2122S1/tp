package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;

public class UserPrefsTest {

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        UserPrefs userPref = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPref.setGuiSettings(null));
    }

    @Test
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        UserPrefs userPrefs = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPrefs.setAddressBookFilePath(null));
    }

    @Test
    public void equals() {
        UserPrefs userPref = new UserPrefs();
        GuiSettings guiSettings = new GuiSettings(500, 500, 200, 200);
        userPref.setGuiSettings(guiSettings);

        UserPrefs otherPref = new UserPrefs();

        // same object
        assertTrue(userPref.equals(userPref));

        // different class
        assertFalse(userPref.equals(null));
        assertFalse(userPref.equals(guiSettings));

        // different gui settings
        assertFalse(userPref.equals(otherPref));

        // same gui settings
        otherPref.setGuiSettings(guiSettings);
        assertTrue(userPref.equals(otherPref));
    }
}
