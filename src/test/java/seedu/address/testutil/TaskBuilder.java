package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.tag.Tag;
import seedu.address.model.task.DeadlineTask;
import seedu.address.model.task.Description;
import seedu.address.model.task.EventTask;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskName;
import seedu.address.model.task.TodoTask;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Task objects.
 */
public abstract class TaskBuilder {

    public static final String DEFAULT_TASK_NAME = "Do CS2103 tP";
    public static final String DEFAULT_TASK_DATE = "2021-10-10";

    protected TaskName taskName;
    protected Set<Tag> tags;
    protected boolean isDone;
    protected Description description;
    protected Task.Priority priority;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        this.taskName = new TaskName(DEFAULT_TASK_NAME);
        this.tags = new HashSet<>();
        this.isDone = false;
        this.description = new Description("No Description");
        this.priority = Task.Priority.LOW;
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    protected TaskBuilder(Task taskToCopy) {
        this.taskName = new TaskName(taskToCopy.getName().toString());
        this.tags = new HashSet<>(taskToCopy.getTags());
        this.isDone = taskToCopy.checkIsDone();
        this.description = new Description(taskToCopy.getDescription());
        this.priority = taskToCopy.getPriority();
    }

    /**
     * Factory method that returns a TaskBuilder matching the TaskType of a given task.
     *
     * @param task The given task.
     * @return A Task Builder.
     */
    public static TaskBuilder of(Task task) {
        if (task instanceof TodoTask) {
            return new TodoTaskBuilder((TodoTask) task);
        } else if (task instanceof DeadlineTask) {
            return new DeadlineTaskBuilder((DeadlineTask) task);
        } else {
            return new EventTaskBuilder((EventTask) task);
        }
    }

    /**
     * Sets the {@code Name} of the {@code Task} that we are building.
     */
    public TaskBuilder withName(String name) {
        this.taskName = new TaskName(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Task} that we are building.
     */
    public TaskBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Parses the {@code description} into a {@code Task} and set it to the {@code Task} that we are building.
     */
    public TaskBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Parses the {@code description} into a {@code Task} and set it to the {@code Task} that we are building.
     */
    public TaskBuilder withPriority(String description) {
        if (description.contains("H") || description.contains("h")) {
            this.priority = Task.Priority.HIGH;
        } else if (description.contains("M") || description.contains("m")) {
            this.priority = Task.Priority.MEDIUM;
        } else {
            this.priority = Task.Priority.LOW;
        }
        return this;
    }

    /**
     * Builds a task with the fields in the TaskBuilder.
     * @return A task.
     */
    public abstract Task build();

}
