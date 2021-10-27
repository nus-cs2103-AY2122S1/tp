package seedu.address.model.assessment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AssessmentNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AssessmentName(null));
    }

    @Test
    public void constructor_invalidAssessmentName_throwsIllegalArgumentException() {
        String invalidAssessmentName = "";
        assertThrows(IllegalArgumentException.class, () -> new AssessmentName(invalidAssessmentName));
    }

    @Test
    public void isValidAssessmentName() {
        // null assessment name
        assertThrows(NullPointerException.class, () -> AssessmentName.isValidAssessmentName(null));

        // invalid assessment name
        assertFalse(AssessmentName.isValidAssessmentName("")); // empty string
        assertFalse(AssessmentName.isValidAssessmentName(" ")); // spaces only
        assertFalse(AssessmentName.isValidAssessmentName("^")); // only non-alphanumeric characters
        assertFalse(AssessmentName.isValidAssessmentName("midterms*")); // contains non-alphanumeric characters

        // valid assessment name
        assertTrue(AssessmentName.isValidAssessmentName("assignment one")); // alphabets only
        assertTrue(AssessmentName.isValidAssessmentName("12345")); // numbers only
        assertTrue(AssessmentName.isValidAssessmentName("Lab5")); // alphanumeric characters
        assertTrue(AssessmentName.isValidAssessmentName("Final Lab")); // with capital letters
        assertTrue(AssessmentName.isValidAssessmentName(
                "Assignment One of the First Academic Year")); // long assessment names
    }
}
