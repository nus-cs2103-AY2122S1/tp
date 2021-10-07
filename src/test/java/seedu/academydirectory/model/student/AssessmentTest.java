package seedu.academydirectory.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.academydirectory.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AssessmentTest {

    @Test
    public void isValidAssessment() {
        // null assessment
        assertThrows(NullPointerException.class, () -> Assessment.isValidAssessment(null));

        // blank assessment
        assertFalse(Assessment.isValidAssessment("")); // empty string
        assertFalse(Assessment.isValidAssessment(" ")); // spaces only

        // incorrect assessment
        assertFalse(Assessment.isValidAssessment("Quiz"));
        assertFalse(Assessment.isValidAssessment("RA"));
        assertFalse(Assessment.isValidAssessment("PEE"));
        assertFalse(Assessment.isValidAssessment("RA 1"));

        // case-insensitive assessment
        assertTrue(Assessment.isValidAssessment("rA1"));
        assertTrue(Assessment.isValidAssessment("midTerm"));

        // valid assessment
        assertTrue(Assessment.isValidAssessment("RA1"));
        assertTrue(Assessment.isValidAssessment("MIDTERM"));
        assertTrue(Assessment.isValidAssessment("RA2"));
        assertTrue(Assessment.isValidAssessment("PE"));
        assertTrue(Assessment.isValidAssessment("FINAL"));
    }
}
