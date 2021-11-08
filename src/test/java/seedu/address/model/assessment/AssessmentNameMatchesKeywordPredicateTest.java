package seedu.address.model.assessment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.PredicateTestUtil.assertPredicateFailure;
import static seedu.address.model.PredicateTestUtil.assertPredicateSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.AssessmentBuilder;

public class AssessmentNameMatchesKeywordPredicateTest {

    @Test
    public void equals() {
        String firstPredicateKeyword = "Midterms";
        String secondPredicateKeyword = "Lab";

        AssessmentNameMatchesKeywordPredicate firstPredicate =
                new AssessmentNameMatchesKeywordPredicate(firstPredicateKeyword);
        AssessmentNameMatchesKeywordPredicate secondPredicate =
                new AssessmentNameMatchesKeywordPredicate(secondPredicateKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        AssessmentNameMatchesKeywordPredicate firstPredicateCopy =
                new AssessmentNameMatchesKeywordPredicate(firstPredicateKeyword);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different assessment -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_assessmentNameMatchesKeyword_returnsTrue() {
        // One keyword
        AssessmentNameMatchesKeywordPredicate predicate =
                new AssessmentNameMatchesKeywordPredicate("Midterms");
        assertPredicateSuccess(predicate, new AssessmentBuilder().withAssesmentName("Midterms").build(), true);
    }

    @Test
    public void test_assessmentNameDoesNotMatchKeyword_returnsFalse() {
        // Zero keywords
        AssessmentNameMatchesKeywordPredicate predicate =
                new AssessmentNameMatchesKeywordPredicate("");
        assertPredicateFailure(predicate, new AssessmentBuilder().withAssesmentName("Midterms").build(),
                AssessmentName.MESSAGE_CONSTRAINTS);

        // Non-matching keyword
        predicate = new AssessmentNameMatchesKeywordPredicate("Midterms");
        assertPredicateSuccess(predicate, new AssessmentBuilder().withAssesmentName("Finals").build(), false);

        // Keyword matches some parts of assessment name
        predicate = new AssessmentNameMatchesKeywordPredicate("Lab");
        assertPredicateSuccess(predicate, new AssessmentBuilder().withAssesmentName("Lab5").build(), false);

        // Mixed-case keyword
        predicate = new AssessmentNameMatchesKeywordPredicate("fIeLdWorK");
        assertPredicateSuccess(predicate, new AssessmentBuilder().withAssesmentName("fIeLdWorK").build(), true);
    }
}
