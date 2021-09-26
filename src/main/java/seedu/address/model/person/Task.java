package seedu.address.model.person;

import java.util.Date;

import seedu.address.model.person.exceptions.InvalidTimeException;

/**
 * Represents a piece of work for a staff.
 */
public class Task {
    private String taskName;
    private Time startTime;
    private Time endTime;
    private Date date;

    /**
     * Constructor of Task given date, startTime, endTime and taskName.
     * @param date Date of the task.
     * @param startTime StartTime of the task.
     * @param endTime EndTime of the task.
     * @param taskName Name of the task.
     */
    public Task(Date date, Time startTime, Time endTime, String taskName) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.taskName = taskName;
    }

    /**
     * Constructor of Task given date, startTime, and endTime.
     * @param date Date of the task.
     * @param startTime StartTime of the task.
     * @param endTime EndTime of the task.
     */
    public Task(Date date, Time startTime, Time endTime) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Constructor of Task given date, name and time represented by hour and min.
     * @param date Date of task.
     * @param startHour StartHour of task.
     * @param startMin StartMin of task.
     * @param endHour EndHour of task.
     * @param endMin EndMin of task.
     * @param taskName Name of task.
     * @throws InvalidTimeException Throws if the given hour and min are invalid.
     */
    public Task(Date date, int startHour, int startMin, int endHour, int endMin, String taskName)
            throws InvalidTimeException {
        this.date = date;
        this.startTime = new Time(startHour, startMin);
        this.endTime = new Time(endHour, endMin);
        this.taskName = taskName;
    }

    /**
     * Constructor of Task given date, and time represented by hour and min.
     * @param date Date of task.
     * @param startHour StartHour of task.
     * @param startMin StartMin of task.
     * @param endHour EndHour of task.
     * @param endMin EndMin of task.
     * @throws InvalidTimeException Throws if the given hour and min are invalid.
     */
    public Task(Date date, int startHour, int startMin, int endHour, int endMin) throws InvalidTimeException {
        this.date = date;
        this.startTime = new Time(startHour, startMin);
        this.endTime = new Time(endHour, endMin);
    }

    /**
     * Sets name of the task.
     * @param taskName Name of the task.
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Returns the startTime of the task.
     * @return The startTime of the task.
     */
    public Time getStartTime() {
        return startTime;
    }

    /**
     * Returns the endTime of the task.
     * @return The endTime of the task.
     */
    public Time getEndTime() {
        return endTime;
    }

    /**
     * Gets the duration of this task in String.
     *
     * @return the duration of this task in String.
     */
    public String getDurationHour() {
        return endTime.comparedToOther(startTime);
    }

    /**
     * Finds whether a time is inside the period of this task.
     *
     * @param time The time.
     * @return Whether the time is inside the period of this task.
     */
    public boolean inInterval(Time time) {
        return time.compareTo(startTime) >= 0 && time.compareTo(endTime) <= 0;
    }

    @Override
    public String toString() {
        return taskName + ": " + "From " + getStartTime() + " to " + getEndTime();
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
