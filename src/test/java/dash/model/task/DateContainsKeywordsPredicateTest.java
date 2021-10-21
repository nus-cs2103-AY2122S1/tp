package dash.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import dash.testutil.TaskBuilder;

public class DateContainsKeywordsPredicateTest {
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("Oct");
        List<String> secondPredicateKeywordList = Arrays.asList("14", "Oct");

        DateContainsKeywordsPredicate firstPredicate = new DateContainsKeywordsPredicate(
                firstPredicateKeywordList);
        DateContainsKeywordsPredicate secondPredicate = new DateContainsKeywordsPredicate(
                secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        DateContainsKeywordsPredicate firstPredicateCopy = new DateContainsKeywordsPredicate(
                firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different task -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_descriptionContainsKeywords_returnsTrue() {
        // One keyword
        DateContainsKeywordsPredicate predicate = new DateContainsKeywordsPredicate(
                Collections.singletonList("Oct"));
        assertTrue(predicate.test(new TaskBuilder().withTaskDate("14 Oct 2021").build()));

        // Multiple keywords
        predicate = new DateContainsKeywordsPredicate(Arrays.asList("14", "Oct"));
        assertTrue(predicate.test(new TaskBuilder().withTaskDate("14 Oct 2021").build()));

        // Mixed-case keywords
        predicate = new DateContainsKeywordsPredicate(Arrays.asList("oCt"));
        assertTrue(predicate.test(new TaskBuilder().withTaskDate("14 Oct 2021").build()));
    }

    @Test
    public void test_descriptionDoesNotContainKeywords_returnsFalse() {
        // Non-matching keyword
        DateContainsKeywordsPredicate predicate = new DateContainsKeywordsPredicate(Arrays.asList(
                "Nov"));
        assertFalse(predicate.test(new TaskBuilder().withTaskDate("14 Oct 2021").build()));

        // Only one matching keyword, fails because of AND search
        predicate = new DateContainsKeywordsPredicate(Arrays.asList("14", "Nov"));
        assertFalse(predicate.test(new TaskBuilder().withTaskDate("14 Oct 2021").build()));

        // Keywords match tags, but does not match date
        predicate = new DateContainsKeywordsPredicate(Arrays.asList("st2334", "graded"));
        assertFalse(predicate.test(new TaskBuilder().withTaskDate("14 Oct 2021").withTags(
                "st2334", "graded").build()));
    }
}
