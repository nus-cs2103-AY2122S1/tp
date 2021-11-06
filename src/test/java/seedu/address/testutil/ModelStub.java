package seedu.address.testutil;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyFriendsList;
import seedu.address.model.ReadOnlyGamesList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.friend.Friend;
import seedu.address.model.friend.FriendId;
import seedu.address.model.game.Game;
import seedu.address.model.game.GameId;
import seedu.address.model.gamefriendlink.GameFriendLink;

/**
 * A default model stub that have all of the methods failing.
 */
public class ModelStub implements Model {
    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public GuiSettings getGuiSettings() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Path getAddressBookFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyFriendsList getFriendsList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setFriendsList(ReadOnlyFriendsList readOnlyFriendsList) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasFriend(Friend friend) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasFriendWithId(FriendId friendId) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteFriend(FriendId targetId) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addFriend(Friend friend) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Friend getFriend(FriendId friendId) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setFriend(Friend target, Friend editedFriend) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Friend> getFilteredAndSortedFriendsList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredAndSortedFriendsList(Predicate<Friend> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredAndSortedFriendsList(Predicate<Friend> predicate, Comparator<Friend> comparator) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void linkFriend(Friend toLink, GameFriendLink gameFriendLink) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void removeLinkAllFriends(Game game) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void unlinkFriend(Friend toUnlink, Game game) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyGamesList getGamesList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setGamesList(ReadOnlyGamesList readOnlyGamesList) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasGame(Game game) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasGameWithId(GameId gameId) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteGame(GameId targetId) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addGame(Game game) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Game getGame(GameId gameId) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Game> getFilteredGamesList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredGamesList(Predicate<Game> predicate) {
        throw new AssertionError("This method should not be called.");
    }
}
