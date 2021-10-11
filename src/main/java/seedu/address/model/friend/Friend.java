package seedu.address.model.friend;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.friend.gamefriendlink.GameFriendLink;
import seedu.address.model.game.GameId;

/**
 * Represents a Friend in the gitGud friend's list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Friend {

    // Identity fields
    // used to uniquely identify each Friend.
    private FriendId friendId;
    private FriendName friendName;

    // Data fields
    private final Set<GameFriendLink> games = new HashSet<>();

    /**
     * Constructs a {@code Friend}.
     * Every field must be present and not null.
     *
     * @param friendId a valid friend id.
     * @param friendName a valid friend name.
     * @param games a set of game-friend links of this friend.
     */
    public Friend(FriendId friendId, FriendName friendName, Set<GameFriendLink> games) {
        requireAllNonNull(friendId, friendName, games);
        this.friendId = friendId;
        this.friendName = friendName;
        this.games.addAll(games);
    }

    /**
     * Overloaded constructor using only friendId and friendName.
     *
     * @param friendId Unique id of friend.
     * @param friendName Name of friend.
     */
    public Friend(FriendId friendId, FriendName friendName) {
        requireAllNonNull(friendId, friendName);
        this.friendId = friendId;
        this.friendName = friendName;
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
    public Set<GameFriendLink> getGames() {
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

        Set<GameFriendLink> gameSet = getGames();
        if (!gameSet.isEmpty()) {
            builder.append("; Games: ");
            gameSet.forEach(builder::append);
        }
        return builder.toString();
    }
}
