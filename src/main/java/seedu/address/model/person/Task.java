package seedu.address.model.person;

import java.time.DayOfWeek;
import java.util.Date;

import seedu.address.model.person.exceptions.InvalidTimeException;

/**
 * Represents a piece of work for a staff.
 */
public class Task {
    private String taskName;
    private Period period;
    private DayOfWeek dayOfWeek;

    /**
     * Constructor of Task given its weekday, time, and name.
     * @param dayOfWeek Weekday of the task.
     * @param period The period when the task located.
     * @param taskName Name of the task.
     */
    public Task(DayOfWeek dayOfWeek, Period period, String taskName) {
        this.dayOfWeek = dayOfWeek;
        this.period = period;
        this.taskName = taskName;
    }

    /**
     * Constructor of Task given its weekday, time, and name.
     * @param dayOfWeek Weekday of the task.
     * @param period The period when the task located.
     */
    public Task(DayOfWeek dayOfWeek, Period period) {
        this.dayOfWeek = dayOfWeek;
        this.period = period;
    }


    /**
     * Sets name of the task.
     * @param taskName Name of the task.
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Returns whether the task is happening in the morning.
     * @return whether the task is happening in the morning.
     */
    public boolean isMorning() {
        return this.period.getValue().equals("morning");
    }

    /**
     * Returns whether the task is happening in the afternoon.
     * @return whether the task is happening in the afternoon.
     */
    public boolean isAfternoon() {
        return this.period.getValue().equals("afternoon");
    }


    @Override
    public String toString() {
        return taskName + ": in " + dayOfWeek.toString() + " " + period.getValue();
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
        return toString().equals(otherTask.toString());
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}
