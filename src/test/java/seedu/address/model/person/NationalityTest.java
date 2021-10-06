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
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidNationality = "";
        assertThrows(IllegalArgumentException.class, () -> new Nationality(invalidNationality));
    }

    @Test
    public void isValidNationality() {
        // null address
        assertThrows(NullPointerException.class, () -> Nationality.isValidNationality(null));

        // invalid addresses
        assertFalse(Nationality.isValidNationality("")); // empty string
        assertFalse(Nationality.isValidNationality(" ")); // spaces only

        // valid addresses
        assertTrue(Nationality.isValidNationality("Blk 456, Den Road, #01-355"));
        assertTrue(Nationality.isValidNationality("-")); // one character
        assertTrue(Nationality.isValidNationality(
                "Leng Inc; 1234 Market St; San Francisco CA 2349879; USA")); // long address
    }
}
