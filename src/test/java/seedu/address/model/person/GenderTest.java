package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class GenderTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Gender(null));
    }

    @Test
    public void isValidGender() {
        // null gender
        assertThrows(NullPointerException.class, () -> Gender.isValidGender(null));

        // invalid Gender
        assertFalse(Gender.isValidGender(" ")); // spaces only
        assertFalse(Gender.isValidGender("B")); // invalid character
        assertFalse(Gender.isValidGender("MF")); // more than one character

        // valid Gender
        assertTrue(Gender.isValidGender("M"));
        assertTrue(Gender.isValidGender("F"));
        assertTrue(Gender.isValidGender("O"));
        assertTrue(Gender.isValidGender("m"));
        assertTrue(Gender.isValidGender("f"));
        assertTrue(Gender.isValidGender("o"));
    }

}
