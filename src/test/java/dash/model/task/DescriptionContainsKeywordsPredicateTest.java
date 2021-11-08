package dash.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import dash.testutil.TaskBuilder;

public class DescriptionContainsKeywordsPredicateTest {
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        DescriptionContainsKeywordsPredicate firstPredicate = new DescriptionContainsKeywordsPredicate(
                firstPredicateKeywordList);
        DescriptionContainsKeywordsPredicate secondPredicate = new DescriptionContainsKeywordsPredicate(
                secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        DescriptionContainsKeywordsPredicate firstPredicateCopy = new DescriptionContainsKeywordsPredicate(
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
        DescriptionContainsKeywordsPredicate predicate = new DescriptionContainsKeywordsPredicate(
                Collections.singletonList("Lectures"));
        assertTrue(predicate.test(new TaskBuilder().withTaskDescription("ST2334 Lectures").build()));

        // Multiple keywords
        predicate = new DescriptionContainsKeywordsPredicate(Arrays.asList("ST2334", "Lectures"));
        assertTrue(predicate.test(new TaskBuilder().withTaskDescription("ST2334 Lectures").build()));

        // Mixed-case keywords
        predicate = new DescriptionContainsKeywordsPredicate(Arrays.asList("ST2334", "Lectures"));
        assertTrue(predicate.test(new TaskBuilder().withTaskDescription("st2334 lEcTuReS").build()));
    }

    @Test
    public void test_descriptionDoesNotContainKeywords_returnsFalse() {
        // Non-matching keyword
        DescriptionContainsKeywordsPredicate predicate = new DescriptionContainsKeywordsPredicate(Arrays.asList(
                "Assignment"));
        assertFalse(predicate.test(new TaskBuilder().withTaskDescription("ST2334 Quiz").build()));

        // Only one matching keyword, fails because of AND search
        predicate = new DescriptionContainsKeywordsPredicate(Arrays.asList("ST2334", "Lectures"));
        assertFalse(predicate.test(new TaskBuilder().withTaskDescription("CS2103T Lectures").build()));

        // Keywords match tags, but does not match description
        predicate = new DescriptionContainsKeywordsPredicate(Arrays.asList("st2334", "graded"));
        assertFalse(predicate.test(new TaskBuilder().withTaskDescription("Probability Quiz").withTags(
                "st2334", "graded").build()));
    }
}
