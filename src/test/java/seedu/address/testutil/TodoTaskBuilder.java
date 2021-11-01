package seedu.address.testutil;

import seedu.address.model.task.Task;
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
    public TodoTaskBuilder(Task taskToCopy) {
        super(taskToCopy);
    }

    @Override
    public TodoTask build() {
        return new TodoTask(taskName, tags, isDone, description, priority);
    }

}
