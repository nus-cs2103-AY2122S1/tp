package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class UserPrefsTest {

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        UserPrefs userPref = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPref.setGuiSettings(null));
    }

    @Test
    public void setHrManagerCandidateFilePath_nullPath_throwsNullPointerException() {
        UserPrefs userPrefs = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPrefs.setHrManagerCandidatesFilePath(null));
    }

    @Test
    public void equals() {
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        UserPrefs anotherUserPrefs = new UserPrefs();
        assertTrue(userPrefs.equals(anotherUserPrefs));

        // same object -> returns true
        assertEquals(userPrefs, userPrefs);
        assertTrue(anotherUserPrefs.equals(anotherUserPrefs));
        assertEquals(userPrefs.hashCode(), anotherUserPrefs.hashCode());

        // null -> returns false
        assertFalse(userPrefs.equals(null));

        // different types -> returns false
        assertFalse(userPrefs.equals(5));
    }

}
