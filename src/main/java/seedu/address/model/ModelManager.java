package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.friend.Friend;
import seedu.address.model.friend.FriendId;

/**
 * Represents the in-memory model of the gitGud friends list data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final FriendsList friendsList;
    private final UserPrefs userPrefs;
    private final FilteredList<Friend> filteredFriends;

    /**
     * Initializes a ModelManager with the given friends list and userPrefs.
     */
    public ModelManager(ReadOnlyFriendsList readOnlyFriendsList, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(readOnlyFriendsList, userPrefs);

        logger.fine("Initializing with address book: " + readOnlyFriendsList + " and user prefs " + userPrefs);

        this.friendsList = new FriendsList(readOnlyFriendsList);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredFriends = new FilteredList<>(this.friendsList.getFriendsList());
    }

    public ModelManager() {
        this(new FriendsList(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setFriendsList(ReadOnlyFriendsList readOnlyFriendsList) {
        this.friendsList.resetData(readOnlyFriendsList);
    }

    @Override
    public ReadOnlyFriendsList getFriendsList() {
        return friendsList;
    }

    @Override
    public boolean hasFriend(Friend friend) {
        requireNonNull(friend);
        return friendsList.hasFriend(friend);
    }

    @Override
    public boolean hasFriendWithId(FriendId friendId) {
        requireNonNull(friendId);
        return friendsList.hasFriendWithId(friendId);
    }

    @Override
    public void deleteFriend(Friend target) {
        friendsList.removeFriend(target);
    }

    @Override
    public void addFriend(Friend friend) {
        friendsList.addFriend(friend);
        updateFilteredFriendsList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setFriend(Friend target, Friend editedFriend) {
        requireAllNonNull(target, editedFriend);

        friendsList.setFriend(target, editedFriend);
    }

    @Override
    public void linkFriend(Friend toLink, HashMap<String, String> games) {
        friendsList.linkFriend(toLink, games);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Friend> getFilteredFriendsList() {
        return filteredFriends;
    }

    @Override
    public void updateFilteredFriendsList(Predicate<Friend> predicate) {
        requireNonNull(predicate);
        filteredFriends.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return friendsList.equals(other.friendsList)
                && userPrefs.equals(other.userPrefs)
                && filteredFriends.equals(other.filteredFriends);
    }

}
