package seedu.address.model.application;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ApplicationBuilder;

public class StatusContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("Pending");
        List<String> secondPredicateKeywordList = Arrays.asList("Pending", "Accepted");

        StatusContainsKeywordsPredicate firstPredicate =
                new StatusContainsKeywordsPredicate(firstPredicateKeywordList);
        StatusContainsKeywordsPredicate secondPredicate =
                new StatusContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        StatusContainsKeywordsPredicate firstPredicateCopy =
                new StatusContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different application -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_statusContainsKeywords_returnsTrue() {
        // One keyword
        StatusContainsKeywordsPredicate predicate =
                new StatusContainsKeywordsPredicate(Collections.singletonList("Pending"));
        assertTrue(predicate.test(
                new ApplicationBuilder().withStatus("Pending").build()));

        // Multiple keywords
        predicate =
                new StatusContainsKeywordsPredicate(Arrays.asList("Accepted", "Rejected"));
        assertTrue(predicate.test(
                new ApplicationBuilder().withStatus("Accepted").build()));

        // Only one matching keyword
        predicate =
                new StatusContainsKeywordsPredicate(Arrays.asList("Accepted", "Rejected"));
        assertTrue(predicate.test(
                new ApplicationBuilder().withStatus("Accepted").build()));

        // Mixed-case keywords
        predicate =
                new StatusContainsKeywordsPredicate(Arrays.asList("PendinG", "RejecTed"));
        assertTrue(predicate.test(
                new ApplicationBuilder().withStatus("Pending").build()));
    }

    @Test
    public void test_statusDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        StatusContainsKeywordsPredicate predicate =
                new StatusContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(
                new ApplicationBuilder().withStatus("Rejected").build()));

        // Non-matching keyword
        predicate =
                new StatusContainsKeywordsPredicate(Arrays.asList("Rejected"));
        assertFalse(predicate.test(
                new ApplicationBuilder().withStatus("Pending").build()));
    }
}
