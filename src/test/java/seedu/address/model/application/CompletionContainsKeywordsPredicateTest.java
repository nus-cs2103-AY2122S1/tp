package seedu.address.model.application;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ApplicationBuilder;

public class CompletionContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("Completed");
        List<String> secondPredicateKeywordList = Collections.singletonList("Uncompleted");

        CompletionContainsKeywordsPredicate firstPredicate =
                new CompletionContainsKeywordsPredicate(firstPredicateKeywordList);
        CompletionContainsKeywordsPredicate secondPredicate =
                new CompletionContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        CompletionContainsKeywordsPredicate firstPredicateCopy =
                new CompletionContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different application -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_completionContainsKeywords_returnsTrue() {
        // One keyword
        CompletionContainsKeywordsPredicate predicate =
                new CompletionContainsKeywordsPredicate(Collections.singletonList("Completed"));
        assertTrue(predicate.test(
                new ApplicationBuilder().withCompletion("Completed").build()));

        // Multiple keywords
        predicate =
                new CompletionContainsKeywordsPredicate(Arrays.asList("Completed", "Uncompleted"));
        assertTrue(predicate.test(
                new ApplicationBuilder().withCompletion("Completed").build()));

        // Only one matching keyword
        predicate =
                new CompletionContainsKeywordsPredicate(Arrays.asList("Completed", "Grab"));
        assertTrue(predicate.test(
                new ApplicationBuilder().withCompletion("Completed").build()));

        // Mixed-case keywords
        predicate =
                new CompletionContainsKeywordsPredicate(Arrays.asList("comPleted"));
        assertTrue(predicate.test(
                new ApplicationBuilder().withCompletion("Completed").build()));
    }

    @Test
    public void test_completionDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        CompletionContainsKeywordsPredicate predicate =
                new CompletionContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(
                new ApplicationBuilder().withCompletion("Completed").build()));
    }
}
