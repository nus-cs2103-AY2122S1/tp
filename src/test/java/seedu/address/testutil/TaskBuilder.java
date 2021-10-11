package seedu.address.testutil;

import seedu.address.commons.core.id.UniqueId;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Name;
import seedu.address.model.task.Task;

public class TaskBuilder {
    public static final String DEFAULT_NAME = "Homework 1";
    public static final String DEFAULT_DEADLINE = "2000-01-01";
    public static final String DEFAULT_ID = "f31648db-5619-4bad-99d2-87367a2b5f28";

    private Name name;
    private Deadline deadline;
    private UniqueId uniqueId;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        this.name = new Name(DEFAULT_NAME);
        this.deadline = new Deadline(DEFAULT_DEADLINE);
        this.uniqueId = new UniqueId(DEFAULT_ID);
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        name = taskToCopy.getName();
        deadline = taskToCopy.getDeadline();
        uniqueId = taskToCopy.getId();
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

    /**
     * Sets the {@code UniqueId} of the {@code Task} that we are building.
     */
    public TaskBuilder withUniqueId(String uniqueId) {
        this.uniqueId = new UniqueId(uniqueId);
        return this;
    }

    public Task build() {
        return new Task(name, deadline, uniqueId);
    }
}
