package seedu.address.model.person;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import seedu.address.model.EmptyShift;

/**
 * Represents a piece of work for a staff.
 */
public class Shift {
    public static final String DELIMITER = "-";
    private static final int NUM_OF_VALUES = 2;


    protected boolean isWorking;
    protected Slot slot;
    protected DayOfWeek dayOfWeek;
    protected List<Period> history = new ArrayList<>();
    private LocalDate startDate;




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
        return slot.isWithinSlotPeriod(time);
    }

    @Override
    public String toString() {
        return dayOfWeek.toString() + "-" + slot.getValue() + ": " + slot.toString();
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
     * Returns if a given string is a valid shift.
     */
    public static boolean isValidShift(String test) {
        if (test.equals("")) {
            return false;
        }
        String[] stringSplit = test.split(DELIMITER);
        if (stringSplit.length != NUM_OF_VALUES) {
            return false;
        }
        String dayString = stringSplit[0];
        String slotString = stringSplit[1];
        return isValidDayOfWeek(dayString)
                && Slot.isValidSlot(slotString);

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
        return slot.equals(otherShift.slot) && dayOfWeek.equals(otherShift.dayOfWeek);
    }



}
