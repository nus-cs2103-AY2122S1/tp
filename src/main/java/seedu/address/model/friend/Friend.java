package seedu.address.model.friend;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.game.Game;

/**
 * Represents a Friend in the gitGud friend's list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Friend {

    // Identity fields
    // used to uniquely identify each Friend.
    private final FriendId friendId;
    private final FriendName friendName;

    // Data fields
    private final Set<Game> games = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Friend(FriendId friendId, FriendName friendName, Set<Game> games) {
        requireAllNonNull(friendId, friendName, games);
        this.friendId = friendId;
        this.friendName = friendName;
        this.games.addAll(games);
    }

    public FriendId getFriendId() {
        return friendId;
    }
    public FriendName getName() {
        return friendName;
    }

    /**
     * Returns an immutable game set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Game> getGames() {
        return Collections.unmodifiableSet(games);
    }

    /**
     * Returns true if both friends have the same friendId, name and games.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Friend)) {
            return false;
        }

        Friend otherFriend = (Friend) other;
        return otherFriend.getFriendId().equals(getFriendId())
                && otherFriend.getGames().equals(getGames())
                && otherFriend.getName().equals(getName());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(friendId);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Friend ID: ")
                .append(getFriendId())
                .append("; Name: ")
                .append(getName());

        Set<Game> gameSet = getGames();
        if (!gameSet.isEmpty()) {
            builder.append("; Games: ");
            gameSet.forEach(builder::append);
        }
        return builder.toString();
    }

}
