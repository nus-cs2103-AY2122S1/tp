package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.friend.Friend;
import seedu.address.model.friend.FriendId;
import seedu.address.model.friend.UniqueFriendsList;
import seedu.address.model.game.Game;
import seedu.address.model.gamefriendlink.GameFriendLink;

/**
 * Wraps all friends list data at the gitGud level.
 * Duplicates are not allowed (by .equals comparison).
 */
public class FriendsList implements ReadOnlyFriendsList {

    private final UniqueFriendsList friends;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        friends = new UniqueFriendsList();
    }

    public FriendsList() {
    }

    /**
     * Creates an Friends List using the friends in {@code toBeCopied}
     */
    public FriendsList(ReadOnlyFriendsList toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the friends list with {@code friends}.
     * {@code friends} must not contain duplicate friends.
     */
    public void setFriends(List<Friend> friends) {
        this.friends.setFriends(friends);
    }

    /**
     * Resets the existing data of this {@code FriendsList} with {@code newData}.
     */
    public void resetData(ReadOnlyFriendsList newData) {
        requireNonNull(newData);

        setFriends(newData.getFriendsList());
    }

    //// person-level operations

    /**
     * Returns true if a friend with the same identity as {@code friend} exists in the friends list.
     */
    public boolean hasFriend(Friend friend) {
        requireNonNull(friend);
        return friends.contains(friend);
    }

    /**
     * Returns true if a friend with the same FriendId as (@code friendId} exists in the friends list.
     */
    public boolean hasFriendWithId(FriendId friendId) {
        requireNonNull(friendId);
        return friends.containsId(friendId);
    }

    /**
     * Adds a friend to the friends list.
     * The friend must not already exist in the friends list.
     */
    public void addFriend(Friend p) {
        friends.add(p);
    }

    /**
     * Gets a friend from the friends list.
     * The friend with the friendId must already exist in the friends list.
     */
    public Friend getFriend(FriendId friendId) {
        return friends.getFriend(friendId);
    }

    /**
     * Replaces the given friend {@code target} in the list with {@code editedFriend}.
     * {@code target} must exist in the friends list.
     * The friend identity of {@code editedFriend} must not be the same as another existing friend in the friends list.
     */
    public void setFriend(Friend target, Friend editedFriend) {
        requireNonNull(editedFriend);

        friends.setFriend(target, editedFriend);
    }

    /**
     * Associates the given friend {@code toLink} in the list with {@code gameFriendLink}
     * {@code toLink} must exist in the friends list.
     */
    public void linkFriend(Friend friendToLink, GameFriendLink gameFriendLink) {
        friends.link(friendToLink, gameFriendLink);
    }

    /**
     * Removes the {@code GameFriendLink} from all friends that are associated with {@code gameId}.
     */
    public void removeLinkAllFriends(Game game) {
        friends.removeLinkAllFriends(game);
    }

    /**
     * Removes the associated {@code GameFriendLink} between Friend {@code toUnlink} and Game {@code game}.
     */
    public void unlinkFriend(Friend friendToUnlink, Game game) {
        friends.unlink(friendToUnlink, game);
    }

    /**
     * Removes {@code key} from this {@code FriendsList}.
     * {@code key} must exist in the friends list.
     */
    public void removeFriend(Friend key) {
        friends.remove(key);
    }

    // util methods

    @Override
    public ObservableList<Friend> getFriendsList() {
        return friends.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FriendsList // instanceof handles nulls
                && friends.equals(((FriendsList) other).friends));
    }

    @Override
    public String toString() {
        return friends.asUnmodifiableObservableList().size() + " friends";
        // TODO: refine later
    }
}
