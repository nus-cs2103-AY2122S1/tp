package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;
import java.util.Objects;

import javafx.collections.ObservableList;
import seedu.address.model.group.Group;
import seedu.address.model.group.UniqueGroupList;
import seedu.address.model.student.Student;
import seedu.address.model.student.UniqueStudentList;
import seedu.address.model.task.Task;
import seedu.address.model.task.UniqueTaskList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameStudent comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniqueStudentList students;
    private final UniqueTaskList tasks;
    private final UniqueGroupList groups;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        students = new UniqueStudentList();
        tasks = new UniqueTaskList();
        groups = new UniqueGroupList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Students in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the student list with {@code students}.
     * {@code students} must not contain duplicate students.
     */
    public void setStudents(List<Student> students) {
        this.students.setStudents(students);
    }

    /**
     * Replaces the contents of the student list with {@code tasks}.
     * {@code tasks} must not contain duplicate tasks.
     */
    public void setTasks(List<Task> tasks) {
        this.tasks.setTasks(tasks);
    }

    /**
     * Replaces the contents of the group list with {@code groups}.
     * {@code groups} must not contain duplicate groups.
     */
    public void setGroups(List<Group> groups) {
        this.groups.setGroups(groups);
    }

    /**
     * Clears all student data from {@code groups}.
     * {@code groups} must not contain duplicate groups.
     */
    public void clearStudentsInGroups(List<Group> groups) {
        this.groups.clearStudentsInGroups(groups);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setStudents(newData.getStudentList());
        setTasks(newData.getTaskList());
        setGroups(newData.getGroupList());
    }


    //// task-level operations
    /**
     * Returns true if a task {@code task} exists in the address book.
     */
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return tasks.contains(task);
    }

    /**
     * Adds a task to the address book.
     * The task must not already exist in the address book.
     */
    public void addTask(Task t) {
        tasks.add(t);
    }

    /**
     * Replaces the given task {@code target} in the list with {@code editedTask}.
     * {@code target} must exist in the address book.
     * The task identity of {@code editedTask} must not be the same as another existing task in the address book.
     */
    public void setTask(Task target, Task editedTask) {
        requireNonNull(editedTask);

        tasks.setTask(target, editedTask);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeTask(Task key) {
        tasks.remove(key);
    }

    /**
     * Clears all tasks in the task list.
     */
    public void clearAllTask() {
        tasks.removeAllTasks();
    }

    /**
     * Sorts all the tasks in the task list.
     */
    public void sortTasks() {
        tasks.sortList();
    }


    //// student-level operations

    /**
     * Returns true if a student with the same identity as {@code student} exists in the address book.
     */
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return students.contains(student);
    }

    /**
     * Returns true if a student with the same identity as {@code student} apart from the student {@toIgnore}
     * exists in the address book.
     */
    public boolean hasAnotherStudent(Student student, Student toIgnore) {
        requireAllNonNull(student, toIgnore);
        return students.containsAnother(student, toIgnore);
    }

    /**
     * Adds a student to the address book.
     * The student must not already exist in the address book.
     * @param s The student to be added.
     */
    public void addStudent(Student s) {
        students.add(s);
    }

    /**
     * Replaces the given student {@code target} in the list with {@code editedStudent}.
     * {@code target} must exist in the address book.
     * The student identity of {@code editedStudent} must not be the same as another existing student in the
     * address book.
     */
    public void setStudent(Student target, Student editedStudent) {
        requireNonNull(editedStudent);
        students.setStudent(target, editedStudent);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void deleteStudent(Student key) {
        requireNonNull(key);
        students.remove(key);
    }

    /**
     * Removes {@code key} from the {@code group} in the {@code AddressBook}.
     * {@code key} and {@code group} must exist in the address book.
     */
    public void deleteStudentFromGroup(Student key, Group group) {
        requireAllNonNull(key, group);
        groups.removeStudentFromGroup(key, group);
    }

    /**
     * Removes the current group to {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeGroupFromStudent(Student key) {
        requireNonNull(key);
        students.removeGroupFromStudent(key);
    }

    /**
     * Removes all groupNames from list of students.
     * {@code groups} must not contain duplicate groups.
     */
    public void clearGroupFromStudents(List<Student> students) {
        requireNonNull(students);
        this.students.clearGroupFromStudents(students);
    }

    //// group-level operations

    /**
     * Returns true if a group with the same identity as {@code group} exists in the address book.
     */
    public boolean hasGroup(Group group) {
        requireNonNull(group);
        return groups.contains(group);
    }

    /**
     * Adds a group to the address book.
     * The group must not already exist in the address book.
     * @param g The group to be added.
     */
    public void addGroup(Group g) {
        groups.add(g);
    }

    /**
     * Replaces the given group {@code target} in the list with {@code editedGroup}.
     * {@code target} must exist in the address book.
     * The group identity of {@code editedGroup} must not be the same as another existing group in the
     * address book.
     */
    public void setGroup(Group target, Group editedGroup) {
        requireNonNull(editedGroup);
        groups.setGroup(target, editedGroup);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void deleteGroup(Group key) {
        groups.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return students.asUnmodifiableObservableList().size() + " students\n"
               + tasks.asUnmodifiableObservableList().size() + " tasks";
        // TODO: refine later
    }

    @Override
    public ObservableList<Student> getStudentList() {
        return students.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Task> getTaskList() {
        return tasks.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Group> getGroupList() {
        return groups.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && students.equals(((AddressBook) other).students)
                && groups.equals(((AddressBook) other).groups)
                && tasks.equals(((AddressBook) other).tasks));
    }

    @Override
    public int hashCode() {
        return Objects.hash(students, tasks);
    }
}
