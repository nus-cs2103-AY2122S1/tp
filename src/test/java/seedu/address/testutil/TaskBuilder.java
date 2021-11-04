package seedu.address.testutil;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDate;
import seedu.address.model.task.TaskName;
import seedu.address.model.task.TaskTime;
import seedu.address.model.task.Venue;

/**
 * A utility class to help with building Task objects.
 */
public class TaskBuilder {

    public static final String DEFAULT_TASK_NAME = "Project Meeting";
    public static final String DEFAULT_TASK_DATE = "2021-12-30";
    public static final String DEFAULT_TASK_TIME = "16:00";
    public static final String DEFAULT_VENUE = "Seminar Room 5";

    private TaskName taskName;
    private TaskDate date;
    private TaskTime time;
    private Venue venue;
    private BooleanProperty isDone;
    private BooleanProperty isOverdue;
    private BooleanProperty isDueSoon;

    /**
     * Creates a {@code TaskBuilder} with the default details
     */
    public TaskBuilder() {
        taskName = new TaskName(DEFAULT_TASK_NAME);
        date = new TaskDate(DEFAULT_TASK_DATE);
        time = new TaskTime(DEFAULT_TASK_TIME);
        venue = new Venue(DEFAULT_VENUE);
        isDone = new SimpleBooleanProperty(false);
        isOverdue = new SimpleBooleanProperty();
        isDueSoon = new SimpleBooleanProperty();
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        taskName = taskToCopy.getTaskName();
        date = taskToCopy.getDate();
        time = taskToCopy.getTime();
        venue = taskToCopy.getVenue();
        isDone = new SimpleBooleanProperty(taskToCopy.getDone());
    }

    /**
     * Sets the {@code TaskName} of the {@code Task} that we are building.
     */
    public TaskBuilder withName(String name) {
        this.taskName = new TaskName(name);
        return this;
    }

    /**
     * Sets the {@code TaskDate} of the {@code Task} that we are building.
     */
    public TaskBuilder withDate(String date) {
        this.date = new TaskDate(date);
        return this;
    }

    /**
     * Sets the {@code TaskTime} of the {@code Task} that we are building.
     */
    public TaskBuilder withTime(String time) {
        this.time = new TaskTime(time);
        return this;
    }

    /**
     * Sets the {@code Venue} of the {@code Task} that we are building.
     */
    public TaskBuilder withVenue(String venue) {
        this.venue = new Venue(venue);
        return this;
    }

    /**
     * Sets the isDone of the {@code Task} that we are building.
     */
    public TaskBuilder withDone(boolean done) {
        this.isDone = new SimpleBooleanProperty(done);
        return this;
    }

    /**
     * Returns a {@code Task} with details of TaskBuilder.
     *
     * @return a Task object.
     */
    public Task build() {
        Task task = new Task(taskName, date, time, venue);
        if (isDone.getValue()) {
            task.setDone();
        } else {
            task.setNotDone();
        }
        task.updateDueDate();

        return task;
    }
}
