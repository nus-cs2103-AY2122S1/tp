package seedu.unify.model.task;

import static seedu.unify.commons.util.CollectionUtil.requireAllNonNull;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import seedu.unify.model.tag.Tag;


/**
 * Represents a Task in the Uni-Fy app.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Task {

    // Identity fields
    private final Name name;

    // Data fields
    private final Time time;
    private final Date date;
    private final Set<Tag> tags = new HashSet<>();
    private final State state;
    private final Priority priority;

    /**
     * Every field must be present and not null.
     */
    public Task(Name name, Time time, Date date, Set<Tag> tags, Priority priority) {
        requireAllNonNull(name, time, date, tags, priority);
        this.name = name;
        this.time = time;
        this.date = date;
        this.tags.addAll(tags);
        this.priority = priority;
        this.state = new State();
    }

    /**
     * Every field must be present and not null.
     */
    public Task(Name name, Time time, Date date, Set<Tag> tags, State state, Priority priority) {
        requireAllNonNull(name, time, date, tags, state, priority);
        this.name = name;
        this.time = time;
        this.date = date;
        this.tags.addAll(tags);
        this.state = state;
        this.priority = priority;
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

    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public State getState() {
        return state;
    }

    public Priority getPriority() {
        return priority;
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
            && otherTask.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, time, date, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
            .append("; Time: ")
            .append(getTime())
            .append("; Date: ")
            .append(getDate())
            .append("; State: ")
            .append(getState())
            .append("; Priority: ")
            .append(getPriority());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        return builder.toString();
    }

    public long getTimeRepresentation() {
        long year = date.getLocalDate().getYear();
        long month = date.getLocalDate().getMonthValue();
        long day = date.getLocalDate().getDayOfMonth();

        long minutes = getTime().getTimeInMinutesFromStartOfDay();
        long timeRepresentation = year;
        timeRepresentation = timeRepresentation * 100 + month;
        timeRepresentation = timeRepresentation * 100 + day;
        timeRepresentation = timeRepresentation * 1000 + minutes;

        return timeRepresentation;
    }
}
