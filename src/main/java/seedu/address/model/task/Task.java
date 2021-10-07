package seedu.address.model.task;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a Task of a Module in the TAB.
 */
public class Task {

    private final String module;
    private final String name;
    private final String deadline;
    private boolean isComplete;

    /**
     * Constructs a Task.
     * @param module The Module this Task is under.
     * @param name The Name of this Task.
     * @param deadline The Deadline of this Task.
     */
    public Task(String module, String name, String deadline) {
        requireAllNonNull(module, name, deadline);
        this.module = module;
        this.name = name;
        this.deadline = deadline;
        this.isComplete = false;
    }

    public String getModule() {
        return name;
    }

    public String getName() {
        return name;
    }

    public String getDeadline() {
        return deadline;
    }

    /**
     * Set a Task as completed.
     */
    public void setComplete() {
        this.isComplete = true;
    }

    /**
     * Set a Task as not completed.
     */
    public void setIncomplete() {
        this.isComplete = false;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Module: ")
                .append(getModule())
                .append("; Deadline: ")
                .append(getDeadline())
                .append("; Is completed: ")
                .append("yes/no");
        return builder.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Task)) {
            return false;
        }

        Task otherTask = (Task) other;
        return otherTask.getModule().equals(getModule())
                && otherTask.getName().equals(getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, module, deadline);
    }

}
