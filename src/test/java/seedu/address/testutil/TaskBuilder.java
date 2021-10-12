package seedu.address.testutil;

import static seedu.address.model.id.UniqueId.DEFAULT_ID;

import seedu.address.model.id.UniqueId;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Description;
import seedu.address.model.task.Task;

public class TaskBuilder {
    public static final String DEFAULT_NAME = "Homework 1";
    public static final String DEFAULT_DEADLINE = "2000-01-01";

    private Description description;
    private Deadline deadline;
    private UniqueId uniqueId;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        this.description = new Description(DEFAULT_NAME);
        this.deadline = new Deadline(DEFAULT_DEADLINE);
        this.uniqueId = DEFAULT_ID;
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        description = taskToCopy.getName();
        deadline = taskToCopy.getDeadline();
        uniqueId = taskToCopy.getId();
    }

    /**
     * Sets the {@code Description} of the {@code Task} that we are building.
     */
    public TaskBuilder withName(String name) {
        this.description = new Description(name);
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
        Task task = new Task(description, deadline, uniqueId);
        uniqueId.setOwner(task);
        return task;
    }
}
