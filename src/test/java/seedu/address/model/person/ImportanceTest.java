package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ImportanceTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Importance(null));
    }

    @Test
    public void isValidImportance() {
        // null
        assertThrows(NullPointerException.class, () -> Importance.isValidImportance(null));

        // invalid importance
        assertFalse(Importance.isValidImportance("")); // empty string
        assertFalse(Importance.isValidImportance(" ")); // spaces only
        assertFalse(Importance.isValidImportance("bob")); // other words
        assertFalse(Importance.isValidImportance("trueb")); // contain valid but invalid
        assertFalse(Importance.isValidImportance("true a")); // contain valid but has invalid with space
        // valid importance
        assertTrue(Name.isValidName("true")); // lower case
        assertTrue(Name.isValidName("FALSE")); // upper case
        assertTrue(Name.isValidName("TrUe")); // mixed case
    }

    @Test
    public void returnCorrectBoolean() {
        assertTrue(new Importance(true).isImportant());
        assertFalse(new Importance(false).isImportant());
    }
}
