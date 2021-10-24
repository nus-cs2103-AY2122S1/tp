package seedu.address.model.person;

import static seedu.address.commons.util.TimeUtil.TIME_FORMATTER;
import static seedu.address.commons.util.TimeUtil.isValidTime;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.exceptions.InvalidShiftTimeException;
import seedu.address.model.EmptyShift;

/**
 * Represents a piece of work for a staff.
 */
public class Shift {

    public static final String DELIMITER = "-";
    private static final LocalTime DEFAULT_MORNING_START_TIME = LocalTime.parse("10:00", TIME_FORMATTER);
    private static final LocalTime DEFAULT_MORNING_END_TIME = LocalTime.parse("16:00", TIME_FORMATTER);
    private static final LocalTime DEFAULT_AFTERNOON_START_TIME = LocalTime.parse("16:00", TIME_FORMATTER);
    private static final LocalTime DEFAULT_AFTERNOON_END_TIME = LocalTime.parse("22:00", TIME_FORMATTER);

    protected boolean isWorking;
    protected Slot slot;
    protected DayOfWeek dayOfWeek;
    protected List<Period> history = new ArrayList<>();
    private LocalDate startDate;
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
        this.startDate = LocalDate.now();
        isWorking = true;
        setDefaultTimings(slot.getOrder());
    }

    /**
     * Creates a shift at {@code dayOfWeek} in {@code Slot slot} at {@code LocalDate startDate}
     * with a history of changes {@code Set<Shift> history}.
     */
    public Shift(DayOfWeek dayOfWeek, Slot slot, LocalDate startDate, List<Period> history) {
        this.dayOfWeek = dayOfWeek;
        this.slot = slot;
        this.startDate = startDate;
        this.history.addAll(history);
        setDefaultTimings(slot.getOrder());
        isWorking = true;
    }


    public Slot getSlot() {
        return this.slot;
    }

    public DayOfWeek getDayOfWeek() {
        return this.dayOfWeek;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public List<Period> getHistory() {
        return Collections.unmodifiableList(history);
    }

    public LocalTime getStartTime() {
        return this.startTime;
    }

    public LocalTime getEndTime() {
        return this.endTime;
    }

    /**
     * Returns if this is an empty shift.
     *
     */
    public boolean isEmpty() {
        return !isWorking;
    }


    /**
     * Returns true if the input {@code endDate} can end this Shift.
     */
    public boolean canRemove(LocalDate endDate) {
        return this.startDate.isBefore(endDate)
                || startDate.equals(endDate);
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
     * Removes this shift by creating an {@code EmptyShift} in the place of it.
     */
    public Shift remove(LocalDate endDate) {
        assert endDate.isAfter(startDate);
        Period period = new Period(startDate, endDate);
        this.history.add(period);
        return new EmptyShift(dayOfWeek, slot, history);

    }

    /**
     * Makes this shift a working shift
     */
    public Shift activate(LocalDate startDate) {
        throw new UnsupportedOperationException("This method should not be called.");
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
        if (test.equals("")) {
            return false;
        }
        String[] stringSplit = test.split(DELIMITER);
        if (stringSplit.length != 4) {
            return false;
        }
        String dayString = stringSplit[0];
        String slotString = stringSplit[1];
        String startTimeString = stringSplit[2];
        String endTimeString = stringSplit[3];
        return isValidDayOfWeek(dayString)
                && Slot.isValidSlot(slotString)
                && isValidTime(startTimeString)
                && isValidTime(endTimeString);

    }



    /**
     * Returns a string of staff names that work on a specified shift. Result string is numbered and
     * has each staff in a new line.
     *
     * @param stafflist full list of staff in Staff'd.
     * @param day day of shift to be compared to.
     * @param time time of shift to be compared to.
     */
    public static String filterListByShift(ObservableList<Person> stafflist, DayOfWeek day, LocalTime time) {
        StringBuilder result = new StringBuilder();
        int counter = 1;
        for (Person p : stafflist) {
            boolean hasShift = p.isWorking(day, time);
            if (hasShift) {
                result.append(counter).append(". ").append(p.getName()).append("\n");
                counter++;
            }
        }
        return result.toString();
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
