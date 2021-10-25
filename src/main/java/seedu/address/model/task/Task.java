package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Task {

    public static final String MESSAGE_CONSTRAINTS =
            "Task should contain at least the task name.";
    private static int dueSoonThreshold = 3;

    private final TaskName taskName;
    private final TaskDate date;
    private final TaskTime time;
    private final Venue venue;
    private boolean isDone = false;
    private boolean isOverdue;
    private boolean isDueSoon;

    /**
     * Dummy constructor.
     */
    public Task(String name) {
        requireNonNull(name);
        this.taskName = new TaskName(name);
        date = new TaskDate("2021-10-10");
        time = new TaskTime("23:59");
        this.venue = new Venue("dummy");
    }

    /**
     * Constructor for task. Creates a new task with the given a String name.
     */
    public Task(TaskName taskName, TaskDate date, TaskTime time, Venue venue) {
        requireNonNull(taskName);
        this.taskName = taskName;
        this.date = date;
        this.time = time;
        this.venue = venue;
        updateDueDate();
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

    public void setNotDone() {
        isDone = false;
    }

    public boolean getIsOverdue() {
        return isOverdue;
    }

    public boolean getIsDueSoon() {
        return isDueSoon;
    }

    /**
     * Updates if the task is overdue or due soon.
     */
    public void updateDueDate() {
        if (!isDone) {
            LocalDate taskDate = date == null ? LocalDate.MAX : date.taskDate;
            LocalTime taskTime = time == null ? LocalTime.MIDNIGHT : time.taskTime;
            LocalDateTime taskDateTime = LocalDateTime.of(taskDate, taskTime);
            if (taskDateTime.isBefore(LocalDateTime.now())) { // Overdue
                isOverdue = true;
                isDueSoon = false;
            } else if (taskDateTime.isBefore(LocalDateTime.now().plusDays(dueSoonThreshold))) { // Due soon
                isOverdue = false;
                isDueSoon = true;
            } else {
                isDueSoon = false;
                isOverdue = false;
            }
        } else {
            isDueSoon = false;
            isOverdue = false;
        }
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
