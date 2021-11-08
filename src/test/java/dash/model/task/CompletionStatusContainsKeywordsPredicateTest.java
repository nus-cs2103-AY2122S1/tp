package dash.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import dash.testutil.TaskBuilder;

public class CompletionStatusContainsKeywordsPredicateTest {
    public void equals() {
        boolean trueBoolean = true;
        boolean falseBoolean = false;

        CompletionStatusContainsKeywordsPredicate firstPredicate = new CompletionStatusContainsKeywordsPredicate(
                trueBoolean);
        CompletionStatusContainsKeywordsPredicate secondPredicate = new CompletionStatusContainsKeywordsPredicate(
                falseBoolean);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        CompletionStatusContainsKeywordsPredicate firstPredicateCopy = new CompletionStatusContainsKeywordsPredicate(
                trueBoolean);
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
        // true
        CompletionStatusContainsKeywordsPredicate predicate = new CompletionStatusContainsKeywordsPredicate(true);
        assertTrue(predicate.test(new TaskBuilder().withCompletionStatus(true).build()));

        // false
        predicate = new CompletionStatusContainsKeywordsPredicate(false);
        assertTrue(predicate.test(new TaskBuilder().withCompletionStatus(false).build()));

    }

    @Test
    public void test_descriptionDoesNotContainKeywords_returnsFalse() {
        // true vs false
        CompletionStatusContainsKeywordsPredicate predicate = new CompletionStatusContainsKeywordsPredicate(true);
        assertFalse(predicate.test(new TaskBuilder().withCompletionStatus(false).build()));

        // false vs true
        predicate = new CompletionStatusContainsKeywordsPredicate(false);
        assertFalse(predicate.test(new TaskBuilder().withCompletionStatus(true).build()));


    }
}
