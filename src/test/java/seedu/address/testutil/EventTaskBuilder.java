package seedu.address.testutil;

import java.util.HashSet;

import seedu.address.model.task.Description;
import seedu.address.model.task.EventTask;
import seedu.address.model.task.TaskDate;

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
        super();
        super.taskName = taskToCopy.getName();
        super.taskDate = new TaskDate(taskToCopy.getTaskDate().toString());
        super.tags = new HashSet<>(taskToCopy.getTags());
        super.isDone = taskToCopy.checkIsDone();
        super.description = new Description(taskToCopy.getDescription());
    }

    @Override
    public EventTask build() {
        return new EventTask(taskName, tags, false, taskDate, description, priority);
    }

}
