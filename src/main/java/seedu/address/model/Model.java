package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.friend.Friend;
import seedu.address.model.friend.FriendId;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Friend> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code readOnlyFriendsList}.
     */
    void setFriendsList(ReadOnlyFriendsList readOnlyFriendsList);

    /** Returns the AddressBook */
    ReadOnlyFriendsList getFriendsList();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasFriend(Friend friend);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deleteFriend(FriendId targetId);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addFriend(Friend friend);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setFriend(Friend target, Friend editedFriend);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Friend> getFilteredFriendsList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredFriendsList(Predicate<Friend> predicate);

    boolean hasFriendId(FriendId idToFind);
}
