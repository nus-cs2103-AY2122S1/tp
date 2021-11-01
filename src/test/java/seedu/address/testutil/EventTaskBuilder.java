package seedu.address.testutil;

import seedu.address.model.task.EventTask;

public class EventTaskBuilder extends TaskBuilder {

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public EventTaskBuilder() {
        super();
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public EventTaskBuilder(EventTask taskToCopy) {
        super(taskToCopy);
    }

    @Override
    public EventTask build() {
        return new EventTask(taskName, tags, isDone, taskDate, description, priority);
    }

}
