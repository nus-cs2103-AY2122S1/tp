package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.Person;
import seedu.address.ui.PersonListPanel;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /**
     * Returns the user profile.
     */
    Person getUserProfile();

    /**
     * Checks if user profile exists.
     */
    boolean isProfilePresent();

    /**
     * Replaces user profile data with the data in {@code userProfile}.
     */
    void setUserProfile(Person userProfile);

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
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Favorites the given person.
     * The person must exist in the address book.
     */
    void favoritePerson(Person target);

    /**
     * Favorites the given person.
     * The person must exist in the address book.
     */
    void unfavoritePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

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
    void setPersonListControl(PersonListPanel personListPanel);

    /**
     * Returns the PersonListPanel used by Model
     *
     * @return PersonListPanel object
     */
    PersonListPanel getPersonListControl();

    /**
     * Selects the Tab at given index
     *
     * @param index the index of tab to select
     */
    void setTabIndex(int index);
}
