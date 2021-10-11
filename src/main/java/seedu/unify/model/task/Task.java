package seedu.unify.model.task;

import static seedu.unify.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a Task in the Uni-Fy app.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Task {

    // Identity fields
    private final Name name;
    private final Time time;

    // Data fields
    private final Date date;
    private final Tag tag;

    /**
     * Every field must be present and not null.
     */
    public Task(Name name, Time time, Date date, Tag tag) {
        requireAllNonNull(name, time, date, tag);
        this.name = name;
        this.time = time;
        this.date = date;
        this.tag = tag;
    }

    public Name getName() {
        return name;
    }

    public Time getTime() {
        return time;
    }

    public Date getDate() {
        return date;
    }

    public Tag getTag() {
        return tag;
    }

    /**
     * Returns true if both tasks have the same name.
     * This defines a weaker notion of equality between two tasks.
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
     * This defines a stronger notion of equality between two tasks.
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
                && otherTask.getTime().equals(getTime())
                && otherTask.getDate().equals(getDate())
                && otherTask.getTag().equals(getTag());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, time, date, tag);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Time: ")
                .append(getTime())
                .append("; Date: ")
                .append(getDate())
                .append("; Tag: ")
                .append(getTag());

        return builder.toString();
    }

}
