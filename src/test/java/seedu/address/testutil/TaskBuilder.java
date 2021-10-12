package seedu.address.testutil;

import static seedu.address.model.id.UniqueId.DEFAULT_ID;

import seedu.address.model.id.UniqueId;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Name;
import seedu.address.model.task.Task;

public class TaskBuilder {
    public static final String DEFAULT_NAME = "Homework 1";
    public static final String DEFAULT_DEADLINE = "2000-01-01";

    private Name name;
    private Deadline deadline;
    private UniqueId uniqueId;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        this.name = new Name(DEFAULT_NAME);
        this.deadline = new Deadline(DEFAULT_DEADLINE);
        this.uniqueId = DEFAULT_ID;
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
        this.uniqueId = UniqueId.generateId(uniqueId);
        return this;
    }

    /**
     * Builds a {@code Task} object from the {@code TaskBuilder}.
     *
     * @return A {@code Task} object.
     */
    public Task build() {
        return new Task(name, deadline, uniqueId);
    }
}
