package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class LevelOfEducationTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new LevelOfEducation(null));
    }

    @Test
    public void constructor_invalidLevelOfEducation_throwsIllegalArgumentException() {
        String invalidLevelOfEducation = "";
        assertThrows(IllegalArgumentException.class, () -> new LevelOfEducation(invalidLevelOfEducation));
    }

    @Test
    public void isValidAddress() {
        // null address
        assertThrows(NullPointerException.class, () -> LevelOfEducation.isValidLevelOfEducation(null));

        // invalid addresses
        assertFalse(LevelOfEducation.isValidLevelOfEducation("")); // empty string
        assertFalse(LevelOfEducation.isValidLevelOfEducation(" ")); // spaces only
        assertFalse(LevelOfEducation.isValidLevelOfEducation("Kindergarten")); // unsupported level of education

        // valid addresses
        assertTrue(LevelOfEducation.isValidLevelOfEducation("Elementary"));
        assertTrue(LevelOfEducation.isValidLevelOfEducation("Middle School"));
        assertTrue(LevelOfEducation.isValidLevelOfEducation("High School"));
        assertTrue(LevelOfEducation.isValidLevelOfEducation("University"));
        assertTrue(LevelOfEducation.isValidLevelOfEducation("Bachelors"));
        assertTrue(LevelOfEducation.isValidLevelOfEducation("Masters"));
        assertTrue(LevelOfEducation.isValidLevelOfEducation("PhD"));
    }
}
