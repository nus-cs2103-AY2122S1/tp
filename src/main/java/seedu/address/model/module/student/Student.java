package seedu.address.model.module.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.task.Task;
import seedu.address.model.task.UniqueTaskList;

/**
 * Represents a Student in the TAB.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student {

    // Identity fields
    private final Name name;
    private final TeleHandle teleHandle;
    private final Email email;

    // Data fields
    private final StudentId studentId;
    private UniqueTaskList taskList;

    /**
     * Every field must be present and not null.
     */
    public Student(StudentId studentId, Name name, TeleHandle teleHandle, Email email) {
        requireAllNonNull(studentId, name, teleHandle, email);
        this.studentId = studentId;
        this.name = name;
        this.teleHandle = teleHandle;
        this.email = email;
        this.taskList = new UniqueTaskList();
    }

    /**
     * Constructor that also specifies taskList, to be used for storage.
     */
    public Student(StudentId studentId, Name name, TeleHandle teleHandle, Email email,
                   UniqueTaskList taskList) {
        this.studentId = studentId;
        this.name = name;
        this.teleHandle = teleHandle;
        this.email = email;
        this.taskList = taskList;
    }

    public Name getName() {
        return name;
    }

    public TeleHandle getTeleHandle() {
        return teleHandle;
    }

    public Email getEmail() {
        return email;
    }

    public StudentId getStudentId() {
        return studentId;
    }

    /**
     * Adds a task to this student's task list.
     * The task must not already exist in this student's task list.
     * @param task
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Returns true if this student has {@code task} exists in the task list.
     */
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return taskList.contains(task);
    }

    /**
     * Returns the UniqueTaskList of this Student.
     */
    public UniqueTaskList getTaskList() {
        return this.taskList;
    }

    /**
     * Sets the UniqueTaskList of this Student to be the input {@code taskList} taskList.
     * @param taskList the taskList to set this Student's UniqueTaskList to.
     */
    public void setTaskList(UniqueTaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameStudent(Student otherStudent) {
        if (otherStudent == this) {
            return true;
        }

        return otherStudent != null
                && otherStudent.getStudentId().equals(getStudentId());
    }

    /**
     * Removes the specified {@Code task} from the student's task list.
     */
    public void removeTask(Task task) {
        taskList.remove(task);
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Student)) {
            return false;
        }

        Student otherStudent = (Student) other;
        return otherStudent.getName().equals(getName())
                && otherStudent.getTeleHandle().equals(getTeleHandle())
                && otherStudent.getEmail().equals(getEmail())
                && otherStudent.getStudentId().equals(getStudentId());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, teleHandle, email, studentId);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Telegram Handle: ")
                .append(getTeleHandle())
                .append("; Email: ")
                .append(getEmail())
                .append("; StudentId: ")
                .append(getStudentId());

        return builder.toString();
    }

}
