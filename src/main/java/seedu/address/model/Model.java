package seedu.address.model;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.model.alias.Alias;
import seedu.address.model.alias.AliasMap;
import seedu.address.model.alias.CommandWord;
import seedu.address.model.alias.Shortcut;
import seedu.address.model.facility.Facility;
import seedu.address.model.person.Person;
import seedu.address.model.sort.SortOrder;


/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /** {@code Predicate} that always evaluates to true */
    Predicate<Facility> PREDICATE_SHOW_ALL_FACILITIES = unused -> true;

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
     * Returns the user pref's aliases.
     */
    AliasMap getAliases();

    /**
     * Adds the given alias to user prefs.
     */
    void addAlias(Alias alias);

    /**
     * Removes the given alias from user prefs.
     *
     * @param shortcut the shortcut to remove.
     * @return commandWord associated with shortcut, null if alias does not exist.
     */
    CommandWord removeAlias(Shortcut shortcut);

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
     * Returns true if a facility with the same parameters as {@code facility} exists in SportsPA.
     */
    boolean hasFacility(Facility facility);

    /**
     * Returns true if all {@code indices} is within the member list.
     */
    boolean isWithinListIndex(List<Index> indices);

    /**
     * Marks attendance of members at specified {@code indices} as present.
     */
    void markMembersAttendance(List<Index> indices);

    /**
     * Marks attendance of specified {@code member} as present.
     */
    void markOneMemberAttendance(Person member);

    /**
     * Unmarks attendance of members at specified {@code indices} as absent.
     */
    void unmarkMembersAttendance(List<Index> indices);

    /**
     * Unmarks attendance of specified {@code member} as absent.
     */
    void unmarkOneMemberAttendance(Person member);

    /**
     * Resets today's attendance for all members.
     */
    void resetTodayAttendance();

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Deletes the given facility.
     * The facility must exist in SportsPA.
     */
    void deleteFacility(Facility target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Adds the given facility.
     *
     * @param facility Facility to be added.
     */
    void addFacility(Facility facility);

    /**
     * Allocates members into facilities.
     *
     * @param predicate the condition in which specifies the members to allocate.
     * @param dayNumber the day on which to allocate the members.
     * @return number of members left unallocated, -1 if zero members to allocate.
     */
    int split(Predicate<Person> predicate, int dayNumber);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /**
     * Replaces the given facility {@code target} with {@code editedFacility}.
     * {@code target} must exist in SportsPA.
     * The facility parameters of {@code editedFacility} must not be the same as another existing facility in SportsPA.
     */
    void setFacility(Facility facility, Facility editedFacility);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Returns a view of the filtered facility list.
     *
     * @return ObservableList with filtered facilities.
     */
    ObservableList<Facility> getFilteredFacilityList();

    /**
     * Returns a person from the address book that has the same name as the given {@code person}.
     *
     * @param person the given person
     * @return a person that has the same name.
     */
    Person getSamePerson(Person person);

    /**
     * Sorts the member list in the specified order.
     */
    void sortMemberList(SortOrder sortOrder);

    /**
     * Clears the contents of the member list.
     */
    void resetMemberList();

    /**
     * Clears the contents of the facility list.
     */
    void resetFacilityList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Updates the filter of the filtered facility list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredFacilityList(Predicate<Facility> predicate);
}
