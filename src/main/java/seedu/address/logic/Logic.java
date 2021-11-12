package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;
import seedu.address.ui.PersonListPanel;

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
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the user profile.
     */
    Person getUserProfile();

    /**
     * Checks if the user profile is present.
     */
    boolean isProfilePresent();

    /**
     * Set the data in user profile.
     */
    void setUserProfile(Person p) throws IOException;

    /**
     * Returns the AddressBook.
     *
     * @see seedu.address.model.Model#getAddressBook()
     */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Saves all data to their respective files
     *
     * @throws IOException if file write error
     */
    void saveAllData() throws IOException;

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredPersonList();

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

    /**
     * Sets the Index of the Person's Detail to view
     *
     * @param index is index to selected index to
     */
    void setSelectedIndex(int index);

    /**
     * Returns the Index of the Person's Detail to view
     *
     * @return currently selected index
     */
    int getSelectedIndex();

    /**
     * Sets the PersonListPanel to control
     *
     * @param personListPanel the person list panel in GUI
     */
    void setPersonList(PersonListPanel personListPanel);

    /**
     * Returns the PersonListPanel used by Model
     *
     * @return PersonListPanel object
     */
    PersonListPanel getPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    ObservableList<Person> getModifiableList();

    void sort();
}
