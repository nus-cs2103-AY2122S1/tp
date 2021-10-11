package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Objects;

/**
 * Represents a task in the Ail.
 * Guarantees: immutable; is valid as declared in {@link #isValidTaskName(String)}
 */
public class Task {
    public static final String MESSAGE_CONSTRAINTS = "Task name can take any values, and it should not be blank";

    /*
     * The first character of the task name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    private final String taskName;
    private final boolean isDone;

    /**
     * Constructs a {@code Task}.
     *
     * @param taskName A valid task name.
     */
    public Task(String taskName) {
        requireNonNull(taskName);
        checkArgument(isValidTaskName(taskName), MESSAGE_CONSTRAINTS);
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Constructs a {@code Task}.
     *
     * @param taskName A valid task name.
     * @param isDone A boolean value.
     */
    public Task(String taskName, boolean isDone) {
        requireNonNull(taskName);
        checkArgument(isValidTaskName(taskName), MESSAGE_CONSTRAINTS);
        this.taskName = taskName;
        this.isDone = isDone;
    }

    public String getTaskName() {
        return taskName;
    }

    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns true if a given string is a valid task name.
     */
    public static boolean isValidTaskName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if both tasks are the same as defined
     * This defines a weaker notion of equality between two tasks.
     */
    /*public boolean isSameTask(Task otherTask) {
        return this.taskName.equals(otherTask.taskName);
    }*/

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        Task task = (Task) other;
        boolean result = isDone == task.isDone && taskName.equals(task.taskName);
        System.out.println(result);
        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskName, isDone);
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return "(" + getStatusIcon() + ")" + taskName;
    }
}
