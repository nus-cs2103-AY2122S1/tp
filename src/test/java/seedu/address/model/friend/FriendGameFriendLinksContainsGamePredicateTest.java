package seedu.address.model.friend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalFriends.ALICE;
import static seedu.address.testutil.TypicalGames.APEX_LEGENDS;
import static seedu.address.testutil.TypicalGames.CSGO;

import org.junit.jupiter.api.Test;

import seedu.address.model.game.Game;

public class FriendGameFriendLinksContainsGamePredicateTest {
    @Test
    void getGame() {
        Game game = CSGO;
        FriendGameFriendLinksContainsGamePredicate predicate =
                new FriendGameFriendLinksContainsGamePredicate(game);
        assertTrue(predicate.getGame().equals(game));
    }

    @Test
    void equals() {
        FriendGameFriendLinksContainsGamePredicate firstPredicate =
                new FriendGameFriendLinksContainsGamePredicate(CSGO);
        FriendGameFriendLinksContainsGamePredicate secondPredicate =
                new FriendGameFriendLinksContainsGamePredicate(APEX_LEGENDS);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // null -> returns false
        assertNotEquals(firstPredicate, null);

        // different types -> returns false
        assertNotEquals(firstPredicate, "String");

        // same values -> returns true
        FriendGameFriendLinksContainsGamePredicate firstPredicateCopy =
                new FriendGameFriendLinksContainsGamePredicate(firstPredicate.getGame());
        assertEquals(firstPredicate, firstPredicateCopy);

        // different person -> returns false
        assertNotEquals(firstPredicate, secondPredicate);
    }

    @Test
    void test_gflContainGame_returnsTrue() {
        Game game = CSGO;
        Friend friend = ALICE;

        // friend's gfl contains game -> true
        FriendGameFriendLinksContainsGamePredicate predicate =
                new FriendGameFriendLinksContainsGamePredicate(game);
        assertTrue(predicate.test(friend));
    }

    @Test
    void test_gflDoesNotContainGame_returnsFalse() {
        Game game = APEX_LEGENDS;
        Friend friend = ALICE;

        // friend's gfl does not contain game -> false
        FriendGameFriendLinksContainsGamePredicate predicate =
                new FriendGameFriendLinksContainsGamePredicate(game);
        assertFalse(predicate.test(friend));
    }
}
