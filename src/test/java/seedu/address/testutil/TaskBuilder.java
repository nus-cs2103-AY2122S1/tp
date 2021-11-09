package seedu.address.testutil;

import seedu.address.model.module.ModuleName;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDeadline;
import seedu.address.model.task.TaskId;
import seedu.address.model.task.TaskName;


public class TaskBuilder {

    public static final String DEFAULT_MODULE = "CS2103";
    public static final String DEFAULT_TASK_ID = "T1";
    public static final String DEFAULT_TASK_NAME = "Assignment 1";
    public static final String DEFAULT_TASK_DEADLINE = "2021-12-31";
    public static final Boolean DEFAULT_IS_COMPLETE = false;

    private ModuleName moduleName;
    private TaskId taskId;
    private TaskName taskName;
    private TaskDeadline taskDeadline;
    private boolean isComplete;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        moduleName = new ModuleName(DEFAULT_MODULE);
        taskId = new TaskId(DEFAULT_TASK_ID);
        taskName = new TaskName(DEFAULT_TASK_NAME);
        taskDeadline = new TaskDeadline(DEFAULT_TASK_DEADLINE);
        isComplete = DEFAULT_IS_COMPLETE;
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        moduleName = taskToCopy.getTaskModuleName();
        taskId = taskToCopy.getTaskId();
        taskName = taskToCopy.getTaskName();
        taskDeadline = taskToCopy.getTaskDeadline();
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
     * Sets the {@code Id} of the {@code Task} that we are building.
     */
    public TaskBuilder withId(String taskId) {
        this.taskId = new TaskId(taskId);
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code Task} that we are building.
     */
    public TaskBuilder withName(String taskName) {
        this.taskName = new TaskName(taskName);
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code Task} that we are building.
     */
    public TaskBuilder withDeadline(String taskDeadline) {
        this.taskDeadline = new TaskDeadline(taskDeadline);
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code Task} that we are building.
     */
    public TaskBuilder withStatus(Boolean isComplete) {
        this.isComplete = isComplete;
        return this;
    }

    /**
     * Build a Task based on the fields specified.
     * @return The Task object built.
     */
    public Task build() {
        return new Task(moduleName, taskId, taskName, taskDeadline, isComplete);
    }

}
