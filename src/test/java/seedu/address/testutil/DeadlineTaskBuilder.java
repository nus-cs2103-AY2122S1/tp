package seedu.address.testutil;

import seedu.address.model.task.DeadlineTask;
import seedu.address.model.task.TaskDate;

/**
 * A utility class to help with building Task objects.
 */
public class DeadlineTaskBuilder extends TaskBuilder {

    private TaskDate taskDate;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public DeadlineTaskBuilder() {
        super();
        taskDate = new TaskDate(TaskBuilder.DEFAULT_TASK_DATE);
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public DeadlineTaskBuilder(DeadlineTask taskToCopy) {
        super(taskToCopy);
        taskDate = new TaskDate(taskToCopy.getDeadline().toString());
    }

    @Override
    public DeadlineTask build() {
        return new DeadlineTask(taskName, tags, isDone, taskDate, description, priority);
    }

    /**
     * Sets the date of the task builder.
     *
     * @param newDate The updated date.
     * @return The taskBuilder.
     */
    public DeadlineTaskBuilder withDate(String newDate) {
        this.taskDate = new TaskDate(newDate);
        return this;
    }

    @Override
    public DeadlineTaskBuilder withName(String name) {
        return (DeadlineTaskBuilder) super.withName(name);
    }

    @Override
    public DeadlineTaskBuilder withTags(String... tags) {
        return (DeadlineTaskBuilder) super.withTags(tags);
    }

    @Override
    public DeadlineTaskBuilder withDescription(String description) {
        return (DeadlineTaskBuilder) super.withDescription(description);
    }

    @Override
    public DeadlineTaskBuilder withPriority(String description) {
        return (DeadlineTaskBuilder) super.withPriority(description);
    }
}
