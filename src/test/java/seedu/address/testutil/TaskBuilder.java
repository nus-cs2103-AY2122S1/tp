package seedu.address.testutil;

import seedu.address.model.task.Date;
import seedu.address.model.task.Label;
import seedu.address.model.task.Task;

/**
 * A utility class to help with building Task objects.
 */
public class TaskBuilder {

    public static final String DEFAULT_LABEL = "Buy Cloth";
    public static final String DEFAULT_DATE = "12th September 2021";

    private Label label;
    private Date date;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        label = new Label(DEFAULT_LABEL);
        date = new Date(DEFAULT_DATE);
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        label = taskToCopy.getLabel();
        date = taskToCopy.getDate();
    }

    /**
     * Sets the {@code Label} of the {@code Task} that we are building.
     */
    public TaskBuilder withLabel(String label) {
        this.label = new Label(label);
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code Task} that we are building.
     */
    public TaskBuilder withDate(String date) {
        this.date = new Date(date);
        return this;
    }

    public Task build() {
        return new Task(label, date);
    }

}
