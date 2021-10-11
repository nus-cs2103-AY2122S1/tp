package seedu.address.model.commons;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class IdTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ID(null));
    }

    @Test
    public void constructor_invalidID_throwsIllegalArgumentException() {
        String invalidID = "abc";
        assertThrows(IllegalArgumentException.class, () -> new ID(invalidID));
    }

    @Test
    public void equals() {
        ID id = new ID("2");

        // same object -> returns true
        assertTrue(id.equals(id));

        // same id -> returns true
        assertTrue(id.equals(new ID("2")));

        // different id -> returns false
        assertFalse(id.equals(new ID("5")));

    }
}
