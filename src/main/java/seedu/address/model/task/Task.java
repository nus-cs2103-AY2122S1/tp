package seedu.address.model.task;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a Task in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Task {
    private final TaskDescription taskDescription;

    /**
     * Constructs a {@code Task}.
     *
     * @param taskDescription A valid task description
     */
    public Task(TaskDescription taskDescription) {
        requireAllNonNull(taskDescription);
        this.taskDescription = taskDescription;
    }

    public TaskDescription getTaskDescription() {
        return taskDescription;
    }

    /**
     * Returns true if both tasks have the same task description.
     */
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Task)) {
            return false;
        }

        Task otherTask = (Task) other;
        return otherTask.getTaskDescription().equals(getTaskDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskDescription);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTaskDescription());
        return builder.toString();
    }
}
