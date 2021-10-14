package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.friend.Friend;
import seedu.address.model.friend.FriendId;
import seedu.address.model.friend.FriendName;
import seedu.address.model.gamefriendlink.GameFriendLink;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class FriendBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_FRIEND_ID = "AB";

    private FriendName friendName;
    private FriendId friendId;
    private Set<GameFriendLink> games;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public FriendBuilder() {
        friendId = new FriendId(DEFAULT_FRIEND_ID);
        friendName = new FriendName(DEFAULT_NAME);
        games = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public FriendBuilder(Friend friendToCopy) {
        friendId = friendToCopy.getFriendId();
        friendName = friendToCopy.getName();
        games = new HashSet<>(friendToCopy.getGameFriendLinks());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public FriendBuilder withFriendName(String friendName) {
        this.friendName = new FriendName(friendName);
        return this;
    }

    /**
     * Parses the {@code games} into a {@code Set<GameFriendLink>} and set it to the {@code Person} that we are
     * building.
     */
    public FriendBuilder withGameFriendLinks(GameFriendLink... gameFriendLinks) {
        this.games = SampleDataUtil.getGameFriendLinkSet(gameFriendLinks);
        return this;
    }

    /**
     * Sets the {@code FriendId} of the {@code Friend} that we are building.
     */
    public FriendBuilder withFriendId(String friendId) {
        this.friendId = new FriendId(friendId);
        return this;
    }


    public Friend build() {
        return new Friend(friendId, friendName, games);
    }
}
