package seedu.address.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.friend.Friend;
import seedu.address.model.friend.FriendId;
import seedu.address.model.game.Game;
import seedu.address.model.game.GameId;
import seedu.address.model.gamefriendlink.GameFriendLink;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Friend> PREDICATE_SHOW_ALL_FRIENDS = unused -> true;
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Game> PREDICATE_SHOW_ALL_GAMES = unused -> true;

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

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

    //=========== FriendsBook ==================================================================================

    /**
     * Returns the FriendsBook
     */
    ReadOnlyFriendsList getFriendsList();

    /**
     * Replaces friends book data with the data in {@code readOnlyFriendsList}.
     */
    void setFriendsList(ReadOnlyFriendsList readOnlyFriendsList);

    /**
     * Returns true if a friend with the same identity as {@code friend} exists in the friends book.
     */
    boolean hasFriend(Friend friend);

    /**
     * Returns true if a friend with the same friendId as {@code friendId} exists in the friends book.
     */
    boolean hasFriendWithId(FriendId friendId);

    /**
     * Deletes the given friend.
     * The friend must exist in the friends book.
     */
    void deleteFriend(FriendId targetId);

    /**
     * Adds the given friend.
     * {@code friend} must not already exist in the friends book.
     */
    void addFriend(Friend friend);

    /**
     * Returns the friend with the given friendId.
     * {@code friend} with the friendId must already exist in the friends book.
     */
    Friend getFriend(FriendId friendId);

    /**
     * Replaces the given friend {@code target} with {@code editedFriend}.
     * {@code target} must exist in the friends book.
     * The friend identity of {@code editedFriend} must not be the same as another existing friend in the friends book.
     */
    void setFriend(Friend target, Friend editedFriend);

    /**
     * Returns an unmodifiable view of the filtered friend list
     */
    ObservableList<Friend> getFilteredAndSortedFriendsList();

    /**
     * Updates the filter of the filtered friend list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredAndSortedFriendsList(Predicate<Friend> predicate);

    /**
     * Updates the filter and comparator of the filtered and sorted friends list to filter by the given
     * {@code predicate} and sorted by the given {@code comparator}.
     * of the s
     *
     * @throws NullPointerException if {@code predicate} or {@code comparator} is null.
     */
    void updateFilteredAndSortedFriendsList(Predicate<Friend> predicate, Comparator<Friend> comparator);

    /**
     * Links the friend {@code toLink} with the game in {@code gameFriendLink}.
     */
    void linkFriend(Friend toLink, GameFriendLink gameFriendLink);

    /**
     * Removes the {@code GameFriendLink} from all friends that are associated with @code game}.
     */
    void removeLinkAllFriends(Game game);

    /**
     * Unlinks the friend {@code toUnlink} with the Game {@code game}.
     */
    void unlinkFriend(Friend toUnlink, Game game);

    //=========== GamesBook ==================================================================================

    /**
     * Returns the GamesBook
     */
    ReadOnlyGamesList getGamesList();

    /**
     * Replaces games book data with the data in {@code readOnlyGamesList}.
     */
    void setGamesList(ReadOnlyGamesList readOnlyGamesList);

    /**
     * Returns true if a game with the same identity as {@code game} exists in the games book.
     */
    boolean hasGame(Game game);

    /**
     * Returns true if a game with the same gameId as {@code gameId} exists in the games book.
     */
    boolean hasGameWithId(GameId gameId);

    /**
     * Deletes the given game based on the {@code targetId}.
     * The game must exist in the games book.
     */
    void deleteGame(GameId targetId);

    /**
     * Adds the given game.
     * {@code game} must not already exist in the games book.
     */
    void addGame(Game game);

    /**
     * Returns the game with the given gameId.
     * {@code game} with the gameId must already exist in the games book.
     */
    Game getGame(GameId gameId);

    /**
     * Returns an unmodifiable view of the filtered game list
     */
    ObservableList<Game> getFilteredGamesList();

    /**
     * Updates the filter of the filtered game list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredGamesList(Predicate<Game> predicate);

}
