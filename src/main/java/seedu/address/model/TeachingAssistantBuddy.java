package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.module.Module;
import seedu.address.model.student.Student;
import seedu.address.model.student.UniqueStudentList;
import seedu.address.model.task.Task;
import seedu.address.model.task.UniqueTaskList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class TeachingAssistantBuddy implements ReadOnlyTeachingAssistantBuddy {

    //todo: need a unique module list here, I am naming it modules for now
    private final UniqueStudentList students;
    private final UniqueTaskList tasks;

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
    }

    public TeachingAssistantBuddy() {}

    /**
     * Creates an TeachingAssistantBuddy using the Persons in the {@code toBeCopied}
     */
    public TeachingAssistantBuddy(ReadOnlyTeachingAssistantBuddy toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// module-level operations

    public void removeModule(Module key) {
        modules.remove(key);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the student list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setStudents(List<Student> students) {
        this.students.setStudents(students);
    }

    /**
     * Replaces the contents of the task list with {@code tasks}.
     * {@code persons} must not contain duplicate tasks.
     */
    public void setTasks(List<Task> tasks) {
        this.tasks.setTasks(tasks);
    }

    /**
     * Resets the existing data of this {@code TeachingAssistantBuddy} with {@code newData}.
     */
    public void resetData(ReadOnlyTeachingAssistantBuddy newData) {
        requireNonNull(newData);
        setStudents(newData.getStudentList());
        setTasks(newData.getTaskList());
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
     * Returns true if a student with the same identity as {@code student} exists in the address book.
     */
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return tasks.contains(task);
    }


    /**
     * Adds a student to the address book.
     * The student must not already exist in the address book.
     */
    public void addStudent(Student p) {
        students.add(p);
    }

    /**
     * Adds a student to the address book.
     * The student must not already exist in the address book.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }


    /**
     * Replaces the given student {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the TAB.
     * The student identity of {@code editedPerson} must not be the same
     * as another existing student in the address book.
     */
    public void setStudent(Student target, Student editedStudent) {
        requireNonNull(editedStudent);

        students.setStudent(target, editedStudent);
    }

    /**
     * Replaces the given task {@code target} in the list with {@code editedTask}.
     * {@code target} must exist in the TAB.
     * The task identity of {@code editedTask} must not be the same
     * as another existing task in the module.
     */
    public void setTask(Task target, Task editedTask) {
        requireNonNull(editedTask);
        tasks.setTask(target, editedTask);
    }

    /**
     * Removes {@code key} from this {@code TeachingAssistantBuddy}.
     * {@code key} must exist in the TAB.
     */
    public void removeStudent(Student key) {
        students.remove(key);
    }

    /**
     * Removes {@code key} from this {@code Module}.
     * {@code key} must exist in the module.
     */
    public void removeTask(Task key) {
        tasks.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return students.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Student> getStudentList() {
        return students.asUnmodifiableObservableList();
    }

    public ObservableList<Module> getModuleList() {
        return modules.asUnmodifiableObservableList();
    }

    public ObservableList<Task> getTaskList() {
        return tasks.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TeachingAssistantBuddy // instanceof handles nulls
                && students.equals(((TeachingAssistantBuddy) other).students));
    }

    @Override
    public int hashCode() {
        return students.hashCode();
    }
}
