package seedu.address.testutil;

import seedu.address.model.task.Deadline;
import seedu.address.model.task.Name;
import seedu.address.model.task.Task;

public class TaskBuilder {
    public static final String DEFAULT_NAME = "Homework 1";
    public static final String DEFAULT_DEADLINE = "2000-01-01";

    private Name name;
    private Deadline deadline;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        this.name = new Name(DEFAULT_NAME);
        this.deadline = new Deadline(DEFAULT_DEADLINE);
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        name = taskToCopy.getName();
        deadline = taskToCopy.getDeadline();
    }

    /**
     * Sets the {@code Name} of the {@code Task} that we are building.
     */
    public TaskBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code Task} that we are building.
     */
    public TaskBuilder withDeadline(String deadline) {
        this.deadline = new Deadline(deadline);
        return this;
    }

    public Task build() {
        return new Task(name, deadline);
    }
}
