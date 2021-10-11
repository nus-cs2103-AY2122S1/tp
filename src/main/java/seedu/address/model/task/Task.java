package seedu.address.model.task;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.id.HasUniqueId;
import seedu.address.model.id.UniqueId;

/**
 * Represents a Task in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Task implements HasUniqueId {
    // The task name
    private final Name name;

    // The deadline of the task
    private final Deadline deadline;

    // The id of the task
    private final UniqueId id;

    /**
     * Every field must be present and not null.
     */
    public Task(Name name, Deadline deadline) {
        this.id = UniqueId.generateId(this);
        requireAllNonNull(name, deadline, id);
        this.name = name;
        this.deadline = deadline;
    }

    /**
     * Every field must be present and not null.
     */
    public Task(Name name, Deadline deadline, UniqueId id) {
        requireAllNonNull(name, deadline, id);
        this.name = name;
        this.deadline = deadline;
        this.id = id;
    }

    public Name getName() {
        return name;
    }

    public Deadline getDeadline() {
        return deadline;
    }

    public UniqueId getId() {
        return id;
    }

    /**
     * Returns true if both tasks have the same name and deadline.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }

        return otherTask != null
                && otherTask.getName().equals(getName())
                && otherTask.getDeadline().equals(getDeadline());
    }

    /**
     * Returns true if both tasks have the same id, name and deadline.
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
        return otherTask.getId().equals(getId()) && isSameTask(otherTask);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, deadline);
    }

    @Override
    public String toString() {
        return getName()
                + "; Deadline: "
                + getDeadline();
    }
}
