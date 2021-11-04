package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ExperienceTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Experience(null));
    }

    @Test
    public void constructor_invalidExperience_throwsIllegalArgumentException() {
        String invalidExperience = "-2";
        assertThrows(IllegalArgumentException.class, () -> new Experience(invalidExperience));
    }

    @Test
    public void isValidExperience() {
        // null experience
        assertThrows(NullPointerException.class, () -> Experience.isValidExperience(null));

        // invalid experience
        assertFalse(Experience.isValidExperience("")); // empty string
        assertFalse(Experience.isValidExperience(" ")); // spaces only
        assertFalse(Experience.isValidExperience("^!@#$%")); // only non-alphanumeric characters
        assertFalse(Experience.isValidExperience("11*")); // contains non-alphanumeric characters
        assertFalse(Experience.isValidExperience("1.1")); // positive number with decimal other than .0 and .5
        assertFalse(Experience.isValidExperience("-11")); // contains negative number
        assertFalse(Experience.isValidExperience("12345")); // number is too large
        assertFalse(Experience.isValidExperience("12345678909876")); // long number


        // valid experience
        assertTrue(Experience.isValidExperience("1")); // positive number without decimals
        assertTrue(Experience.isValidExperience("3.0")); // positive number with .0
        assertTrue(Experience.isValidExperience("5.5")); // positive number with .5
        assertTrue(Experience.isValidExperience(
                Double.toString(Experience.MAX_EXPERIENCE))); // number not larger than maximum

    }
}
