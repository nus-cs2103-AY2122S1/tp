package dash.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import dash.testutil.TaskBuilder;

public class TagTaskContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("homework");
        List<String> secondPredicateKeywordList = Arrays.asList("homework", "graded");

        TagTaskContainsKeywordsPredicate firstPredicate = new TagTaskContainsKeywordsPredicate(firstPredicateKeywordList);
        TagTaskContainsKeywordsPredicate secondPredicate = new TagTaskContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TagTaskContainsKeywordsPredicate firstPredicateCopy = new TagTaskContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_tagsContainsKeywords_returnsTrue() {
        // One keyword
        TagTaskContainsKeywordsPredicate predicate = new TagTaskContainsKeywordsPredicate(Collections.singletonList("homework"));
        assertTrue(predicate.test(new TaskBuilder().withTags("homework").build()));

        // Multiple keywords
        predicate = new TagTaskContainsKeywordsPredicate(Arrays.asList("homework", "graded"));
        assertTrue(predicate.test(new TaskBuilder().withTags("homework", "graded").build()));

        // Mixed-case keywords
        predicate = new TagTaskContainsKeywordsPredicate(Arrays.asList("hOmEwOrK", "gRaDeD"));
        assertTrue(predicate.test(new TaskBuilder().withTags("homework", "graded").build()));
    }

    @Test
    public void test_tagsDoesNotContainKeywords_returnsFalse() {
        // Non-matching keyword
        TagTaskContainsKeywordsPredicate predicate = new TagTaskContainsKeywordsPredicate(Arrays.asList("Graded"));
        assertFalse(predicate.test(new TaskBuilder().withTags("homework").build()));

        // Only one matching keyword, fails because of AND search
        predicate = new TagTaskContainsKeywordsPredicate(Arrays.asList("homework", "Graded"));
        assertFalse(predicate.test(new TaskBuilder().withTags("homework", "Urgent").build()));

        // Keywords match description but not tags
        predicate = new TagTaskContainsKeywordsPredicate(Arrays.asList("Homework", "Urgent"));
        assertFalse(predicate.test(new TaskBuilder().withTaskDescription("Homework Urgent").build()));
    }
}
