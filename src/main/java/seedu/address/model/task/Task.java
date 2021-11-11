package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

import seedu.address.model.common.Description;

/**
 * Represents a Task in the address book.
 * Guarantees: Details are present and not null. There are no restrictions to what is allowed as a description.
 */
public class Task {

    private final Description description;
    private boolean done = false;

    /**
     * Constructs a Task object.
     * Description must be present and not null.
     *
     * @param description Description of the task.
     */
    public Task(Description description) {
        requireNonNull(description);
        this.description = description;
    }

    public Description getDescription() {
        return description;
    }

    public void setDoneTask() {
        done = true;
    }

    public void setUndoneTask() {
        done = false;
    }

    public boolean getDoneTask() {
        return done;
    }

    /**
     * Returns true if both {@code Task} have the same {@code description} or are the same task.
     * Does not check whether the tasks are done or not.
     * This defines a weaker notion of equality between the two {@code Tasks}.
     *
     * @param otherTask Other task to compare.
     * @return Returns true if both {@code Task} have the same {@code description} or are the
     * same task and false otherwise.
     */
    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }

        return otherTask != null
                && otherTask.getDescription().equals(getDescription());
    }

    @Override
    public String toString() {
        return description.toString();
    }

    @Override
    public boolean equals (Object other) {
        return other == this
                || (other instanceof Task
                && description.equals(((Task) other).getDescription())
                && (done == ((Task) other).getDoneTask()));
    }

    @Override
    public int hashCode() {
        return description.hashCode();
    }
}
