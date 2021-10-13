package seedu.friendbook.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.friendbook.commons.core.GuiSettings;
import seedu.friendbook.logic.commands.CommandResult;
import seedu.friendbook.logic.commands.exceptions.CommandException;
import seedu.friendbook.logic.parser.exceptions.ParseException;
import seedu.friendbook.model.ReadOnlyFriendBook;
import seedu.friendbook.model.person.Person;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the FriendBook.
     *
     * @see seedu.friendbook.model.Model#getFriendBook()
     */
    ReadOnlyFriendBook getFriendBook();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredPersonList();

    /** Returns an unmodifiable view of the filtered list of persons sorted by days to birthday */
    ObservableList<Person> getFilteredPersonListSortedByBirthday();

    /**
     * Returns the user prefs' friend book file path.
     */
    Path getFriendBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
