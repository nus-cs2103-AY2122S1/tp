package seedu.smartnus.model.note;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.smartnus.testutil.NoteBuilder;

public class NoteNameContainsKeywordPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        NoteNameContainsKeywordsPredicate firstPredicate =
                new NoteNameContainsKeywordsPredicate(firstPredicateKeywordList);
        NoteNameContainsKeywordsPredicate secondPredicate =
                new NoteNameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        NoteNameContainsKeywordsPredicate firstPredicateCopy =
                new NoteNameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertEquals(firstPredicate, firstPredicateCopy);

        // different types -> returns false
        assertNotEquals(1, firstPredicate);

        // null -> returns false
        assertNotEquals(null, firstPredicate);

        // different question -> returns false
        assertNotEquals(firstPredicate, secondPredicate);
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        NoteNameContainsKeywordsPredicate predicate =
                new NoteNameContainsKeywordsPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new NoteBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new NoteNameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new NoteBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new NoteNameContainsKeywordsPredicate(List.of("Carol"));
        assertTrue(predicate.test(new NoteBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new NoteNameContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new NoteBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NoteNameContainsKeywordsPredicate predicate = new NoteNameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new NoteBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new NoteNameContainsKeywordsPredicate(List.of("Carol"));
        assertFalse(predicate.test(new NoteBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void hashCode_test() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        NoteNameContainsKeywordsPredicate firstPredicate =
                new NoteNameContainsKeywordsPredicate(firstPredicateKeywordList);
        NoteNameContainsKeywordsPredicate secondPredicate =
                new NoteNameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertEquals(firstPredicate.hashCode(), firstPredicate.hashCode());

        // same values -> returns true
        NoteNameContainsKeywordsPredicate firstPredicateCopy =
                new NoteNameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertEquals(firstPredicate.hashCode(), firstPredicateCopy.hashCode());

        // different types -> returns false
        assertNotEquals(1, firstPredicate.hashCode());

        // null -> returns false
        assertNotEquals((Double) null, firstPredicate.hashCode());

        // different question -> returns false
        assertNotEquals(firstPredicate.hashCode(), secondPredicate.hashCode());
    }
}
