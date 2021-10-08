package seedu.address.model.friend;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.FriendBuilder;

public class FriendIdMatchesKeywordPredicateTest {

    @Test
    public void equals() {
        String firstPredicateKeyword = "first";
        String secondPredicateKeyword = "second";

        FriendIdMatchesKeywordPredicate firstPredicate =
                new FriendIdMatchesKeywordPredicate(firstPredicateKeyword);
        FriendIdMatchesKeywordPredicate secondPredicate =
                new FriendIdMatchesKeywordPredicate(secondPredicateKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        FriendIdMatchesKeywordPredicate firstPredicateCopy =
                new FriendIdMatchesKeywordPredicate(firstPredicateKeyword);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_friendIdMatchesKeyword_returnsTrue() {
        FriendIdMatchesKeywordPredicate predicate =
                new FriendIdMatchesKeywordPredicate("87652533");
        assertTrue(predicate.test(new FriendBuilder().withFriendName("Alice Bob").withFriendId("87652533").build()));
    }

    @Test
    public void test_friendIdDoesNotMatchKeyword_returnsFalse() {
        // empty keyword
        FriendIdMatchesKeywordPredicate predicate = new FriendIdMatchesKeywordPredicate("");
        assertFalse(predicate.test(new FriendBuilder().withFriendName("Alice Bob").withFriendId("87652533").build()));

        // Non-matching keyword
        predicate = new FriendIdMatchesKeywordPredicate("notFriendId");
        assertFalse(predicate.test(new FriendBuilder().withFriendName("Alice Bob").withFriendId("87652533").build()));

        // Keyword matches name, but not friendId
        predicate = new FriendIdMatchesKeywordPredicate("Alice Bob");
        assertFalse(predicate.test(new FriendBuilder().withFriendName("Alice Bob").withFriendId("87652533").build()));
    }
}
