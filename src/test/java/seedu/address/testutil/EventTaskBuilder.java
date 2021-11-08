package seedu.address.testutil;

import seedu.address.model.task.EventTask;
import seedu.address.model.task.TaskDate;

public class EventTaskBuilder extends TaskBuilder {

    private TaskDate taskDate;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public EventTaskBuilder() {
        super();
        taskDate = new TaskDate(TaskBuilder.DEFAULT_TASK_DATE);
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public EventTaskBuilder(EventTask taskToCopy) {
        super(taskToCopy);
        taskDate = new TaskDate(taskToCopy.getTaskDate().toString());
    }

    @Override
    public EventTask build() {
        return new EventTask(taskName, tags, isDone, taskDate, description, priority);
    }

    /**
     * Sets the date of the task builder.
     *
     * @param newDate The updated date.
     * @return The taskBuilder.
     */
    public EventTaskBuilder withDate(String newDate) {
        this.taskDate = new TaskDate(newDate);
        return this;
    }

    @Override
    public EventTaskBuilder withName(String name) {
        return (EventTaskBuilder) super.withName(name);
    }

    @Override
    public EventTaskBuilder withTags(String... tags) {
        return (EventTaskBuilder) super.withTags(tags);
    }

    @Override
    public EventTaskBuilder withDescription(String description) {
        return (EventTaskBuilder) super.withDescription(description);
    }

    @Override
    public EventTaskBuilder withPriority(String description) {
        return (EventTaskBuilder) super.withPriority(description);
    }
}
