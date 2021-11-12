package seedu.address.model.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalFriends.ALICE;
import static seedu.address.testutil.TypicalFriends.BENSON;
import static seedu.address.testutil.TypicalGames.APEX_LEGENDS;
import static seedu.address.testutil.TypicalGames.CSGO;

import org.junit.jupiter.api.Test;

import seedu.address.model.friend.Friend;

class GameFriendLinksContainsGameIdPredicateTest {


    @Test
    void getFriend() {
        Friend friend = ALICE;
        GameFriendLinksContainsGameIdPredicate predicate =
                new GameFriendLinksContainsGameIdPredicate(friend);
        assertEquals(friend, predicate.getFriend());
    }

    @Test
    void equals() {
        Friend friend = BENSON;
        Friend otherFriend = ALICE;
        GameFriendLinksContainsGameIdPredicate firstPredicate =
                new GameFriendLinksContainsGameIdPredicate(friend);
        GameFriendLinksContainsGameIdPredicate secondPredicate =
                new GameFriendLinksContainsGameIdPredicate(otherFriend);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // null -> returns false
        assertNotEquals(firstPredicate, null);

        // different types -> returns false
        assertNotEquals(firstPredicate, "String");

        // same values -> returns true
        GameFriendLinksContainsGameIdPredicate firstPredicateCopy =
                new GameFriendLinksContainsGameIdPredicate(firstPredicate.getFriend());
        assertEquals(firstPredicate, firstPredicateCopy);

        // different person -> returns false
        assertNotEquals(firstPredicate, secondPredicate);
    }

    @Test
    void test_gflContainsGameId_returnsTrue() {
        Game game = CSGO;
        Friend friend = ALICE;

        // gfl contains game -> true
        GameFriendLinksContainsGameIdPredicate predicate =
                new GameFriendLinksContainsGameIdPredicate(friend);
        assertTrue(predicate.test(game));
    }

    @Test
    void test_gflContainsGameId_returnsFalse() {
        Game game = APEX_LEGENDS;
        Friend friend = ALICE;

        // gfl contains game -> false
        GameFriendLinksContainsGameIdPredicate predicate =
                new GameFriendLinksContainsGameIdPredicate(friend);
        assertFalse(predicate.test(game));
    }
}
