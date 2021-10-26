package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;
import seedu.address.model.task.Timestamp;
import seedu.address.model.util.SampleDataUtil;

public class TaskBuilder {
    public static final String DEFAULT_TITLE = "Buy groceries";
    public static final String DEFAULT_DESCRIPTION = "Two eggs, one carton of milk and five tomatoes";
    public static final Set<Tag> DEFAULT_TAGS = new HashSet<>();
    public static final boolean DEFAULT_IS_DONE = false;


    private String title;
    private String description;
    private Timestamp timestamp;
    private Set<Tag> tags;
    private boolean isDone;

    /**
     * Creates a new {@code TaskBuilder} instance initialized with default values.
     */
    public TaskBuilder() {
        this.title = DEFAULT_TITLE;
        this.description = DEFAULT_DESCRIPTION;
        try {
            this.timestamp = Timestamp.of("2021-10-27");
        } catch (ParseException pe) {
            //Never occurs
            this.timestamp = null;
        }
        this.tags = DEFAULT_TAGS;
        this.isDone = DEFAULT_IS_DONE;
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        title = taskToCopy.getTitle();
        description = taskToCopy.getDescription().orElse(null);
        timestamp = taskToCopy.getTimestamp().orElse(null);
        isDone = taskToCopy.isDone();
        tags = new HashSet<>(taskToCopy.getTags());
    }

    /**
     * Sets the {@code title} of the {@code Task} that we are building.
     */
    public TaskBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * Sets the {@code description} of the {@code Task} that we are building.
     */
    public TaskBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Sets the {@code timestamp} of the {@code Task} that we are building.
     */
    public TaskBuilder withTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
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
     * Sets whether the {@code task} we are building is {@code done}.
     */
    public TaskBuilder withDone(boolean done) {
        this.isDone = done;
        return this;
    }

    public Task build() {
        return new Task(title, description, timestamp, tags, isDone);
    }
}
