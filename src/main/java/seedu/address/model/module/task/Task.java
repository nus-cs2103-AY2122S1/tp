package seedu.address.model.module.task;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.module.Module;
import seedu.address.model.module.Name;

/**
 * Represents a task in the Ailurus.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Task extends Module {
    // data fields
    private final boolean isDone;
    private final TaskDeadline taskDeadline;

    /**
     * Constructs a {@code Task}.
     *
     * @param taskName A valid task name.
     */
    public Task(Name taskName, TaskDeadline taskDeadline) {
        super(taskName);
        requireAllNonNull(taskDeadline);
        this.isDone = false;
        this.taskDeadline = taskDeadline;
    }

    /**
     * Constructs a {@code Task}.
     *
     * @param taskName A valid task name.
     * @param isDone A boolean value.
     */
    public Task(Name taskName, boolean isDone, TaskDeadline taskDeadline) {
        super(taskName);
        requireAllNonNull(isDone, taskDeadline);
        this.isDone = isDone;
        this.taskDeadline = taskDeadline;
    }

    @Override
    public boolean isSameType(Module module) {
        return this.equals(module);
    }

    public boolean isDone() {
        return isDone;
    }

    public TaskDeadline getTaskDeadline() {
        return taskDeadline;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        Task task = (Task) other;
        boolean result = isDone == task.isDone && super.getName().equals(task.getName())
                && this.getTaskDeadline().equals(task.getTaskDeadline());
        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.getName(), isDone);
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return "(" + getStatusIcon() + ")" + super.getName();
    }
}
