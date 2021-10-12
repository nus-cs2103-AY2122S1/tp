package seedu.address.model;

import java.nio.file.Path;
import java.util.HashSet;
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

    //=========== FriendsBook ==================================================================================

    /**
     * Replaces friends book data with the data in {@code readOnlyFriendsList}.
     */
    void setFriendsList(ReadOnlyFriendsList readOnlyFriendsList);

    /**
     * Returns the FriendsBook
     */
    ReadOnlyFriendsList getFriendsList();

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
     * Replaces the given friend {@code target} with {@code editedFriend}.
     * {@code target} must exist in the friends book.
     * The friend identity of {@code editedFriend} must not be the same as another existing friend in the friends book.
     */
    void setFriend(Friend target, Friend editedFriend);

    /**
     * Returns an unmodifiable view of the filtered friend list
     */
    ObservableList<Friend> getFilteredFriendsList();

    /**
     * Updates the filter of the filtered friend list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredFriendsList(Predicate<Friend> predicate);

    /**
     * Links the friend {@code toLink} with the games in the HashMap.
     * The key in the HashMap represents a {@code GAME_NAME}, while the value represents
     * the {@code IN_GAME_USERNAME} for that game.
     */
    void linkFriend(Friend toLink, HashSet<GameFriendLink> gameFriendLinks);

    boolean hasFriendId(FriendId idToFind);

    //=========== GamesBook ==================================================================================

    /**
     * Replaces games book data with the data in {@code readOnlyGamesList}.
     */
    void setGamesList(ReadOnlyGamesList readOnlyGamesList);

    /**
     * Returns the GamesBook
     */
    ReadOnlyGamesList getGamesList();

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
     * Replaces the given game {@code target} with {@code editedGame}.
     * {@code target} must exist in the games book.
     * The game identity of {@code editedGame} must not be the same as another existing game in the games book.
     */
    void setGame(Game target, Game editedGame);

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

    boolean hasGameId(GameId idToFind);
}
