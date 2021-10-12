package seedu.address.model.module.student;

import seedu.address.model.task.Task;
import seedu.address.model.task.UniqueTaskList;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

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
    //private final Set<Tag> tags = new HashSet<>();

    private final UniqueTaskList taskList;

    /**
     * Every field must be present and not null.
     */
    public Student(StudentId studentId, Name name, TeleHandle teleHandle, Email email) {
        requireAllNonNull(name, teleHandle, email, studentId);
        this.name = name;
        this.teleHandle = teleHandle;
        this.email = email;
        this.studentId = studentId;
        this.taskList = new UniqueTaskList();
        //this.tags.addAll(tags);
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
                .append("; Phone: ")
                .append(getTeleHandle())
                .append("; Email: ")
                .append(getEmail())
                .append("; StudentId: ")
                .append(getStudentId());

        return builder.toString();
    }

}
