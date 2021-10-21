package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.tag.Tag;
import seedu.address.model.task.Description;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDate;
import seedu.address.model.task.TaskName;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Task objects.
 */
public class TaskBuilder {

    public static final String DEFAULT_TASK_NAME = "Do CS2103 tP";
    public static final String DEFAULT_TASK_DATE = "2021-10-10";

    private TaskName taskName;
    private TaskDate taskDate;
    private Set<Tag> tags;
    private boolean isDone;
    private Description description;
    private Task.Priority priority;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        this.taskName = new TaskName(DEFAULT_TASK_NAME);
        this.taskDate = new TaskDate(DEFAULT_TASK_DATE);
        this.tags = new HashSet<>();
        this.isDone = false;
        this.description = new Description("No Description");
        this.priority = Task.Priority.LOW;
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        this.taskName = taskToCopy.getName();
        this.tags = new HashSet<>(taskToCopy.getTags());
        this.isDone = taskToCopy.checkIsDone();
        this.description = new Description(taskToCopy.getDescription());
    }

    /**
     * Sets the {@code Name} of the {@code Task} that we are building.
     */
    public TaskBuilder withName(String name) {
        this.taskName = new TaskName(name);
        return this;
    }

    /**
     * Sets the {@code TaskDate} of the {@code Task} that we are building.
     */
    public TaskBuilder withDeadline(String date) {
        this.taskDate = new TaskDate(date);
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
        this.priority = Task.Priority.LOW;
        return this;
    }

    public Task build() {
        return new Task(taskName, tags, false, description, priority);
    }

}
