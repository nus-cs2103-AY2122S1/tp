package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.person.Shift.isValidEmpty;
import static seedu.address.model.person.Shift.isValidShift;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import seedu.address.model.person.exceptions.DuplicateShiftException;
import seedu.address.model.person.exceptions.NoShiftException;

/**
 * Represents the schedule for the staff, which contains all the task for the staff.
 */
public class Schedule {

    public static final String MESSAGE_CONSTRAINTS = "Schedule json string error! Invalid format."; //todo idek if need
    public static final int HOURS_PER_SLOT = 4;

    private static final int DAY_OF_WEEK = 7;
    private static final int PERIOD_OF_DAY = 2;
    // Set the number of hours for a slot as 4 hours



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
        if (!isValidSchedule(loadString)) {
            throw new IllegalArgumentException("String does not match a valid schedule");
        }
        String[] shiftArray = loadString.split(" ");
        for (String s : shiftArray) {
            if (isValidShift(s)) {

            }
            if (isValidEmpty(s)) {

            }
            String[] shiftString = s.split("-");
            String shiftDayString = shiftString[0].toUpperCase();
            DayOfWeek shiftDay = DayOfWeek.valueOf(shiftDayString);
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
    public void addShift(DayOfWeek dayOfWeek, Slot slot, LocalDate startDate) throws DuplicateShiftException {
        Shift shift = new Shift(dayOfWeek, slot);
        Shift shift1 = shifts[dayOfWeek.getValue() - 1][slot.getOrder()];

        if (shift1 == null) {
            shifts[dayOfWeek.getValue() - 1][slot.getOrder()] = shift;
            return;
        }
        if (shift1.isWorking) {
            throw new DuplicateShiftException();
        }
        shifts[dayOfWeek.getValue() - 1][slot.getOrder()] = shift1.activate(startDate);
    }

    /**
     * Removes a new task for a staff.
     *
     * @param dayOfWeek The day of the shift in a week.
     * @param slot The period of the shift.
     * @param endDate The date the endDate is at.
     * @throws NoShiftException throws when a user tries to delete a shift that does not exist.
     */
    public void removeShift(DayOfWeek dayOfWeek, Slot slot, LocalDate endDate) throws NoShiftException {
        Shift shift = shifts[dayOfWeek.getValue() - 1][slot.getOrder()];
        if (shift == null || !shift.isWorking || shift.canRemove(endDate)) {
            throw new NoShiftException();
        }
        shifts[dayOfWeek.getValue() - 1][slot.getOrder()] = shift.remove(endDate);
    }

    /**
     * Checks whether a staff is working in a certain period.
     *
     * @param dayOfWeek The day want to check.
     * @param slot The period want to check.
     */
    public boolean isWorking(DayOfWeek dayOfWeek, Slot slot) {
        return isWorking(dayOfWeek, slot.getOrder());
    }

    /**
     * Checks whether a staff is working in a certain period.
     *
     * @param dayOfWeek The day want to check.
     * @param slotNum The slot number want to check.
     */
    public boolean isWorking(DayOfWeek dayOfWeek, int slotNum) {
        // TODO change from slots 0 and 1 to checking by a specific timing?
        return shifts[dayOfWeek.getValue() - 1][slotNum] != null
                && shifts[dayOfWeek.getValue() - 1][slotNum].isWorking;
    }

    /**
     * Checks whether a staff is working in a certain period.
     *
     * @param time The time to check if the staff is working at
     */
    public boolean isWorking(DayOfWeek dayOfWeek, LocalTime time) {
        for (Shift s : shifts[dayOfWeek.getValue() - 1]) {
            if (s == null) {
                continue;
            }
            if (s.isWorking(time)) {
                return true;
            }
        }
        return false;
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
     * Calculates the total working hours os one schedule.
     *
     * @return The total working hours.
     */
    public int getTotalWorkingHour() {
        int totalHours = 0;
        for (Shift[] dayShifts : shifts) {
            for (Shift shift : dayShifts) {
                if (shift != null) {
                    totalHours += HOURS_PER_SLOT;
                }
            }
        }
        return totalHours;
    }

    /**
     * Calculates the total working hours over {@code Period period}
     * of this schedule, while removing {@code Collection<Period> absentPeriods} from the count.
     */
    public int getTotalWorkingHour(Period period, Collection<Period> absentPeriods) {
        requireNonNull(period);
        requireNonNull(absentPeriods);
        int totalHours = 0;
        List<LocalDate> datesNotCounted = absentPeriods
                .stream()
                .flatMap(p -> p.toList().stream())
                .collect(Collectors.toList());
        List<LocalDate> dates = period.toList();
        for (LocalDate date : dates) {
            if (datesNotCounted.contains(date)) {
                continue;
            }
            //test both slots in a date
            if (isWorking(date.getDayOfWeek(), Slot.MORNING)) {
                totalHours += HOURS_PER_SLOT;
            }

            if (isWorking(date.getDayOfWeek(), Slot.AFTERNOON)) {
                totalHours += HOURS_PER_SLOT;
            }
        }
        return totalHours;
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
            if (!isValidShift(s) && !isValidEmpty(s)) {
                return false;
            }
        }
        return true;
    }

}
