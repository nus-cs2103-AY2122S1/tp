package seedu.address.model.task;



import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.module.ModuleName;

/**
 * Represents a Task of a TeachingAssistantBuddy in the TAB.
 */
public class Task {

    private final ModuleName moduleName;
    private final String name;
    private final String deadline;
    private boolean isComplete;

    /**
     * Constructs a Task.
     * @param moduleName The TeachingAssistantBuddy this Task is under.
     * @param name The Name of this Task.
     * @param deadline The Deadline of this Task.
     */
    public Task(ModuleName moduleName, String name, String deadline) {
        requireAllNonNull(moduleName, name, deadline);
        this.moduleName = moduleName;
        this.name = name;
        this.deadline = deadline;
        this.isComplete = false;
    }

    public String getModuleNameString() {
        return this.moduleName.toString();
    }

    public ModuleName getModuleName() {
        return this.moduleName;
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
                && otherTask.getName().equals(this.getName())
                && otherTask.getModuleNameString().equals(this.getModuleNameString());
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
        String completeStatus = isComplete ? "yes" : "no";
        builder.append(getName())
                .append("; Module: ")
                .append(getModuleNameString())
                .append("; Deadline: ")
                .append(getDeadline())
                .append("; Is completed: ")
                .append(completeStatus);
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
        return otherTask.getModuleNameString().equals(getModuleNameString())
                && otherTask.getName().equals(getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, moduleName, deadline);
    }

}
