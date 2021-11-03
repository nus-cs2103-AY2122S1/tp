package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import java.util.Objects;

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
        UserPrefs up1 = new UserPrefs();
        // same object
        assertEquals(up1, up1);
        // null
        assertNotEquals(up1, null);
        // different types
        assertNotEquals(up1, 1);

        UserPrefs up2 = new UserPrefs();
        // same default values
        assertEquals(up1, up2);
    }

    @Test
    public void hashCode_success() {
        UserPrefs userPrefs = new UserPrefs();
        assertEquals(userPrefs.hashCode(),
                Objects.hash(userPrefs.getGuiSettings(),
                        userPrefs.getAddressBookFilePath(),
                        userPrefs.getAliases()));

        assertEquals(new UserPrefs().hashCode(), new UserPrefs().hashCode());
    }

}
