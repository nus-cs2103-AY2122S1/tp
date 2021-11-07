package seedu.address.model.module.task;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
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

    /**
     * Returns true if both tasks have the same name and the same deadline.
     * This defines a weaker notion of equality between two tasks.
     */
    @Override
    public boolean isSameType(Module otherTask) {
        if (otherTask == this) {
            return true;
        } else if (otherTask instanceof Task) {
            return this.getName().equals(otherTask.getName())
                    && this.getTaskDeadline().equals(((Task) otherTask).getTaskDeadline());
        } else {
            return false;
        }
    }

    public boolean isDone() {
        return isDone;
    }

    public TaskDeadline getTaskDeadline() {
        return taskDeadline;
    }

    /**
     * Returns the deadline date of the {@code Task}.
     *
     * @return date
     */
    public LocalDateTime getTaskDeadlineDate() {
        return taskDeadline.getDeadline();
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
        return super.getName().toString() + " by " + getTaskDeadline().toString() + " ";
    }
}
