package seedu.address.model.friend;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.friend.exceptions.DuplicateFriendException;
import seedu.address.model.friend.exceptions.FriendNotFoundException;
import seedu.address.model.game.Game;
import seedu.address.model.game.GameId;
import seedu.address.model.gamefriendlink.GameFriendLink;

/**
 * A list of friends that enforces uniqueness between its elements and does not allow nulls.
 * <p>
 * A friends is considered unique by comparing using {@code Friend#equals(Object)}. As such, adding and updating
 * of friends uses Friend#equals(Object) for equality so as to ensure that the friend being added or updated
 * is unique in terms of identity in the UniqueFriendsList. The removal of a friend also uses
 * Friend#equals(Object) so as to ensure that the friend with exactly the same friendId will be removed.
 * <p>
 * Supports a minimal set of list operations.
 *
 * @see Friend#equals(Object)
 */
public class UniqueFriendsList implements Iterable<Friend> {

    private final ObservableList<Friend> internalList = FXCollections.observableArrayList();
    private final ObservableList<Friend> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent friend as the given argument.
     */
    public boolean contains(Friend toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Returns true if the list contains a friend with idToCheck.
     */
    public boolean containsId(FriendId idToCheck) {
        requireNonNull(idToCheck);
        return internalList.stream().anyMatch(friend -> friend.getFriendId().equals(idToCheck));
    }

    /**
     * Adds a friend to the list.
     * The friend must not already exist in the list.
     */
    public void add(Friend friendToAdd) {
        requireNonNull(friendToAdd);
        if (containsId(friendToAdd.getFriendId())) {
            throw new DuplicateFriendException();
        }
        internalList.add(friendToAdd);
    }

    /**
     * Gets a friend from the list.
     * The friend with the friendId must already exist in the list.
     */
    public Friend getFriend(FriendId friendId) {
        requireNonNull(friendId);
        if (!containsId(friendId)) {
            throw new FriendNotFoundException();
        }
        return internalList.stream().filter(friend -> friend.getFriendId().equals(friendId)).iterator().next();
    }

    /**
     * Replaces the friend {@code friendToEdit} in the list with {@code editedFriend}.
     * {@code friendToEdit} must exist in the list.
     * The friend identity of {@code editedFriend} must not be the same as another existing friend in the list.
     */
    public void setFriend(Friend friendToEdit, Friend editedFriend) {
        requireAllNonNull(friendToEdit, editedFriend);

        int index = internalList.indexOf(friendToEdit);
        if (index == -1) {
            throw new FriendNotFoundException();
        }

        // guard against if editedFriend already exists in the UniqueFriendsList.
        if (!friendToEdit.isSameFriendId(editedFriend) && containsId(editedFriend.getFriendId())) {
            throw new DuplicateFriendException();
        }

        internalList.set(index, editedFriend);
    }

    /**
     * Removes the equivalent friend from the list.
     * The friend must exist in the list.
     */
    public void remove(Friend toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new FriendNotFoundException();
        }
    }

    /**
     * Links a friend with the game he plays.
     * The friend must exist in the list.
     */
    public void link(Friend toLink, GameFriendLink gameFriendLink) {
        requireAllNonNull(toLink, gameFriendLink);

        Map<GameId, GameFriendLink> currentLinks = new HashMap<>(toLink.getGameFriendLinks());
        Friend friendToEdit = new Friend(toLink.getFriendId(), toLink.getFriendName(),
                currentLinks, toLink.getSchedule());
        friendToEdit.link(gameFriendLink);
        this.setFriend(toLink, friendToEdit);
    }

    /**
     * Removes the {@code GameFriendLink} from all friends that are associated with {@code game}.
     */
    public void removeLinkAllFriends(Game game) {
        requireNonNull(game);

        internalList.forEach(friend -> {
            unlink(friend, game);
        });
    }

    /**
     * Unlinks a Friend {@code friendToUnlink} with game {@code gameToUnlink}.
     * The friend must exist in the list.
     */
    public void unlink(Friend friendToUnlink, Game game) {
        requireAllNonNull(friendToUnlink, game);

        Map<GameId, GameFriendLink> currentLinks = new HashMap<>(friendToUnlink.getGameFriendLinks());
        Friend friendToEdit = new Friend(friendToUnlink.getFriendId(), friendToUnlink.getFriendName(),
                currentLinks, friendToUnlink.getSchedule());
        friendToEdit.unlink(game);
        this.setFriend(friendToUnlink, friendToEdit);
    }

    public void setFriends(UniqueFriendsList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code friends}.
     * {@code friends} must not contain duplicate friends.
     */
    public void setFriends(List<Friend> friends) {
        requireAllNonNull(friends);
        if (!friendsAreUnique(friends)) {
            throw new DuplicateFriendException();
        }

        internalList.setAll(friends);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Friend> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Friend> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueFriendsList // instanceof handles nulls
                && internalList.equals(((UniqueFriendsList) other).internalList));
    }

    /**
     * Returns true if {@code friends} contains only unique friends.
     */
    private boolean friendsAreUnique(List<Friend> friends) {
        for (int i = 0; i < friends.size() - 1; i++) {
            for (int j = i + 1; j < friends.size(); j++) {
                if (friends.get(i).equals(friends.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
