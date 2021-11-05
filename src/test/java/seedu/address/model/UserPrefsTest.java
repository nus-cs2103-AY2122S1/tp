package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.TypicalPersons;

public class UserPrefsTest {

    @Test
    public void equal_sameUserPrefs_success() {
        UserPrefs userPref = new UserPrefs();
        assertTrue(userPref.equals(userPref));
    }

    @Test
    public void equal_sameInstanceUserPrefs_success() {
        UserPrefs userPref1 = new UserPrefs();
        UserPrefs userPref2 = new UserPrefs();
        assertTrue(userPref1.equals(userPref2));
    }

    @Test
    public void equal_differentInstanceUserPrefs_success() {
        UserPrefs userPref1 = new UserPrefs();
        Person notUserPrefInstance = TypicalPersons.ALICE;
        assertFalse(userPref1.equals(notUserPrefInstance));
    }

    @Test
    public void hashcode_differentUserPref() {
        UserPrefs userPref1 = new UserPrefs();
        UserPrefs userPref2 = new UserPrefs();
        assertEquals(userPref1.hashCode(), userPref2.hashCode());
    }

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

}
