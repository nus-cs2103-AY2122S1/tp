package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.parser.SortCommandParser;
import seedu.address.model.person.Person;
import seedu.address.model.tuition.TuitionClass;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    Predicate<TuitionClass> PREDICATE_SHOW_ALL_TUITIONS = unused -> true;

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
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /** Returns an unmodifiable view of the filtered tuition list */
    ObservableList<TuitionClass> getFilteredTuitionList();

    /**
     * Updates the filter of the filtered tuition list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTuitionList(Predicate<TuitionClass> predicate);

    boolean hasTuition(TuitionClass tuitionClass);

    /**
     * Deletes the given tuition class.
     * The class must exist in the address book.
     */
    void deleteTuition(TuitionClass target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addTuition(TuitionClass tuitionClass);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setTuition(TuitionClass target, TuitionClass editedTuition);

    /**
     * Check whether the list of students contains the index from input.
     * @param index the index of student to be checked.
     * @return the person if the index is present.
     */
    Person getStudent(Index index);

    /**
     * Check whether the list of tuition classes contains the index from input.
     * @param index the index of class to be checked.
     * @return the class if the index is present.
     */
    TuitionClass getTuitionClass(Index index);

    /**
     * Add a new student to an existing class.
     * @param tuitionClass an existing class.
     * @param person an existing student.
     * @return the tuition class after modification.
     */
    TuitionClass addToClass(TuitionClass tuitionClass, Person person);

    /**
     * Returns a person with the same name as the input person.
     * @param otherPerson the person to be checked
     * @return the person with the same name as input.
     */
    Person getSameNamePerson(Person otherPerson);

    /**
     * Check whether the list of tuition classes contains the id from input.
     * @param id the id of class to be checked.
     * @return the class if the id is present.
     */
    TuitionClass getClassById(Integer id);

    void sort(SortCommandParser.Order order);
}
