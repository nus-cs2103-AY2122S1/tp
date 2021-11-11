package seedu.intern.model.applicant;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.intern.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class InstitutionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Institution(null));
    }

    @Test
    public void constructor_invalidInstitution_throwsIllegalArgumentException() {
        String invalidInstitution = "";
        assertThrows(IllegalArgumentException.class, () -> new Institution(invalidInstitution));
    }

    @Test
    public void isValidInstitution() {
        // null name
        assertThrows(NullPointerException.class, () -> Institution.isValidInstitution(null));

        // invalid name
        assertFalse(Institution.isValidInstitution("")); // empty string
        assertFalse(Institution.isValidInstitution(" ")); // spaces only
        assertFalse(Institution.isValidInstitution("^")); // only non-alphanumeric characters
        assertFalse(Institution.isValidInstitution("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(Institution.isValidInstitution("NUS")); // alphabets only
        assertTrue(Institution.isValidInstitution("Nanyang Institute of Technology")); // long names
        assertTrue(Institution.isValidInstitution("NUS2021")); // alphanumeric characters
    }
}
