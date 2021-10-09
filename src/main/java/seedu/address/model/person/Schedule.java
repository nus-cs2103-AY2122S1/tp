package seedu.address.model.person;

import java.time.DayOfWeek;

/**
 * Represents the schedule for the staff, which contains all the task for the staff.
 */
public class Schedule {
    private static final int DAY_OF_WEEK = 7;
    private static final int PERIOD_OF_DAY = 2;

    private Shift[][] shifts;

    /**
     * Initialize schedule object.
     */
    public Schedule() {
        this.shifts = new Shift[DAY_OF_WEEK][PERIOD_OF_DAY];
        for (int day = 0; day < DAY_OF_WEEK; day++) {
            shifts[day][0] = null;
            shifts[day][1] = null;
        }
    }

    /**
     * Adds a new shift for a staff.
     * @param dayOfWeek The day of the shift in a week.
     * @param slot The slot of the shift located.
     * @param shiftName The name of the shift.
     */
    public void addTask(DayOfWeek dayOfWeek, Slot slot, String shiftName) {
        Shift shift = new Shift(dayOfWeek, slot, shiftName);
        shifts[dayOfWeek.getValue() - 1][slot.getOrder()] = shift;
    }

    /**
     * Removes a new task for a staff.
     * @param dayOfWeek The day of the task in a week.
     * @param slot The period of the task.
     */
    public void removeTask(DayOfWeek dayOfWeek, Slot slot) {
        shifts[dayOfWeek.getValue() - 1][slot.getOrder()] = null;
    }

    /**
     * Checks whether a staff is working in a certain period.
     * @param dayOfWeek The day want to check.
     * @param slot The period want to check.
     */
    public boolean isWorking(DayOfWeek dayOfWeek, Slot slot) {
        return shifts[dayOfWeek.getValue() - 1][slot.getOrder()] != null;
    }
}
