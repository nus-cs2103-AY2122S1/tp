package seedu.unify.testutil;

import seedu.unify.model.task.Date;
import seedu.unify.model.task.Name;
import seedu.unify.model.task.Tag;
import seedu.unify.model.task.Task;
import seedu.unify.model.task.Time;

/**
 * A utility class to help with building Task objects.
 */
public class TaskBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_TIME = "16:40";
    public static final String DEFAULT_DATE = "2021-12-11";
    public static final String DEFAULT_TAG = "Important";

    private Name name;
    private Time time;

    private Date date;
    private Tag tag;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        name = new Name(DEFAULT_NAME);
        time = new Time(DEFAULT_TIME);
        date = new Date(DEFAULT_DATE);
        tag = new Tag(DEFAULT_TAG);
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        name = taskToCopy.getName();
        time = taskToCopy.getTime();
        date = taskToCopy.getDate();
        tag = taskToCopy.getTag();
    }

    /**
     * Sets the {@code Name} of the {@code Task} that we are building.
     */
    public TaskBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code Task} that we are building.
     */
    public TaskBuilder withTag(String tag) {
        this.tag = new Tag(tag);
        return this;
    }


    /**
     * Sets the {@code Date} of the {@code Task} that we are building.
     */
    public TaskBuilder withDate(String date) {
        this.date = new Date(date);
        return this;
    }

    /**
     * Sets the {@code Time} of the {@code Task} that we are building.
     */
    public TaskBuilder withTime(String time) {
        this.time = new Time(time);
        return this;
    }

    public Task build() {
        return new Task(name, time, date, tag);
    }
}
