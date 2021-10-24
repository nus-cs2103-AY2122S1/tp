package seedu.address.model.assessment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.AssessmentBuilder;

public class AssessmentNameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("Midterms");
        List<String> secondPredicateKeywordList = Arrays.asList("Lab", "Finals");

        AssessmentNameContainsKeywordsPredicate firstPredicate =
                new AssessmentNameContainsKeywordsPredicate(firstPredicateKeywordList);
        AssessmentNameContainsKeywordsPredicate secondPredicate =
                new AssessmentNameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        AssessmentNameContainsKeywordsPredicate firstPredicateCopy =
                new AssessmentNameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different assessment -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_assessmentNameContainsKeywords_returnsTrue() {
        // One keyword
        AssessmentNameContainsKeywordsPredicate predicate =
                new AssessmentNameContainsKeywordsPredicate(Collections.singletonList("Midterms"));
        assertTrue(predicate.test(new AssessmentBuilder().withAssesmentName("Midterms").build()));

        // Multiple keywords
        predicate = new AssessmentNameContainsKeywordsPredicate(Arrays.asList("Fieldwork", "Assignment"));
        assertTrue(predicate.test(new AssessmentBuilder().withAssesmentName("Fieldwork Assignment").build()));

        // Only one matching keyword
        predicate = new AssessmentNameContainsKeywordsPredicate(Arrays.asList("Fieldwork", "Assignment"));
        assertTrue(predicate.test(new AssessmentBuilder().withAssesmentName("Assignment One").build()));

        // Mixed-case keywords
        predicate = new AssessmentNameContainsKeywordsPredicate(Arrays.asList("fIeLdWorK", "AsSigNmEnt"));
        assertTrue(predicate.test(new AssessmentBuilder().withAssesmentName("fIeLdWorK AsSigNmEnt").build()));
    }

    @Test
    public void test_assessmentNameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        AssessmentNameContainsKeywordsPredicate predicate =
                new AssessmentNameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new AssessmentBuilder().withAssesmentName("Midterms").build()));

        // Non-matching keyword
        predicate = new AssessmentNameContainsKeywordsPredicate(Arrays.asList("Midterms"));
        assertFalse(predicate.test(new AssessmentBuilder().withAssesmentName("Finals").build()));

        // Keywords match score, but does not match assessment name
        predicate = new AssessmentNameContainsKeywordsPredicate(Collections.singletonList("60/100"));
        assertFalse(predicate.test(new AssessmentBuilder().withAssesmentName("Finals")
                .withScore(60, 100).build()));
    }
}
