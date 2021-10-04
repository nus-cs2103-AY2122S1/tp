package seedu.address.model.person;

import java.time.DayOfWeek;

/**
 * Represents the schedule for the staff, which contains all the task for the staff.
 */
public class Schedule {
    private static final int DAY_OF_WEEK = 7;
    private static final int PERIOD_OF_DAY = 2;

    private Task[][] tasks;

    public Schedule() {
        this.tasks = new Task[DAY_OF_WEEK][PERIOD_OF_DAY];
        for (int day = 0; day < DAY_OF_WEEK; day++) {
            tasks[day][0] = null;
            tasks[day][1] = null;
        }
    }

    /**
     * Adds a new task for a staff.
     * @param dayOfWeek The day of the task in a week.
     * @param period The period of the task.
     * @param taskName The name of the task.
     */
    public void addTask(DayOfWeek dayOfWeek, Period period, String taskName) {
        Task task = new Task(dayOfWeek, period, taskName);
        tasks[dayOfWeek.getValue() - 1][period.getOrder()] = task;
    }

    /**
     * Removes a new task for a staff.
     * @param dayOfWeek The day of the task in a week.
     * @param period The period of the task.
     */
    public void removeTask(DayOfWeek dayOfWeek, Period period) {
        tasks[dayOfWeek.getValue() - 1][period.getOrder()] = null;
    }

    /**
     * Checks whether a staff is working in a certain period.
     * @param dayOfWeek The day want to check.
     * @param period The period want to check.
     */
    public boolean isWorking(DayOfWeek dayOfWeek, Period period) {
        return tasks[dayOfWeek.getValue() - 1][period.getOrder()] != null;
    }
}
