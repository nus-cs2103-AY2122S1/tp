package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Task {
    public static final String MESSAGE_CONSTRAINTS = "Task names should contain at least 1 non-whitespace character";
    public static final String VALIDATION_REGEX = "\\S+.*"; // At least 1 non-whitespace
    public final String taskName;

    /**
     * Constructor for task. Creates a new task with the given a String name.
     */
    public Task (String taskName) {
        requireNonNull(taskName);
        checkArgument(isValidTaskName(taskName), MESSAGE_CONSTRAINTS);
        this.taskName = taskName;
    }

    public String getTaskName() {
        return taskName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Task && taskName.equals(((Task) other).getTaskName()));
    }

    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }

        return otherTask != null
                && otherTask.getTaskName().equals(getTaskName());
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidTaskName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return taskName;
    }
}
