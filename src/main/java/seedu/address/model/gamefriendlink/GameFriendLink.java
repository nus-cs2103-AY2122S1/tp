package seedu.address.model.gamefriendlink;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.friend.FriendId;
import seedu.address.model.game.GameId;

/**
 * Represents an association between a {@code Friend} and a {@code Game} that are present in the gitGud database.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class GameFriendLink {

    private final GameId gameId;
    private final FriendId friendId;
    private final UserName userName;

    /**
     * Constructs a {@code GameFriendLink}.
     * Every field must be present and not null.
     *
     * @param gameId   the valid game id of an existing {@code Game}.
     * @param friendId the valid friend id of an existing {@code Friend}.
     * @param userName a valid username.
     */
    public GameFriendLink(GameId gameId, FriendId friendId, UserName userName) {
        requireAllNonNull(gameId, friendId, userName);
        this.gameId = gameId;
        this.friendId = friendId;
        this.userName = userName;
    }

    public GameId getGameId() {
        return gameId;
    }

    public FriendId getFriendId() {
        return friendId;
    }

    public UserName getUserName() {
        return userName;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof GameFriendLink)) {
            return false;
        }

        GameFriendLink otherLink = (GameFriendLink) other;
        return otherLink.getGameId().equals(getGameId())
                && otherLink.getFriendId().equals(getFriendId())
                && otherLink.getUserName().equals(getUserName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId, friendId, userName);
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Game ID: ")
                .append(getGameId())
                .append("Username: ")
                .append(getUserName());

        return builder.toString();
    }
}
