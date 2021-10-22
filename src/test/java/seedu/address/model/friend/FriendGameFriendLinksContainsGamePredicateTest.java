package seedu.address.model.friend;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.util.SampleDataUtil.getSampleFriends;
import static seedu.address.model.util.SampleDataUtil.getSampleGames;

import org.junit.jupiter.api.Test;

import seedu.address.model.game.Game;

public class FriendGameFriendLinksContainsGamePredicateTest {
    @Test
    void getGame() {
        Game game = getSampleGames()[0];
        FriendGameFriendLinksContainsGamePredicate predicate =
                new FriendGameFriendLinksContainsGamePredicate(game);
        assertTrue(predicate.getGame().equals(game));
    }

    @Test
    void equals() {
        Game[] gameArr = getSampleGames();
        FriendGameFriendLinksContainsGamePredicate firstPredicate =
                new FriendGameFriendLinksContainsGamePredicate(gameArr[0]);
        FriendGameFriendLinksContainsGamePredicate secondPredicate =
                new FriendGameFriendLinksContainsGamePredicate(gameArr[1]);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        FriendGameFriendLinksContainsGamePredicate firstPredicateCopy =
                new FriendGameFriendLinksContainsGamePredicate(gameArr[0]);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    void test_gflContainGame_returnsTrue() {
        Game game = getSampleGames()[1];
        Friend friend = getSampleFriends()[0];

        // friend's gfl contains game -> true
        FriendGameFriendLinksContainsGamePredicate predicate =
                new FriendGameFriendLinksContainsGamePredicate(game);
        assertTrue(predicate.test(friend));
    }

    @Test
    void test_gflDoesNotContainGame_returnsFalse() {
        Game game = getSampleGames()[2];
        Friend friend = getSampleFriends()[0];

        // friend's gfl does not contain game -> false
        FriendGameFriendLinksContainsGamePredicate predicate =
                new FriendGameFriendLinksContainsGamePredicate(game);
        assertFalse(predicate.test(friend));
    }
}
