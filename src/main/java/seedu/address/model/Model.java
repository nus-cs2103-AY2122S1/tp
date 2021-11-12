package seedu.address.model;

import java.nio.file.Path;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.exceptions.InvalidShiftTimeException;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Slot;
import seedu.address.model.person.exceptions.DuplicateShiftException;
import seedu.address.model.person.exceptions.NoShiftException;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

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
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person staff);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedStaff);

    /**
     * Retrieves the Person at the index specified of the observed filtered list.
     *
     * @param index The index of the Person desired
     * @return the Person at the index of the filtered list specified.
     */
    Person getFilteredPersonListByIndex(int index);


    /**
     * Returns the person with given name.
     * @param name Given name.
     * @return Matched Person.
     */
    Person findPersonByName(Name name);

    /**
     * Add a shift to a target staff's schedule.
     * {@code target} must exist in the address book.
     *
     * @param target The target staff.
     * @param dayOfWeek of the shift.
     * @param slot of the shift.
     * @param startDate The startDate of the shift.
     * @throws DuplicateShiftException Throws when there is already a shift at the target slot.
     */
    void addShift(Person target, DayOfWeek dayOfWeek, Slot slot,
                  LocalDate startDate, LocalDate endDate) throws DuplicateShiftException;

    /**
     * Deletes a shift from a target staff's schedule.
     * {@code target} must exist in the address book.
     *
     * @param target The target staff.
     * @param dayOfWeek of the shift.
     * @param slot of the shift.
     * @param endDate The date that the shift ends at.
     * @throws NoShiftException throws when a user tries to delete a shift that does not exist.
     */
    void deleteShift(Person target, DayOfWeek dayOfWeek, Slot slot, LocalDate startDate,
                     LocalDate endDate) throws NoShiftException;

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /** Returns an unmodifiable view of the unfiltered person list */
    ObservableList<Person> getUnFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Set time for a shift from a target staff's schedule.
     * {@code target} must exist in the address book.
     *
     * @param target The target staff.
     * @param dayOfWeek of the shift.
     * @param slot of the shift.
     * @param startTime The start time of the shift.
     * @param endTime The end time of the shift.
     * @throws InvalidShiftTimeException throws when the timings of Shift are invalid.
     */
    void setShiftTime(Person target, DayOfWeek dayOfWeek, Slot slot, LocalTime startTime, LocalTime endTime,
                      LocalDate startDate, LocalDate endDate)
            throws InvalidShiftTimeException;

}
