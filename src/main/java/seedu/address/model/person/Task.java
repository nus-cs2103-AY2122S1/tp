package seedu.address.model.person;

import java.time.DayOfWeek;

/**
 * Represents a piece of work for a staff.
 */
public class Task {
    private String taskName;
    private Slot slot;
    private DayOfWeek dayOfWeek;

    /**
     * Constructor of Task given its weekday, time, and name.
     * @param dayOfWeek Weekday of the task.
     * @param slot The slot when the task located.
     * @param taskName Name of the task.
     */
    public Task(DayOfWeek dayOfWeek, Slot slot, String taskName) {
        this.dayOfWeek = dayOfWeek;
        this.slot = slot;
        this.taskName = taskName;
    }

    /**
     * Constructor of Task given its weekday, time, and name.
     * @param dayOfWeek Weekday of the task.
     * @param slot The period when the task located.
     */
    public Task(DayOfWeek dayOfWeek, Slot slot) {
        this.dayOfWeek = dayOfWeek;
        this.slot = slot;
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
        return this.slot.getValue().equals("morning");
    }

    /**
     * Returns whether the task is happening in the afternoon.
     * @return whether the task is happening in the afternoon.
     */
    public boolean isAfternoon() {
        return this.slot.getValue().equals("afternoon");
    }


    @Override
    public String toString() {
        return taskName + ": in " + dayOfWeek.toString() + " " + slot.getValue();
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
