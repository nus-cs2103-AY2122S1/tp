package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

public class UserPrefsTest {

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        UserPrefs userPref = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPref.setGuiSettings(null));
    }

    @Test
    public void setInventoryFilePath_nullPath_throwsNullPointerException() {
        UserPrefs userPrefs = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPrefs.setInventoryFilePath(null));
    }

    @Test
    public void equals_sameObject() {
        UserPrefs userPrefs = new UserPrefs();
        assertTrue(userPrefs.equals(userPrefs));
    }

    @Test
    public void unequal_notUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        assertFalse(userPrefs.equals(1));
    }

    @Test
    public void hashCodeTest() {
        UserPrefs userPrefs = new UserPrefs();
        assertTrue(userPrefs.hashCode() == userPrefs.hashCode());
    }

    @Test
    public void getBookKeepingTest() {
        UserPrefs userPrefs = new UserPrefs();
        assertTrue(userPrefs.getBookKeepingFilePath().equals(Paths.get("data", "bookKeeping.json")));
    }



}
