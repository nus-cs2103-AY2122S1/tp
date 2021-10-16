package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NationalityTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Nationality(null));
    }

    @Test
    public void isValidNationality() {
        // null nationality
        assertThrows(NullPointerException.class, () -> Nationality.isValidNationality(null));

        // invalid nationality
        assertFalse(Nationality.isValidNationality(" ")); // spaces only

        // valid nationality
        assertTrue(Nationality.isValidNationality("Singapore"));
        assertTrue(Nationality.isValidNationality("-")); // one character
        assertTrue(Nationality.isValidNationality("")); // empty string
    }
}
