package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TodoTaskBuilder;

public class TaskNameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        TaskNameContainsKeywordsPredicate firstPredicate =
                new TaskNameContainsKeywordsPredicate(firstPredicateKeywordList);
        TaskNameContainsKeywordsPredicate secondPredicate =
                new TaskNameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TaskNameContainsKeywordsPredicate firstPredicateCopy =
                new TaskNameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different task -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        TaskNameContainsKeywordsPredicate predicate =
                new TaskNameContainsKeywordsPredicate(Collections.singletonList("Tutorial"));
        assertTrue(predicate.test(new TodoTaskBuilder().withName("Tutorial MONDAY").build()));

        // Multiple keywords
        predicate = new TaskNameContainsKeywordsPredicate(Arrays.asList("Tutorial", "MONDAY"));
        assertTrue(predicate.test(new TodoTaskBuilder().withName("Tutorial MONDAY").build()));

        // Only one matching keyword
        predicate = new TaskNameContainsKeywordsPredicate(Arrays.asList("MONDAY", "different"));
        assertTrue(predicate.test(new TodoTaskBuilder().withName("Tutorial different").build()));

        // Mixed-case keywords
        predicate = new TaskNameContainsKeywordsPredicate(Arrays.asList("tutORiaL", "monday"));
        assertTrue(predicate.test(new TodoTaskBuilder().withName("Tutorial MONDAY").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        TaskNameContainsKeywordsPredicate predicate = new TaskNameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new TodoTaskBuilder().withName("Tutorial").build()));

        // Non-matching keyword
        predicate = new TaskNameContainsKeywordsPredicate(List.of("different"));
        assertFalse(predicate.test(new TodoTaskBuilder().withName("Tutorial MONDAY").build()));

        // Keywords match phone, email and address, but does not match name
        predicate = new TaskNameContainsKeywordsPredicate(
                Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new TodoTaskBuilder().withName("Tutorial")
                .withDescription("Go through worksheet").build()));
    }
}
