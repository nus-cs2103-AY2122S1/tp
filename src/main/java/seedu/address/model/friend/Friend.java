package seedu.address.model.friend;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.gamefriendlink.GameFriendLink;

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
    private final Set<GameFriendLink> gameFriendLinks = new HashSet<>();

    /**
     * Constructs a {@code Friend}.
     * Every field must be present and not null.
     *
     * @param friendId        a valid friend id.
     * @param friendName      a valid friend name.
     * @param gameFriendLinks a set of game-friend links of this friend.
     */
    public Friend(FriendId friendId, FriendName friendName, Set<GameFriendLink> gameFriendLinks) {
        requireAllNonNull(friendId, gameFriendLinks);
        this.friendId = friendId;
        this.friendName = friendName == null ? FriendName.DEFAULT_FRIEND_NAME : friendName;
        this.gameFriendLinks.addAll(gameFriendLinks);
    }

    /**
     * Overloaded constructor using only friendId and friendName.
     *
     * @param friendId   Unique id of friend.
     * @param friendName Name of friend.
     */
    public Friend(FriendId friendId, FriendName friendName) {
        requireAllNonNull(friendId);
        this.friendId = friendId;
        this.friendName = friendName == null ? FriendName.DEFAULT_FRIEND_NAME : friendName;
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
    public Set<GameFriendLink> getGameFriendLinks() {
        return Collections.unmodifiableSet(gameFriendLinks);
    }

    /**
     * Returns true if both friends have same friendId.
     * @return
     */
    public boolean isSameFriendId(Friend friendId) {
        return this.friendId == friendId.getFriendId();
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
                && otherFriend.getGameFriendLinks().equals(getGameFriendLinks())
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
        builder.append("Friend ID: ")
                .append(getFriendId())
                .append("; Name: ")
                .append(getName());

        Set<GameFriendLink> gameSet = getGameFriendLinks();
        if (!gameSet.isEmpty()) {
            builder.append("; Games: ");
            gameSet.forEach(builder::append);
        }
        return builder.toString();
    }

}
