package seedu.address.model.friend;

import java.util.function.Predicate;

import seedu.address.model.game.Game;


public class FriendGameFriendLinksContainsGamePredicate implements Predicate<Friend> {
    private final Game game;

    public FriendGameFriendLinksContainsGamePredicate(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FriendGameFriendLinksContainsGamePredicate // instanceof handles nulls
                && game.equals(((FriendGameFriendLinksContainsGamePredicate) other).game)); // state check
    }

    @Override
    public boolean test(Friend friend) {
        return friend.hasGameAssociation(game);
    }
}
