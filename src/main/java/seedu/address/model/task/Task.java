package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.util.Callback;


public class Task {

    public static final String MESSAGE_CONSTRAINTS =
            "Task should contain at least the task name.";

    /** Days prior to a task's date to remind user that its due soon. */
    private static int daysPriorToTaskDate = 3;

    private final TaskName taskName;
    private final TaskDate date;
    private final TaskTime time;
    private final Venue venue;
    private final BooleanProperty isDone;
    private final BooleanProperty isOverdue;
    private final BooleanProperty isDueSoon;

    /**
     * Constructor for task. Creates a new task with the given a String name.
     */
    public Task(TaskName taskName, TaskDate date, TaskTime time, Venue venue) {
        requireNonNull(taskName);

        this.isDone = new SimpleBooleanProperty(false);
        this.isOverdue = new SimpleBooleanProperty();
        this.isDueSoon = new SimpleBooleanProperty();

        this.taskName = taskName;
        this.date = date;
        this.time = time;
        this.venue = venue;
        updateDueDate();
    }

    /**
     * Creates a "dummy" {@code Task} for viewing all tasks.
     */
    public Task(String taskName) {
        requireNonNull(taskName);

        this.isDone = new SimpleBooleanProperty();
        this.isOverdue = new SimpleBooleanProperty();
        this.isDueSoon = new SimpleBooleanProperty();

        this.taskName = new TaskName(taskName);
        this.date = new TaskDate("2021-12-12");
        this.time = new TaskTime("23:59");
        this.venue = new Venue("dummy");
    }

    /**
     * {@code extractor} used for listView to detect changes in
     * {@code isOverdue} and {@code isDueSoon} variables of {@code Task}s.
     */
    public static Callback<Task, Observable[]> extractor() {
        return (Task t) -> new Observable[]{t.isOverdue, t.isDueSoon};
    }

    public static void setDaysPriorToTaskDate(int days) {
        daysPriorToTaskDate = days;
    }

    public static int getDaysPriorToTaskDate() {
        return daysPriorToTaskDate;
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
        return isDone.getValue();
    }

    public void setDone() {
        isDone.setValue(true);
    }

    public void setNotDone() {
        isDone.setValue(false);
    }

    public boolean getIsOverdue() {
        return isOverdue.getValue();
    }

    public boolean getIsDueSoon() {
        return isDueSoon.getValue();
    }

    public BooleanProperty getIsOverdueBooleanProperty() {
        return this.isOverdue;
    };

    public BooleanProperty getIsDueSoonBooleanProperty() {
        return this.isDueSoon;
    }

    /**
     * Updates if the task is overdue or due soon.
     */
    public void updateDueDate() {
        if (!isDone.getValue()) {
            LocalDate taskDate = date == null ? LocalDate.MAX : date.taskDate;
            LocalTime taskTime = time == null ? LocalTime.MIDNIGHT : time.taskTime;
            LocalDateTime taskDateTime = LocalDateTime.of(taskDate, taskTime);
            if (taskDateTime.isBefore(LocalDateTime.now())) { // Overdue
                isOverdue.setValue(true);
                isDueSoon.setValue(false);
            } else if (taskDateTime.isBefore(LocalDateTime.now().plusDays(daysPriorToTaskDate))) { // Due soon
                isOverdue.setValue(false);
                isDueSoon.setValue(true);
            } else {
                isDueSoon.setValue(false);
                isOverdue.setValue(false);
            }
        } else {
            isDueSoon.setValue(false);
            isOverdue.setValue(false);
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
                && ((otherTask.getDone()) == (getDone()))
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
