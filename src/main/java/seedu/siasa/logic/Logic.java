package seedu.siasa.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.siasa.commons.core.GuiSettings;
import seedu.siasa.logic.commands.CommandResult;
import seedu.siasa.logic.commands.exceptions.CommandException;
import seedu.siasa.logic.parser.exceptions.ParseException;
import seedu.siasa.model.ReadOnlySiasa;
import seedu.siasa.model.contact.Contact;
import seedu.siasa.model.policy.Policy;

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
     * Returns the AddressBook.
     *
     * @see seedu.siasa.model.Model#getSiasa()
     */
    ReadOnlySiasa getAddressBook();

    /** Returns an unmodifiable view of the filtered list of contacts */
    ObservableList<Contact> getFilteredContactList();

    /** Returns an unmodifiable view of the filtered list of policies */
    ObservableList<Policy> getFilteredPolicyList();

    /**
     * Returns the user prefs' SIASA file path.
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
