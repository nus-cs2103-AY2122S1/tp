package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

public class Task {

    public static final String MESSAGE_CONSTRAINTS =
            "Task should contain at least the task name.";

    private final TaskName taskName;
    private final TaskDate date;
    private final TaskTime time;
    private final Venue venue;
    private boolean isDone = false;

    /**
     * Constructor for task. Creates a new task with the given a String name.
     */
    public Task (TaskName taskName, TaskDate date, TaskTime time, Venue venue) {
        requireNonNull(taskName);
        this.taskName = taskName;
        this.date = date;
        this.time = time;
        this.venue = venue;
    }

    public TaskName getTaskName() {
        return taskName;
    }

    public TaskDate getDate() {
        return date;
    }

    public TaskTime getTime() {
        return time;
    }

    public Venue getVenue() {
        return venue;
    }

    public boolean getDone() {
        return isDone;
    }

    public void setDone() {
        isDone = true;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Task)) {
            return false;
        }

        Task otherTask = (Task) other;
        boolean sameDate = otherTask.getDate() == null
                ? date == null
                : otherTask.getDate().equals(date);
        boolean sameTime = otherTask.getTime() == null
                ? time == null
                : otherTask.getTime().equals(time);
        boolean sameVenue = otherTask.getVenue() == null
                ? venue == null
                : otherTask.getVenue().equals(venue);
        return otherTask.getTaskName().equals(taskName)
                && otherTask.isDone == isDone
                && sameDate
                && sameTime
                && sameVenue;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTaskName())
                .append("; Date: ")
                .append(date == null ? "" : date)
                .append("; Time: ")
                .append(time == null ? "" : time)
                .append("; Venue: ")
                .append(venue == null ? "" : venue);

        return builder.toString();
    }
}
