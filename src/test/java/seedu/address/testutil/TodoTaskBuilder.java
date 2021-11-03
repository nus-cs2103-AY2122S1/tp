package seedu.address.testutil;

import seedu.address.model.task.TodoTask;

/**
 * A utility class to help with building Task objects.
 */
public class TodoTaskBuilder extends TaskBuilder {

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TodoTaskBuilder() {
        super();
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public TodoTaskBuilder(TodoTask taskToCopy) {
        super(taskToCopy);
    }

    @Override
    public TodoTask build() {
        return new TodoTask(taskName, tags, isDone, description, priority);
    }

    @Override
    public TodoTaskBuilder withName(String name) {
        return (TodoTaskBuilder) super.withName(name);
    }

    @Override
    public TodoTaskBuilder withTags(String... tags) {
        return (TodoTaskBuilder) super.withTags(tags);
    }

    @Override
    public TodoTaskBuilder withDescription(String description) {
        return (TodoTaskBuilder) super.withDescription(description);
    }

    @Override
    public TodoTaskBuilder withPriority(String description) {
        return (TodoTaskBuilder) super.withPriority(description);
    }
}
