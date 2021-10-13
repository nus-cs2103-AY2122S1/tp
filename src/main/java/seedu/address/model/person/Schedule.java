package seedu.address.model.person;

import java.time.DayOfWeek;
import java.util.Objects;

import seedu.address.model.person.exceptions.DuplicateShiftException;

/**
 * Represents the schedule for the staff, which contains all the task for the staff.
 */
public class Schedule {

    public static final String MESSAGE_CONSTRAINTS = "Schedule json string error! Invalid format."; //todo idek if need

    private static final int DAY_OF_WEEK = 7;
    private static final int PERIOD_OF_DAY = 2;


    private static final String SCHEDULE_DEFAULT = "Schedule:\n"
            + "Monday: %1$s\n"
            + "Tuesday: %2$s\n"
            + "Wednesday: %3$s\n"
            + "Thursday: %4$s\n"
            + "Friday: %5$s\n"
            + "Saturday: %6$s\n"
            + "Sunday: %7$s\n";

    private Shift[][] shifts = new Shift[7][2];


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
     * Alternate constructor for schedule object.
     */
    public Schedule(String loadString) {
        String[] shiftArray = loadString.split(" ");
        for (String s : shiftArray) {
            String[] shiftString = s.split("-");
            DayOfWeek shiftDay = DayOfWeek.valueOf(shiftString[0]);
            Slot shiftSlot = Slot.translateStringToSlot(shiftString[1]);
            shifts[shiftDay.getValue() - 1][shiftSlot.getOrder()] = new Shift(shiftDay, shiftSlot);
        }
    }

    /**
     * Adds a new shift for a staff.
     *
     * @param dayOfWeek The day of the shift in a week.
     * @param slot The slot of the shift located.
     * @throws DuplicateShiftException throws when there is already a shift in the target slot.
     */
    public void addShift(DayOfWeek dayOfWeek, Slot slot) throws DuplicateShiftException {
        Shift shift = new Shift(dayOfWeek, slot);
        if (shifts[dayOfWeek.getValue() - 1][slot.getOrder()] != null) {
            throw new DuplicateShiftException();
        }
        shifts[dayOfWeek.getValue() - 1][slot.getOrder()] = shift;
    }

    /**
     * Removes a new task for a staff.
     *
     * @param dayOfWeek The day of the shift in a week.
     * @param slot The period of the shift.
     */
    public void removeShift(DayOfWeek dayOfWeek, Slot slot) {
        shifts[dayOfWeek.getValue() - 1][slot.getOrder()] = null;
    }

    /**
     * Checks whether a staff is working in a certain period.
     *
     * @param dayOfWeek The day want to check.
     * @param slot The period want to check.
     */
    public boolean isWorking(DayOfWeek dayOfWeek, Slot slot) {
        return shifts[dayOfWeek.getValue() - 1][slot.getOrder()] != null;
    }

    /**
     * Creates the shift array in a legible text output.
     * @param shifts The shifts in to format.
     *
     * @return The string format to display.
     */
    private static String formatShiftsToString(Shift[] shifts) {
        String result = "";
        for (Shift shift: shifts) {
            if (shift == null) {
                continue;
            }
            result += "\n\t";
            result += shift;

        }
        return result;

    }

    /**
     * Method to display the schedule in a palatable manner.
     *
     * @return The displayed schedule.
     */
    public String toViewScheduleString() {
        return String.format(SCHEDULE_DEFAULT,
                formatShiftsToString(shifts[0]),
                formatShiftsToString(shifts[1]),
                formatShiftsToString(shifts[2]),
                formatShiftsToString(shifts[3]),
                formatShiftsToString(shifts[4]),
                formatShiftsToString(shifts[5]),
                formatShiftsToString(shifts[6]));

    }

    /**
     * Returns String representation of the schedule object.
     *
     * @return String representation of all existing shifts in the schedule, concatenated with a whitespace.
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for (Shift[] innerArray : shifts) {
            for (Shift s : innerArray) {
                if (!Objects.isNull(s)) {
                    builder.append(s.toSaveString());
                }
            }
        }
        return builder.toString();
    }

    /**
     * Returns if a given string is a valid scheduleString.
     */
    public static boolean isValidSchedule(String test) {
        if (test.equals("")) {
            return true;
        }
        String[] shiftSplit = test.split(" ");
        for (String s : shiftSplit) {
            if (!Shift.isValidShift(s)) {
                return false;
            }
        }
        return true;
    }

}
