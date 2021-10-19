package seedu.address.testutil;

import seedu.address.model.module.ModuleName;
import seedu.address.model.task.Task;


public class TaskBuilder {

    public static final String DEFAULT_MODULE = "CS2103";
    public static final String DEFAULT_NAME = "Assignment 1";
    public static final String DEFAULT_DEADLINE = "2021-12-31";
    public static final Boolean DEFAULT_IS_COMPLETE = false;

    private ModuleName moduleName;
    private String name;
    private String deadline;
    private boolean isComplete;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        moduleName = new ModuleName(DEFAULT_MODULE);
        name = DEFAULT_NAME;
        deadline = DEFAULT_DEADLINE;
        isComplete = DEFAULT_IS_COMPLETE;
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        moduleName = taskToCopy.getModuleName();
        name = taskToCopy.getName();
        deadline = taskToCopy.getDeadline();
        isComplete = taskToCopy.isComplete();
    }

    /**
     * Sets the {@code Module} of the {@code Task} that we are building.
     */
    public TaskBuilder withModule(String moduleName) {
        this.moduleName = new ModuleName(moduleName);
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code Task} that we are building.
     */
    public TaskBuilder withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code Task} that we are building.
     */
    public TaskBuilder withDeadline(String deadline) {
        this.deadline = deadline;
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code Task} that we are building.
     */
    public TaskBuilder withStatus(Boolean isComplete) {
        this.isComplete = isComplete;
        return this;
    }

    public Task build() {
        return new Task(moduleName, name, deadline);
    }

}
