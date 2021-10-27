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
        assertFalse(Nationality.isValidNationality("-")); // dash only
        assertFalse(Nationality.isValidNationality("Singapore")); // this is a country, not a nationality
        assertFalse(Nationality.isValidNationality("Indonesia"));
        assertFalse(Nationality.isValidNationality("SoCian")); // invalid input

        // valid nationality
        assertTrue(Nationality.isValidNationality("Singaporean"));
        assertTrue(Nationality.isValidNationality("Indonesian"));
        assertTrue(Nationality.isValidNationality("American"));
        // case-insensitive
        assertTrue(Nationality.isValidNationality("singaporean"));
        assertTrue(Nationality.isValidNationality("south korean"));
        assertTrue(Nationality.isValidNationality("vietnamese"));
        assertTrue(Nationality.isValidNationality("thai"));
    }
}
