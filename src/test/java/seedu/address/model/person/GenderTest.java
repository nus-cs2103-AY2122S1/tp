package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class GenderTest {
    @Test
    public void constructor_null_createsDefaultGender() {
        String noGender = "N.A";
        assertEquals(new Gender(null).value, noGender);
    }

    @Test
    public void constructor_invalidGender_throwsIllegalArgumentException() {
        String invalidGender = "Dinosaur";
        assertThrows(IllegalArgumentException.class, () -> new Gender(invalidGender));
    }

    @Test
    public void isValidAddress() {
        // invalid genders
        assertFalse(Gender.isValidGender("")); // empty string
        assertFalse(Gender.isValidGender(" ")); // spaces only
        assertFalse(Gender.isValidGender("ME")); // Anything other than 'M', 'F' or 'N.A'
        assertFalse(Gender.isValidGender("123")); // Anything other than 'M', 'F' or 'N.A'

        // valid genders
        assertTrue(Gender.isValidGender("M"));
        assertTrue(Gender.isValidGender("F"));
        assertTrue(Gender.isValidGender("m")); // lower case
        assertTrue(Gender.isValidGender("f")); // lower case
        assertTrue(Gender.isValidGender("N.A"));
    }

    @Test
    public void isEmpty() {
        //true only when null or "N.A" is passed into constructor, case-insensitive
        Gender test = new Gender(null);
        assertTrue(test.isEmpty());

        test = new Gender("N.A");
        assertTrue(test.isEmpty());

        test = new Gender("n.A");
        assertTrue(test.isEmpty());

        //False either m or f
        test = new Gender("f");
        assertFalse(test.isEmpty());

        test = new Gender("m");
        assertFalse(test.isEmpty());
    }
}
