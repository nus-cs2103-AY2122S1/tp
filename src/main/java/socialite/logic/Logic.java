package socialite.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import socialite.commons.core.GuiSettings;
import socialite.logic.commands.CommandResult;
import socialite.logic.commands.exceptions.CommandException;
import socialite.logic.parser.exceptions.ParseException;
import socialite.model.Model;
import socialite.model.ReadOnlyCommandHistory;
import socialite.model.ReadOnlyContactList;
import socialite.model.person.Person;

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
     * Returns the ContactList.
     *
     * @see Model#getContactList()
     */
    ReadOnlyContactList getContactList();

    /** Returns an unmodifiable view of an entire list of persons */
    ObservableList<Person> getFullPersonList();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Returns the user prefs' contact list file path.
     */
    Path getContactListFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    ReadOnlyCommandHistory getCommandHistory();
}
