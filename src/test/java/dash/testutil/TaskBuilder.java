package dash.testutil;

import java.util.HashSet;
import java.util.Set;

import dash.model.person.Person;
import dash.model.tag.Tag;
import dash.model.task.CompletionStatus;
import dash.model.task.Task;
import dash.model.task.TaskDate;
import dash.model.task.TaskDescription;
import dash.model.util.SampleDataUtil;

/**
 * A utility class to help with building Task objects.
 */
public class TaskBuilder {
    public static final String DEFAULT_DESCRIPTION = "Complete CS2103T Project";

    private TaskDescription taskDescription;
    private CompletionStatus completionStatus;
    private TaskDate taskDate;
    private Set<Person> people;
    private Set<Tag> tags;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        taskDescription = new TaskDescription(DEFAULT_DESCRIPTION);
        completionStatus = new CompletionStatus(false);
        taskDate = new TaskDate();
        people = new HashSet<>();
        tags = new HashSet<>();
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        taskDescription = taskToCopy.getTaskDescription();
        completionStatus = taskToCopy.getCompletionStatus();
        taskDate = taskToCopy.getTaskDate();
        people = new HashSet<>(taskToCopy.getPeople());
        tags = new HashSet<>(taskToCopy.getTags());
    }

    /**
     * Sets the {@code TaskDescription} of the {@code Task} that we are building.
     */
    public TaskBuilder withTaskDescription(String taskDescription) {
        this.taskDescription = new TaskDescription(taskDescription);
        return this;
    }

    /**
     * Sets the {@code CompletionStatus} of the {@code Task} that we are building.
     */
    public TaskBuilder withCompletionStatus(boolean isComplete) {
        this.completionStatus = new CompletionStatus(isComplete);
        return this;
    }

    /**
     * Sets the {@code TaskDate} of the {@code Task} that we are building.
     */
    public TaskBuilder withTaskDate(String taskDate) {
        this.taskDate = new TaskDate(taskDate, false);
        return this;
    }

    /**
     * Sets the {@code People} of the {@code Task} that we are building.
     */
    public TaskBuilder withPeople(Person... people) {
        this.people = SampleDataUtil.getPersonSet(people);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Task} that we are building.
     */
    public TaskBuilder withTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    public Task build() {
        return new Task(taskDescription, completionStatus, taskDate, people, tags);
    }

}
