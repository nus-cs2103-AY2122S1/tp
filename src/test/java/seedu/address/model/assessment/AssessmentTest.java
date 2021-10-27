package seedu.address.model.assessment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ACTUAL_SCORE_LAB5;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSESSMENT_NAME_LAB5;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TOTAL_SCORE_LAB5;
import static seedu.address.testutil.TypicalAssessments.LAB5;
import static seedu.address.testutil.TypicalAssessments.MIDTERMS;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.AssessmentBuilder;

public class AssessmentTest {

    @Test
    public void isSameAssessment() {
        // same object -> returns true
        assertTrue(MIDTERMS.isSameAssessment(MIDTERMS));

        // null -> returns false
        assertFalse(MIDTERMS.isSameAssessment(null));

        // same assessment name, different score -> returns true
        Assessment editedMidterms = new AssessmentBuilder(MIDTERMS).withScore(60, 100).build();
        assertTrue(MIDTERMS.isSameAssessment(editedMidterms));

        // different assessment name, same score -> returns false
        editedMidterms = new AssessmentBuilder(MIDTERMS).withAssesmentName(VALID_ASSESSMENT_NAME_LAB5).build();
        assertFalse(MIDTERMS.isSameAssessment(editedMidterms));

        // name differs in case, all other attributes same -> returns false
        Assessment editedLab5 = new AssessmentBuilder(LAB5)
                .withAssesmentName(VALID_ASSESSMENT_NAME_LAB5.toLowerCase()).build();
        assertFalse(LAB5.isSameAssessment(editedLab5));

        // name has trailing spaces, all other attributes same -> returns false
        String assessmentNameWithTrailingSpaces = VALID_ASSESSMENT_NAME_LAB5 + " ";
        editedLab5 = new AssessmentBuilder(LAB5).withAssesmentName(assessmentNameWithTrailingSpaces).build();
        assertFalse(LAB5.isSameAssessment(editedLab5));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Assessment midtermsCopy = new AssessmentBuilder(MIDTERMS).build();
        assertTrue(MIDTERMS.equals(midtermsCopy));

        // same object -> returns true
        assertTrue(MIDTERMS.equals(MIDTERMS));

        // null -> returns false
        assertFalse(MIDTERMS.equals(null));

        // different type -> returns false
        assertFalse(MIDTERMS.equals(5));

        // different assessment -> returns false
        assertFalse(MIDTERMS.equals(LAB5));

        // different assessment name -> returns false
        Assessment editedMidterms = new AssessmentBuilder(MIDTERMS)
                .withAssesmentName(VALID_ASSESSMENT_NAME_LAB5).build();
        assertFalse(MIDTERMS.equals(editedMidterms));

        // different score -> returns false
        editedMidterms = new AssessmentBuilder(MIDTERMS)
                .withScore(VALID_ACTUAL_SCORE_LAB5, VALID_TOTAL_SCORE_LAB5).build();
        assertFalse(MIDTERMS.equals(editedMidterms));
    }
}
