package seedu.address.model.person;

import static seedu.address.commons.util.TimeUtil.TIME_FORMATTER;
import static seedu.address.commons.util.TimeUtil.isValidTime;

import java.time.DayOfWeek;
import java.time.LocalTime;

import seedu.address.commons.exceptions.InvalidShiftTimeException;

/**
 * Represents a piece of work for a staff.
 */
public class Shift {

    private static final LocalTime DEFAULT_MORNING_START_TIME = LocalTime.parse("10:00", TIME_FORMATTER);
    private static final LocalTime DEFAULT_MORNING_END_TIME = LocalTime.parse("16:00", TIME_FORMATTER);
    private static final LocalTime DEFAULT_AFTERNOON_START_TIME = LocalTime.parse("16:00", TIME_FORMATTER);
    private static final LocalTime DEFAULT_AFTERNOON_END_TIME = LocalTime.parse("22:00", TIME_FORMATTER);

    private Slot slot;
    private DayOfWeek dayOfWeek;

    private LocalTime startTime;
    private LocalTime endTime;

    /**
     * Constructor of Task given its weekday, time, and name.
     *
     * @param dayOfWeek Weekday of the shift.
     * @param slot The slot when the shift located.
     */
    public Shift(DayOfWeek dayOfWeek, Slot slot) {
        this.dayOfWeek = dayOfWeek;
        this.slot = slot;
        if (slot.equals(Slot.MORNING)) {
            setDefaultTimings(0);
        } else {
            setDefaultTimings(1);
        }
    }

    /**
     * Returns whether the shift is happening in the morning.
     *
     * @return whether the shift is happening in the morning.
     */
    public boolean isInMorning() {
        return this.slot.getValue().equals("morning");
    }

    /**
     * Returns whether the shift is happening in the afternoon.
     *
     * @return whether the shift is happening in the afternoon.
     */
    public boolean isInAfternoon() {
        return this.slot.getValue().equals("afternoon");
    }

    public boolean isWorking(LocalTime time) {
        return isWithinSlotPeriod(time);
    }



    /**
     * Returns a String representation of the shift that is suitable for json.
     */
    public String toSaveString() {
        return dayOfWeek.toString() + "-" + slot.getValue() + "-" + startTime.toString() + "-"
                + endTime.toString() + " ";
    }

    /**
     * Returns if a given string is a valid DayOfWeek.
     */
    public static boolean isValidDayOfWeek(String test) {
        for (DayOfWeek d :DayOfWeek.values()) {
            if (d.toString().equalsIgnoreCase(test)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if the timing for a shift is valid, and then update them.
     * @param startTime Start time of the shift
     * @param endTime End time of the shift
     * @param order Indicate the shift is in the morning slot or the afternoon slot.
     * @throws InvalidShiftTimeException Throws when the timing is invalid. There are two cases, one is the startTime
     * is later then the endTime, the other is that the time is out of the bound of the default one.
     */
    public void setTime(LocalTime startTime, LocalTime endTime, int order) throws InvalidShiftTimeException {
        if (startTime.isAfter(endTime)) {
            throw new InvalidShiftTimeException();
        }
        if (order == 0) {
            if (startTime.isBefore(DEFAULT_MORNING_START_TIME) || endTime.isAfter(DEFAULT_MORNING_END_TIME)) {
                throw new InvalidShiftTimeException();
            }
        } else {
            if (startTime.isBefore(DEFAULT_AFTERNOON_START_TIME) || endTime.isAfter(DEFAULT_AFTERNOON_END_TIME)) {
                throw new InvalidShiftTimeException();
            }
        }
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public void setDefaultTimings(int order) {
        // TODO make this more flexible to change the timings if necessary
        // Currently, assumed that morning shift is 10 - 4.00pm, night shift is 4 - 10
        // Can consider having a settings page or smt pop out so they can set this?
        if (order == 0) {
            this.startTime = DEFAULT_MORNING_START_TIME;
            this.endTime = DEFAULT_MORNING_END_TIME;
        } else if (order == 1) {
            this.startTime = DEFAULT_AFTERNOON_START_TIME;
            this.endTime = DEFAULT_AFTERNOON_END_TIME;
        } else {
            System.out.println("Check the slot class"); //TODO make sure the order is checked before a slot is created
        }
    }

    /**
     * Checks if a specified timing is within the slot period.
     *
     * @param time The time which will be checked against.
     * @return
     */
    public boolean isWithinSlotPeriod(LocalTime time) {
        return time.equals(startTime) || time.equals(endTime)
                || time.isBefore(endTime) && time.isAfter(startTime);
    }

    /**
     * Returns if a given string is a valid shift.
     */
    public static boolean isValidShift(String test) {
        boolean resultBoolean = false;
        if (test.equals("")) {
            return false;
        }
        String[] stringSplit = test.split("-");
        if (stringSplit.length == 4) {
            String dayString = stringSplit[0];
            String slotString = stringSplit[1];
            String startTimeString = stringSplit[2];
            String endTimeString = stringSplit[3];
            resultBoolean = isValidDayOfWeek(dayString);
            resultBoolean &= Slot.isValidSlot(slotString);
            resultBoolean &= isValidTime(startTimeString);
            resultBoolean &= isValidTime(endTimeString);
        }
        return resultBoolean;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Shift)) {
            return false;
        }
        Shift otherShift = (Shift) other;
        return slot.equals(otherShift.slot) && dayOfWeek.equals(otherShift.dayOfWeek)
                && startTime.equals(otherShift.startTime)
                && endTime.equals(otherShift.endTime);
    }

    @Override
    public String toString() {
        return this.dayOfWeek.toString() + " " + this.slot.toString()
                + "From " + startTime.toString() + " to " + endTime.toString();
    }
}
