package seedu.address.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyFriendsList;
import seedu.address.model.ReadOnlyGamesList;
import seedu.address.model.friend.Friend;
import seedu.address.model.game.Game;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     *
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException   If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the FriendsBook.
     *
     * @see seedu.address.model.Model#getFriendsList()
     */
    ReadOnlyFriendsList getFriendsBook();

    /**
     * Returns the GamesBook.
     *
     * @see seedu.address.model.Model#getGamesList()
     */
    ReadOnlyGamesList getGamesBook();

    /**
     * Returns an unmodifiable view of the filtered list of friends
     */
    ObservableList<Friend> getFilteredFriendsList();

    /**
     * Returns an unmodifiable view of the filtered list of games
     */
    ObservableList<Game> getFilteredGamesList();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
