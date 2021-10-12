package seedu.address.model.task;



import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.module.ModuleName;

/**
 * Represents a Task of a TeachingAssistantBuddy in the TAB.
 */
public class Task {

    private final ModuleName module;
    private final String name;
    private final String deadline;
    private boolean isComplete;

    /**
     * Constructs a Task.
     * @param module The TeachingAssistantBuddy this Task is under.
     * @param name The Name of this Task.
     * @param deadline The Deadline of this Task.
     */
    public Task(ModuleName module, String name, String deadline) {
        requireAllNonNull(module, name, deadline);
        this.module = module;
        this.name = name;
        this.deadline = deadline;
        this.isComplete = false;
    }

    public String getModule() {
        return this.module.toString();
    }

    public String getName() {
        return name;
    }

    public String getDeadline() {
        return deadline;
    }

    public boolean isComplete() {
        return isComplete;
    }

    /**
     * Compares two tasks and returns true when they share the same name and module.
     * @param otherTask The Task to compare with.
     * @return Whether these two Tasks are the same.
     */
    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }

        return otherTask != null
                && otherTask.getName().equals(otherTask.getName())
                && otherTask.getModule().equals(otherTask.getModule());
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
                .append("; TeachingAssistantBuddy: ")
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
