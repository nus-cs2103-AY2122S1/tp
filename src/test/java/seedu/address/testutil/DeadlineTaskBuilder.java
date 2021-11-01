package seedu.address.testutil;

import seedu.address.model.task.DeadlineTask;

/**
 * A utility class to help with building Task objects.
 */
public class DeadlineTaskBuilder extends TaskBuilder {

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public DeadlineTaskBuilder() {
        super();
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public DeadlineTaskBuilder(DeadlineTask taskToCopy) {
        super(taskToCopy);
    }

    @Override
    public DeadlineTask build() {
        return new DeadlineTask(taskName, tags, isDone, taskDate, description, priority);
    }

}
