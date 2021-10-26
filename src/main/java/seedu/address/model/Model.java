package seedu.address.model;

import java.nio.file.Path;
import java.util.Map;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.parser.Alias;
import seedu.address.model.student.Assessment;
import seedu.address.model.student.Group;
import seedu.address.model.student.Student;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Student> PREDICATE_SHOW_ALL_STUDENTS = unused -> true;

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
     * Sets the user prefs' aliases.
     */
    void setAliases(Map<String, String> aliases);

    /**
     * Returns the user prefs' aliases.
     */
    Map<String, String> getAliases();

    /**
     * Adds an alias to the user prefs' aliases.
     */
    void addAlias(Alias alias);

    /**
     * Removes an alias from the user pref's aliases.
     */
    void removeAlias(String aliasWord);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a student with the same identity as {@code student} exists in the address book.
     */
    boolean hasStudent(Student student);

    /**
     * Returns student in address book with same identity as {@code studentToMatch} if exists.
     */
    Student getStudent(Student studentToMatch);

    /**
     * Deletes the given student.
     * The student must exist in the address book.
     */
    void deleteStudent(Student target);

    /**
     * Adds the given student.
     * {@code student} must not already exist in the address book.
     */
    void addStudent(Student student);

    /**
     * Replaces the given student {@code target} with {@code editedStudent}.
     * {@code target} must exist in the address book.
     * The student identity of {@code editedStudent} must not be the same as
     * another existing student in the address book.
     */
    void setStudent(Student target, Student editedStudent);

    /**
     * Returns true if a group with the same identity as {@code group} exists in the application.
     */
    boolean hasGroup(Group group);

    /**
     * Adds the given group.
     * {@code group} must not already exist in the address book.
     */
    void addGroup(Group group);

    /**
     * Returns true if a group with the same identity as {@code assessment} exists in the application.
     */
    boolean hasAssessment(Assessment assessment);

    /**
     * Returns assessment in address book with same identity as {@code assessmentToMatch} if exists.
     */
    Assessment getAssessment(Assessment assessmentToMatch);

    /**
     * Adds the given assessment.
     * {@code assessment} must not already exist in the address book.
     */
    void addAssessment(Assessment assessment);

    /**
     * Replaces the given assessment {@code target} with {@code editedAssessment}.
     * {@code target} must exist in the address book.
     * The assessment identity of {@code editedAssessment} must not be the same as
     * another existing assessment in the address book.
     */
    void setAssessment(Assessment target, Assessment editedAssessment);

    /** Returns an unmodifiable view of the filtered student list */
    ObservableList<Student> getFilteredStudentList();

    /**
     * Updates the filter of the filtered student list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredStudentList(Predicate<Student> predicate);
}
