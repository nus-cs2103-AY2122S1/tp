package seedu.address.model.friend;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.FriendBuilder;

public class FriendIdContainsKeywordPredicateTest {

    @Test
    public void equals() {
        String firstKeyword = "first";
        String secondKeyword = "second";

        FriendIdContainsKeywordPredicate firstPredicate =
                new FriendIdContainsKeywordPredicate(firstKeyword);
        FriendIdContainsKeywordPredicate secondPredicate =
                new FriendIdContainsKeywordPredicate(secondKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        FriendIdContainsKeywordPredicate firstPredicateCopy =
                new FriendIdContainsKeywordPredicate(firstKeyword);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_idContainsKeywords_returnsTrue() {
        // No keyword (list all)
        FriendIdContainsKeywordPredicate predicate =
                new FriendIdContainsKeywordPredicate("");
        assertTrue(predicate.test(new FriendBuilder().withFriendId("Alice Bob").build()));

        // Matching partial keyword
        predicate = new FriendIdContainsKeywordPredicate("lice");
        assertTrue(predicate.test(new FriendBuilder().withFriendId("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new FriendIdContainsKeywordPredicate("aLIce");
        assertTrue(predicate.test(new FriendBuilder().withFriendId("Alice").build()));
    }

    @Test
    public void test_idDoesNotContainKeywords_returnsFalse() {
        // Non-matching keyword
        FriendIdContainsKeywordPredicate predicate = new FriendIdContainsKeywordPredicate("Carol");
        assertFalse(predicate.test(new FriendBuilder().withFriendId("Alice Bob").build()));

        // Keywords match name, but does not match id
        predicate =
                new FriendIdContainsKeywordPredicate("Alice");
        assertFalse(predicate.test(new FriendBuilder().withFriendId("Ally").withFriendName("Alice")
                .build()));
    }
}
