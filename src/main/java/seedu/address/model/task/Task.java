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
public class Task implements Comparable<Task> {

    private final TaskName name;
    private final Set<Tag> tags = new HashSet<>();
    private boolean isDone;

    /**
     * Constructs a {@code Task}.
     *
     * @param name A valid TaskName.
     * @param tags A valid Set of Tags.
     */
    public Task(TaskName name, Set<Tag> tags, boolean isDone) {
        this.name = name;
        this.tags.addAll(tags);
        this.isDone = isDone;
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

    public void markTaskComplete() {
        this.isDone = true;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }


    /**
     * Returns true if both Tasks have the same name.
     * This defines a weaker notion of equality between two Task.
     */
    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }

        return otherTask != null
                && otherTask.getName().equals(getName())
                && otherTask.getDate().equals(getDate());
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
                && otherTask.getDate().equals(getDate());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, tags, isDone);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("\nStatus: ")
                .append(getStatusString());

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
    private LocalDate getDate() {
        if (this instanceof DeadlineTask) {
           return ((DeadlineTask) this).getDeadline().getDeadline();
        } else if (this instanceof EventTask) {
            return ((EventTask) this).getTaskDate().getDeadline();
        } else {
            return LocalDate.MAX;
        }
    }

    @Override
    public int compareTo(Task otherTask) {
        LocalDate thisDate = this.getDate();
        LocalDate otherDate = otherTask.getDate();

        return thisDate.compareTo(otherDate);
    }
}
