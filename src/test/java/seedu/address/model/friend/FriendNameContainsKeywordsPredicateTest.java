package seedu.address.model.friend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.FriendBuilder;

public class FriendNameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        FriendNameContainsKeywordsPredicate firstPredicate =
                new FriendNameContainsKeywordsPredicate(firstPredicateKeywordList);
        FriendNameContainsKeywordsPredicate secondPredicate =
                new FriendNameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        FriendNameContainsKeywordsPredicate firstPredicateCopy =
                new FriendNameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertEquals(firstPredicateCopy, firstPredicate);

        // different types -> returns false
        assertNotEquals(1, firstPredicate);

        // null -> returns false
        assertNotEquals(null, firstPredicate);

        // different person -> returns false
        assertNotEquals(secondPredicate, firstPredicate);
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        FriendNameContainsKeywordsPredicate predicate =
                new FriendNameContainsKeywordsPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new FriendBuilder().withFriendName("Alice Bob").build()));

        // Multiple keywords
        predicate = new FriendNameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new FriendBuilder().withFriendName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new FriendNameContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new FriendBuilder().withFriendName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new FriendNameContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new FriendBuilder().withFriendName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        FriendNameContainsKeywordsPredicate predicate =
                new FriendNameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new FriendBuilder().withFriendName("Alice").build()));

        // Non-matching keyword
        predicate = new FriendNameContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new FriendBuilder().withFriendName("Alice Bob").build()));

        // Keywords match phone, email and address, but does not match name
        predicate =
                new FriendNameContainsKeywordsPredicate(Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new FriendBuilder().withFriendName("Alice").withFriendId("12345")
                .build()));
    }
}
