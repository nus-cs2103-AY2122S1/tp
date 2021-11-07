package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.group.Group;
import seedu.address.model.student.Student;
import seedu.address.model.task.Task;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Student> PREDICATE_SHOW_ALL_STUDENTS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Task> PREDICATE_SHOW_ALL_TASKS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Group> PREDICATE_SHOW_ALL_GROUPS = unused -> true;

    enum DisplayType {
        STUDENTS, TASKS, GROUPS, TASK_HISTORY
    }

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

    DisplayType getDisplayType();

    /**
     * Returns true if a student with the same identity as {@code student} exists in the address book.
     */
    boolean hasStudent(Student student);

    /**
     * Returns true if a student with the same identity as {@code student} apart from {@toIgnore} exists in
     * the address book.
     */
    boolean hasAnotherStudent(Student student, Student toIgnore);

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
     * The student identity of {@code editedStudent} must not be the same as another existing student in the
     * address book.
     */
    void setStudent(Student target, Student editedStudent);

    /**
     * Retrieve the attendance of the given student {@code target} for {@code week}.
     * {@code target} must exist in the student list.
     */
    String getStudentAttendance(Student target, int week);

    /**
     * Marks the attendance of the given student {@code target} for {@code week}.
     * {@code target} must exist in the student list.
     */
    void markStudentAttendance(Student target, int week);

    /**
     * Retrieve the attendance of the given student {@code target} for {@code week}.
     * {@code target} must exist in the student list.
     */
    String getStudentParticipation(Student target, int week);

    /**
     * Marks the attendance of the given student {@code target} for {@code week}.
     * {@code target} must exist in the student list.
     */
    void markStudentParticipation(Student target, int week);

    /**
     * Adds the given student {@code target} to a group of {@code group}.
     * {@code target} must exist in the student list.
     * A group of {@code group} must exist in the group list.
     */
    void addMember(Student target, Group group);

    /**
     * Removes the given member {@code target} from a group of {@code group}.
     * A group of {@code group} must exist in the group list.
     * {@code targe} must exist in the list of members of the group of  {@code group}.
     */
    void deleteMember(Student target, Group group);

    /** Returns an unmodifiable view of the filtered student list */
    ObservableList<Student> getFilteredStudentList();

    /** Returns an unmodifiable view of all students in student list */
    ObservableList<Student> getAllStudentList();

    /**
     * Updates the filter of the filtered student list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredStudentList(Predicate<Student> predicate);

    /**
     * Returns true if a group with the same identity as {@code group} exists in tApp.
     */
    boolean hasGroup(Group group);

    /**
     * Deletes the given group.
     * The group must exist in tApp.
     */
    void deleteGroup(Group target);

    /**
     * Adds the given group.
     * {@code group} must not already exist in tApp.
     */
    void addGroup(Group group);

    /**
     * Replaces the given group {@code target} with {@code editedGroup}.
     * {@code target} must exist in the address book.
     * The group identity of {@code editedGroup} must not be the same as another existing group in the address book.
     */
    void setGroup(Group target, Group editedGroup);

    /** Returns an unmodifiable view of the filtered group list */
    ObservableList<Group> getFilteredGroupList();

    /**
     * Updates the filter of the filtered group list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredGroupList(Predicate<Group> predicate);

    /** Returns an unmodifiable view of all groups in group list */
    ObservableList<Group> getAllGroupList();

    /**
     * Returns true if a task with the same identity as {@code task} exists in the address book.
     */
    boolean hasTask(Task task);

    /**
     * Deletes the given task.
     * The task must exist in the address book.
     */
    void deleteTask(Task target);

    /**
     * Marks the given task as completed.
     * The task must exist in the address book.
     */
    void toggleTaskIsDone(Task target);

    /**
     * Adds the given task.
     * {@code task} must not already exist in the address book.
     */
    void addTask(Task task);

    /**
     * Replaces the given student {@code target} with {@code editedTask}.
     * {@code target} must exist in the address book.
     * The task identity of {@code editedTask} must not be the same as another existing task in the address book.
     */
    void setTask(Task target, Task editedTask);

    /**
     * Clears all tasks in tApp.
     */
    void clearTasks();

    /** Returns an unmodifiable view of the filtered student list */
    ObservableList<Task> getFilteredTaskList();

    /**
     * Updates the filter of the filtered task list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTaskList(Predicate<Task> predicate);

}
