package seedu.address.model.participant;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ParticipantBuilder;

public class EventParticipantNameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        ParticipantNameContainsKeywordsPredicate firstPredicate =
                new ParticipantNameContainsKeywordsPredicate(firstPredicateKeywordList);
        ParticipantNameContainsKeywordsPredicate secondPredicate =
                new ParticipantNameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ParticipantNameContainsKeywordsPredicate firstPredicateCopy =
                new ParticipantNameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        ParticipantNameContainsKeywordsPredicate predicate =
                new ParticipantNameContainsKeywordsPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new ParticipantBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new ParticipantNameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new ParticipantBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new ParticipantNameContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new ParticipantBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new ParticipantNameContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new ParticipantBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        ParticipantNameContainsKeywordsPredicate predicate =
                new ParticipantNameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new ParticipantBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new ParticipantNameContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new ParticipantBuilder().withName("Alice Bob").build()));

        // Keywords match phone, email and address, but does not match name
        predicate = new ParticipantNameContainsKeywordsPredicate(
                Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new ParticipantBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build()));
    }
}
