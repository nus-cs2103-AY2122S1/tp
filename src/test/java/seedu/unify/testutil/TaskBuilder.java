package seedu.unify.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.unify.model.task.Tag;
import seedu.unify.model.task.Date;
import seedu.unify.model.task.Name;
import seedu.unify.model.task.Task;
import seedu.unify.model.task.Time;
import seedu.unify.model.util.SampleDataUtil;

/**
 * A utility class to help with building Task objects.
 */
public class TaskBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_TIME = "16:40";
    public static final String DEFAULT_DATE = "2021-12-11";

    private Name name;
    private Time time;

    private Date date;
    private Set<Tag> tags;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        name = new Name(DEFAULT_NAME);
        time = new Time(DEFAULT_TIME);
        date = new Date(DEFAULT_DATE);
        tags = new HashSet<>();
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        name = taskToCopy.getName();
        time = taskToCopy.getTime();
        date = taskToCopy.getDate();
        tags = new HashSet<>(taskToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Task} that we are building.
     */
    public TaskBuilder withName(String name) {
        this.name = new Name(name);
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
        return new Task(name, time, date, tags);
    }
}
