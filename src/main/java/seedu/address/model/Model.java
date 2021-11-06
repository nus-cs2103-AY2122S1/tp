package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.assessment.Assessment;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupName;
import seedu.address.model.student.Name;
import seedu.address.model.student.Note;
import seedu.address.model.student.Student;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Student> PREDICATE_SHOW_ALL_STUDENTS = unused -> true;
    Predicate<Group> PREDICATE_SHOW_ALL_GROUPS = unused -> true;

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
     * Returns the user prefs' CS book file path.
     */
    Path getCsBookFilePath();

    /**
     * Sets the user prefs' CS book file path.
     */
    void setCsBookFilePath(Path csBookFilePath);

    /**
     * Replaces model data with the data in {@code csBook}.
     */
    void setCsBook(ReadOnlyCsBook csBook);

    /** Returns the CsBook */
    ReadOnlyCsBook getCsBook();

    /**
     * Returns true if a student with the same identity as {@code student} exists in the CS book.
     */
    boolean hasStudent(Student student);

    /**
     * Returns true if a student with the same identity as {@code student} exists in the CS book.
     */
    boolean hasStudent(Name name);

    /**
     * Returns the target student from the CS book.
     */
    void changeStudentGroup(Student student, Group newGroup);

    /**
     * Updates the note of a student in the CS book.
     */
    Student updateStudentNote(Student student, Note note);

    /**
     * Deletes the given student.
     * The student must exist in the model.
     */
    void deleteStudent(Student target);

    /**
     * Adds the given student.
     * {@code student} must not already exist in the model.
     */
    void addStudent(Student student);

    /**
     * Retrieves a student by their name
     */
    Student getStudentByName(Name studentName);

    /**
     * Replaces the given student {@code target} with {@code editedStudent}.
     * {@code target} must exist in the model.
     * The student identity of {@code editedStudent} must not be the same as another existing student in the address
     * book.
     */
    void setStudent(Student target, Student editedStudent);

    /** Returns an unmodifiable view of the filtered student list */
    ObservableList<Student> getFilteredStudentList();

    /**
     * Updates the filter of the filtered student list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredStudentList(Predicate<Student> predicate);


    /**
     * Returns true if a group with the same identity as {@code group} exists in the model.
     */
    boolean hasGroup(Group group);

    /**
     * Returns true if a group with the same groupName as {@code groupName} exists in the model.
     */
    boolean hasGroup(GroupName groupName);

    /**
     * Adds a student to a new group as well as removing the old reference of the student from the old group.
     */
    void updateGroupStudent(Group group, Student student);

    /**
     * Deletes the given group.
     * The group must exist in the model.
     */
    void deleteGroup(Group target);

    /**
     * Adds the given group.
     * {@code group} must not already exist in the model.
     */
    void addGroup(Group group);

    /**
     * Returns the group in the model, identified by the given {@code groupName}
     */
    Group getGroupByGroupName(GroupName groupName);

    ///**
    // * Replaces the given group {@code target} with {@code editedGroup}.
    // * {@code target} must exist in the model.
    // * The group identity of {@code editedGroup} must not be the same as another existing group in the address
    // * book.
    // */
    // void setGroup(Group target, Group editedGroup); //TODO to implement in 1.3

    /** Returns an unmodifiable view of the filtered group list */
    ObservableList<Group> getFilteredGroupList();

    /**
     * Updates the filter of the filtered group list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredGroupList(Predicate<Group> predicate);

    /**
     * Returns true if a student with the same identity as {@code student} in the CS Book has the same assessment with
     * the same identity as {@code assessment}
     */
    boolean hasAssessment(Student student, Assessment assessment);

    /**
     * Adds the given assessment to the given student.
     * {@code assessment} must not already exist in the student's.
     */
    void addAssessment(Student student, Assessment assessment);

    /**
     * Deletes the given assessment from the given student.
     * {@code assessment} must exist in the student's assessment list.
     */
    void deleteAssessment(Student student, Assessment assessment);
}
