package seedu.address.model.application;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ApplicationBuilder;

public class DeadlineContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("2020-10-10");
        List<String> secondPredicateKeywordList = Arrays.asList("2021-10-10", "2021-11-11");

        DeadlineContainsKeywordsPredicate firstPredicate =
                new DeadlineContainsKeywordsPredicate(firstPredicateKeywordList);
        DeadlineContainsKeywordsPredicate secondPredicate =
                new DeadlineContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        DeadlineContainsKeywordsPredicate firstPredicateCopy =
                new DeadlineContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different application -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_deadlineContainsKeywords_returnsTrue() {
        // One keyword
        DeadlineContainsKeywordsPredicate predicate =
                new DeadlineContainsKeywordsPredicate(Collections.singletonList("2020-10-10"));
        assertTrue(predicate.test(
                new ApplicationBuilder().withDeadline("2020-10-10").build()));

        // Only one matching keyword
        predicate =
                new DeadlineContainsKeywordsPredicate(Arrays.asList("2020-10-10", "2020-10-11"));
        assertTrue(predicate.test(
                new ApplicationBuilder().withDeadline("2020-10-10").build()));
    }

    @Test
    public void test_deadlineDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        DeadlineContainsKeywordsPredicate predicate =
                new DeadlineContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(
                new ApplicationBuilder().withDeadline("2005-05-05").build()));

        // Non-matching keyword
        predicate =
                new DeadlineContainsKeywordsPredicate(Arrays.asList("2006-06-06"));
        assertFalse(predicate.test(
                new ApplicationBuilder().withDeadline("2020-10-10").build()));
    }
}
