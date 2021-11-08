package seedu.address.testutil;

import seedu.address.model.module.Name;
import seedu.address.model.module.task.Task;
import seedu.address.model.module.task.TaskDeadline;

/**
 * A utility class to help with building Task objects.
 */
public class TaskBuilder {
    public static final String DEFAULT_NAME = "team meeting";
    public static final boolean DEFAULT_IS_DONE = false;
    public static final String DEFAULT_DEADLINE = "27/10/2021 23:59";

    private Name taskName;
    private boolean isDone;
    private TaskDeadline taskDeadline;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        this.taskName = new Name(DEFAULT_NAME);
        this.isDone = DEFAULT_IS_DONE;
        this.taskDeadline = new TaskDeadline(DEFAULT_DEADLINE);
    }

    /**
     * Initializes the {@code TaskBuilder} with the data of {@code toCopy}.
     */
    public TaskBuilder(Task toCopy) {
        this.taskName = toCopy.getName();
        this.isDone = toCopy.isDone();
        this.taskDeadline = toCopy.getTaskDeadline();
    }

    /**
     * Sets the {@code taskName} of the {@code TaskBuilder} that we are building.
     */
    public TaskBuilder withName(String name) {
        this.taskName = new Name(name);
        return this;
    }

    /**
     * Sets the {@code taskDeadline} of the {@code TaskBuilder} that we are building.
     */
    public TaskBuilder withDeadline(String time) {
        this.taskDeadline = new TaskDeadline(time);
        return this;
    }

    /**
     * Sets the {@code isDone} of the {@code TaskBuilder} that we are building.
     */
    public TaskBuilder withIsDone(boolean isDone) {
        this.isDone = isDone;
        return this;
    }

    public Task build() {
        return new Task(taskName, isDone, taskDeadline);
    }
}
