package seedu.address.model.friend;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.FriendBuilder;

public class FriendIdMatchesKeywordPredicateTest {

    @Test
    public void equals() {
        String firstKeyword = "first";
        String secondKeyword = "second";

        FriendIdMatchesKeywordPredicate firstPredicate =
                new FriendIdMatchesKeywordPredicate(firstKeyword);
        FriendIdMatchesKeywordPredicate secondPredicate =
                new FriendIdMatchesKeywordPredicate(secondKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        FriendIdMatchesKeywordPredicate firstPredicateCopy =
                new FriendIdMatchesKeywordPredicate(firstKeyword);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_idMatchesKeywords_returnsTrue() {
        // Same case keyword
        FriendIdMatchesKeywordPredicate predicate =
                new FriendIdMatchesKeywordPredicate("AB");
        assertTrue(predicate.test(new FriendBuilder().withFriendId(FriendBuilder.DEFAULT_FRIEND_ID).build()));

        // Mixed-case keyword
        predicate = new FriendIdMatchesKeywordPredicate("aB");
        assertTrue(predicate.test(new FriendBuilder().withFriendId(FriendBuilder.DEFAULT_FRIEND_ID).build()));
    }

    @Test
    public void test_idDoesNotMatchKeywords_returnsFalse() {
        // Non-matching keyword
        FriendIdMatchesKeywordPredicate predicate = new FriendIdMatchesKeywordPredicate("Carol");
        assertFalse(predicate.test(new FriendBuilder().withFriendId(FriendBuilder.DEFAULT_FRIEND_ID).build()));

        // Keywords match name, but does not match id
        predicate =
                new FriendIdMatchesKeywordPredicate("Alice");
        assertFalse(predicate.test(new FriendBuilder().withFriendId("Ally").withFriendName("Alice")
                .build()));
    }
}

