package seedu.address.model.task;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a Task in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Task {
    // Identity fields
    private final Name name;

    // Data fields
    private final Deadline deadline;

    /**
     * Every field must be present and not null.
     */
    public Task(Name name, Deadline deadline) {
        requireAllNonNull(name, deadline);
        this.name = name;
        this.deadline = deadline;
    }

    public Name getName() {
        return name;
    }

    public Deadline getDeadline() {
        return deadline;
    }

    /**
     * Returns true if both tasks have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }

        return otherTask != null
                && otherTask.getName().equals(getName());
    }

    /**
     * Returns true if both tasks have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Task)) {
            return false;
        }

        Task otherTask = (Task) other;
        return otherTask.getName().equals(getName())
                && otherTask.getDeadline().equals(getDeadline());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, deadline);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Deadline: ")
                .append(getDeadline())
                .append("; Completion Status: ");

        return builder.toString();
    }
}
