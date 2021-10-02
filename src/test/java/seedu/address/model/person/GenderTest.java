package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class GenderTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Gender(null));
    }

    @Test
    public void constructor_invalidGender_throwsIllegalArgumentException() {
        String invalidGender = "*hello";
        assertThrows(IllegalArgumentException.class, () -> new Gender(invalidGender));
    }

    @Test
    public void isValidGender() {
        // null gender
        assertThrows(NullPointerException.class, () -> Gender.isValidGender(null));

        // invalid gender
        assertFalse(Gender.isValidGender("hehe"));
        assertFalse(Gender.isValidGender("female^"));

        // valid gender
        assertTrue(Gender.isValidGender("f"));
        assertTrue(Gender.isValidGender("Female"));
        assertTrue(Gender.isValidGender("MALE"));
        assertTrue(Gender.isValidGender("male"));
        assertTrue(Gender.isValidGender("m"));
    }

    @Test
    public void getGenderType() {
        assertEquals(GenderType.MALE, new Gender("m").value);
        assertEquals(GenderType.MALE, new Gender("mAle").value);
        assertEquals(GenderType.FEMALE, new Gender("F").value);
        assertEquals(GenderType.FEMALE, new Gender("femAle").value);
    }

    @Test
    public void equal() {
        Gender gender = new Gender("Male");

        // same object -> returns true
        assertTrue(gender.equals(gender));

        // same values -> returns true
        Gender remarkCopy = new Gender(gender.toString());
        assertTrue(gender.equals(remarkCopy));

        // different types -> returns false
        assertFalse(gender.equals(1));

        // null -> returns false
        assertFalse(gender.equals(null));

        // different gender -> returns false
        Gender differentGender = new Gender("FEMALE");
        assertFalse(gender.equals(differentGender));
    }
}
