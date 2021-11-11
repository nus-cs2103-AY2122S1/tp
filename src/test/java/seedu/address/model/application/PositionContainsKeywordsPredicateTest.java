package seedu.address.model.application;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ApplicationBuilder;

public class PositionContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("Engineer");
        List<String> secondPredicateKeywordList = Arrays.asList("Engineer", "Manager");

        PositionContainsKeywordsPredicate firstPredicate =
                new PositionContainsKeywordsPredicate(firstPredicateKeywordList);
        PositionContainsKeywordsPredicate secondPredicate =
                new PositionContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PositionContainsKeywordsPredicate firstPredicateCopy =
                new PositionContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different application -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_positionContainsKeywords_returnsTrue() {
        // One keyword
        PositionContainsKeywordsPredicate predicate =
                new PositionContainsKeywordsPredicate(Collections.singletonList("Manager"));
        assertTrue(predicate.test(
                new ApplicationBuilder().withPosition("Senior Manager").build()));

        // Multiple keywords
        predicate =
                new PositionContainsKeywordsPredicate(Arrays.asList("Senior", "Manager"));
        assertTrue(predicate.test(
                new ApplicationBuilder().withPosition("Senior Manager").build()));

        // Only one matching keyword
        predicate =
                new PositionContainsKeywordsPredicate(Arrays.asList("Senior", "Manager"));
        assertTrue(predicate.test(
                new ApplicationBuilder().withPosition("Junior Manager").build()));

        // Mixed-case keywords
        predicate =
                new PositionContainsKeywordsPredicate(Arrays.asList("ManageR", "EngineeR"));
        assertTrue(predicate.test(
                new ApplicationBuilder().withPosition("Manager Engineer").build()));
    }

    @Test
    public void test_positionDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        PositionContainsKeywordsPredicate predicate =
                new PositionContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(
                new ApplicationBuilder().withPosition("Engineer").build()));

        // Non-matching keyword
        predicate =
                new PositionContainsKeywordsPredicate(Arrays.asList("Manager"));
        assertFalse(predicate.test(
                new ApplicationBuilder().withPosition("Programmer").build()));
    }
}
