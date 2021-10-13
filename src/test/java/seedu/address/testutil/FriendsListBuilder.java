package seedu.address.testutil;

import seedu.address.model.FriendsList;
import seedu.address.model.friend.Friend;

/**
 * A utility class to help with building FriendsList objects.
 * Example usage: <br>
 *     {@code FriendsList fl = new FriendsList().withFriend(new Friend("friendId", "friendName")).build();}
 */
public class FriendsListBuilder {

    private FriendsList friendsList;

    public FriendsListBuilder() {
        friendsList = new FriendsList();
    }

    public FriendsListBuilder(FriendsList friendsList) {
        this.friendsList = friendsList;
    }

    /**
     * Adds a new {@code Friend} to the {@code FriendsList} that we are building.
     */
    public FriendsListBuilder withFriend(Friend friend) {
        friendsList.addFriend(friend);
        return this;
    }

    public FriendsList build() {
        return friendsList;
    }
}
