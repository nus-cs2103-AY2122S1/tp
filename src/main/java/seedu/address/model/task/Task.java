package seedu.address.model.task;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.module.ModuleName;

/**
 * Represents a Task of a TeachingAssistantBuddy in the TAB.
 */
public class Task {
    private final ModuleName moduleName;
    private final TaskId taskId;
    private final TaskName taskName;
    private final TaskDeadline taskDeadline;
    private boolean isComplete;

    /**
     * Constructs a Task.
     * @param moduleName The name of the Module this Task is under.
     * @param taskId The ID of this task.
     * @param taskName The name of this task.
     * @param taskDeadline The Deadline of this Task.
     */
    public Task(ModuleName moduleName, TaskId taskId, TaskName taskName, TaskDeadline taskDeadline) {
        requireAllNonNull(moduleName, taskId, taskName, taskDeadline);
        this.moduleName = moduleName;
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskDeadline = taskDeadline;
        this.isComplete = false;
    }

    /**
     * Another constructor of a Task object that will initialise the isComplete field of a Task as specified.
     * @param moduleName The name of the Module this Task is under.
     * @param taskId The ID of this task.
     * @param taskName The name of this task.
     * @param taskDeadline The Deadline of this Task.
     * @param isComplete Whether this Task is complete or not.
     */
    public Task(ModuleName moduleName, TaskId taskId, TaskName taskName, TaskDeadline taskDeadline,
                boolean isComplete) {
        requireAllNonNull(moduleName, taskId, taskName, taskDeadline);
        this.moduleName = moduleName;
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskDeadline = taskDeadline;
        this.isComplete = isComplete;
    }

    public String getModuleNameString() {
        return this.moduleName.toString();
    }

    public ModuleName getTaskModuleName() {
        return this.moduleName;
    }

    public TaskId getTaskId() {
        return this.taskId;
    }

    public TaskName getTaskName() {
        return this.taskName;
    }

    public TaskDeadline getTaskDeadline() {
        return this.taskDeadline;
    }

    public boolean isComplete() {
        return this.isComplete;
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
                //&& otherTask.getTaskName().equals(this.getTaskName())
                && otherTask.getModuleNameString().equals(this.getModuleNameString())
                && otherTask.getTaskId().equals(this.getTaskId());
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
        builder.append(getTaskName())
                .append("; Module: ")
                .append(getModuleNameString())
                .append("; Deadline: ")
                .append(getTaskDeadline())
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
                && otherTask.getTaskName().equals(getTaskName())
                && otherTask.getTaskId().equals(getTaskId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(moduleName, taskId, taskName, taskDeadline);
    }

}
