package seedu.address.model.game;

import java.util.function.Predicate;

import seedu.address.model.friend.Friend;

// TODO: remove duplicate

/**
 * Tests that a {@code gameId} is contained within a friend's {@Code GameFriendLinks}
 */
public class GameFriendLinksContainsGameIdPredicate implements Predicate<Game> {
    private final Friend friend;

    public GameFriendLinksContainsGameIdPredicate(Friend friend) {
        this.friend = friend;
    }

    public Friend getFriend() {
        return friend;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GameFriendLinksContainsGameIdPredicate // instanceof handles nulls
                && friend.equals(((GameFriendLinksContainsGameIdPredicate) other).friend)); // state check
    }

    @Override
    public boolean test(Game game) {
        return friend.hasGameAssociation(game);
    }
}
