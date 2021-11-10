package seedu.address.logic;

import java.nio.file.Path;
import java.util.Hashtable;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyCountdown;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

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
     * @see seedu.address.model.Model#getAddressBook()
     */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns the Countdown.
     *
     * @see seedu.address.model.Model#getCountdown()
     */
    ReadOnlyCountdown getCountdown();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Returns a list of all unique tags present in the model
     */
    ObservableList<Tag> getUniqueTagList();

    /**
     * Returns a HashTable of all unique tags together with the number of contact
     * labelled with the tag respectively.
     */
    Hashtable<Tag, Integer> getUniqueTagTable();

    /**
     * Returns the user prefs' countdown file path.
     */
    Path getCountdownFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the total number of contacts in the address book
     */
    int size();
}
