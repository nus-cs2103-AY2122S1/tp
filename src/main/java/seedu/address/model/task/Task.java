package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.LocalTime;

public class Task {

    public static final String MESSAGE_CONSTRAINTS =
            "Task should contain at least the task name.";

    public final TaskName taskName;
    public final LocalDate date;
    public final LocalTime time;
    public final Venue venue;

    /**
     * Constructor for task. Creates a new task with the given a String name.
     */
    public Task (TaskName taskName, LocalDate date, LocalTime time, Venue venue) {
        requireNonNull(taskName);
        this.taskName = taskName;
        this.date = date;
        this.time = time;
        this.venue = venue;
    }

    public TaskName getTaskName() {
        return taskName;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public Venue getVenue() {
        return venue;
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
