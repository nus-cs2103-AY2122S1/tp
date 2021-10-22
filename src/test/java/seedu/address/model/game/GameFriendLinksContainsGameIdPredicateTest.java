package seedu.address.model.game;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.util.SampleDataUtil.getSampleFriends;
import static seedu.address.model.util.SampleDataUtil.getSampleGames;

import org.junit.jupiter.api.Test;

import seedu.address.model.friend.Friend;

class GameFriendLinksContainsGameIdPredicateTest {


    @Test
    void getFriend() {
        Friend friend = getSampleFriends()[0];
        GameFriendLinksContainsGameIdPredicate predicate =
                new GameFriendLinksContainsGameIdPredicate(friend);
        assertTrue(predicate.getFriend().equals(friend));
    }

    @Test
    void equals() {
        Friend friend = getSampleFriends()[0];
        Friend otherFriend = getSampleFriends()[1];
        GameFriendLinksContainsGameIdPredicate firstPredicate =
                new GameFriendLinksContainsGameIdPredicate(friend);
        GameFriendLinksContainsGameIdPredicate secondPredicate =
                new GameFriendLinksContainsGameIdPredicate(otherFriend);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        GameFriendLinksContainsGameIdPredicate firstPredicateCopy =
                new GameFriendLinksContainsGameIdPredicate(friend);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));

    }

    @Test
    void test_gflContainsGameId_returnsTrue() {
        Game game = getSampleGames()[1];
        Friend friend = getSampleFriends()[0];

        // gfl contains game -> true
        GameFriendLinksContainsGameIdPredicate predicate =
                new GameFriendLinksContainsGameIdPredicate(friend);
        assertTrue(predicate.test(game));
    }

    @Test
    void test_gflContainsGameId_returnsFalse() {
        Game game = getSampleGames()[2];
        Friend friend = getSampleFriends()[0];

        // gfl contains game -> true
        GameFriendLinksContainsGameIdPredicate predicate =
                new GameFriendLinksContainsGameIdPredicate(friend);
        assertFalse(predicate.test(game));
    }
}
