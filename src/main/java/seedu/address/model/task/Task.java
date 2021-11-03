package seedu.address.model.task;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Task in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public abstract class Task implements Comparable<Task>, Cloneable {

    private TaskName name;
    private Set<Tag> tags = new HashSet<>();
    private String description;
    private boolean isDone;
    private Priority priority;

    public enum Priority {
        HIGH, MEDIUM, LOW
    }

    /**
     * Constructs a {@code Task}.
     *
     * @param name A valid TaskName.
     * @param tags A valid Set of Tags.
     * @param description A valid Description of Tags.
     * @param isDone A boolean indicating the status of the Task.
     * @param priority A valid priority for the Task.
     */
    public Task(TaskName name, Set<Tag> tags, boolean isDone, Description description, Priority priority) {
        this.name = name;
        this.tags.addAll(tags);
        this.isDone = isDone;
        this.description = description.description;
        this.priority = priority;
    }

    /**
     * Constructs a {@code Task}.
     *
     * @param name A valid TaskName.
     * @param tags A valid Set of Tags.
     * @param description A valid Description of Tags.
     * @param isDone A boolean indicating the status of the Task.
     */
    public Task(TaskName name, Set<Tag> tags, boolean isDone, Description description) {
        this.name = name;
        this.tags.addAll(tags);
        this.isDone = isDone;
        this.description = description.description;
        this.priority = Priority.LOW;
    }

    public TaskName getName() {
        return name;
    }

    public String getStatusString() {
        return this.isDone ? "Completed" : "Pending";
    }

    public boolean checkIsDone() {
        return isDone;
    }

    public void toggleIsDone() {
        this.isDone = !this.isDone;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public String getDescription() {
        return description;
    }

    public Priority getPriority() {
        return priority;
    }

    public String getPriorityAsString() {
        if (this.priority == Priority.HIGH) {
            return "HIGH";
        } else if (this.priority == Priority.MEDIUM) {
            return "MEDIUM";
        } else {
            return "LOW";
        }
    }

    /**
     * Returns true if both Tasks have the same name.
     * This defines a weaker notion of equality between two Task.
     */
    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        } else if (this.getClass().equals(otherTask.getClass())) {
            return otherTask.getName().equals(getName()) && otherTask.getDate().equals(getDate())
                    && otherTask.getDescription().equals(getDescription());
        } else {
            return false;
        }
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
                && otherTask.getTags().equals(getTags())
                && otherTask.getDate().equals(getDate())
                && otherTask.getDescription().equals(getDescription())
                && otherTask.getPriority().equals(getPriority());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, tags, description, isDone, priority);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("\nStatus: ")
                .append(getStatusString());
        if (!this.getDescription().isEmpty()) {
            builder.append("\nDescription: ")
                    .append(getDescription());
        }
        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("\nTags: ");
            tags.forEach(builder::append);
        }

        return builder.toString();
    }

    /**
     * Returns the date associated with the task if the task is a DeadlineTask or an EventTask.
     * The maximum LocalDate is returned if the task is a TodoTask.
     *
     * @return The date associated with a task.
     */
    public abstract LocalDate getDate();

    @Override
    public int compareTo(Task otherTask) {
        if (this.isDone) {
            return otherTask.isDone ? 0 : 1;
        } else if (otherTask.isDone) {
            return -1;
        }

        LocalDate thisDate = this.getDate();
        LocalDate otherDate = otherTask.getDate();

        int result = thisDate.compareTo(otherDate);

        if (result == 0) {
            return this.priority.compareTo(otherTask.priority);
        } else {
            return result;
        }
    }

    @Override
    public Task clone() {
        try {
            Task clone = (Task) super.clone();
            clone.name = this.name;
            clone.tags = new HashSet<>();
            clone.tags.addAll(this.tags);
            clone.isDone = this.isDone;
            clone.description = this.description;
            clone.priority = this.priority;
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
