package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Represents a piece of work for a staff.
 */
public class Shift {
    private static final String WORKING_SHIFT_IDENTIFIER = "W";
    private static final String EMPTY_SHIFT_IDENTIFIER = "E";
    private static final String DELIMITER = "-";
    private static final int NUM_OF_VALUES = 5;
    private Slot slot;
    private DayOfWeek dayOfWeek;
    private LocalDate startDate;
    private Period period;
    protected List<Shift> history = new ArrayList<>();
    public boolean isWorking;

    private class EmptyShift extends Shift {

        public EmptyShift(DayOfWeek dayOfWeek, Slot slot, List<Shift> history) {
            super(dayOfWeek, slot);
            this.history.addAll(history);
            isWorking = false;

        }

        @Override
        public boolean isWorking(LocalTime time) {
            return false;
        }

        @Override
        public Shift activate(LocalDate startDate) {
            return new Shift(dayOfWeek, slot, startDate, history);
        }

        @Override
        public Shift remove(LocalDate endDate) {
            throw new UnsupportedOperationException("This method should not be called.");
        }

        @Override
        public String toSaveString() {
            return EMPTY_SHIFT_IDENTIFIER + DELIMITER +  dayOfWeek.toString()
                    + DELIMITER + slot.getValue() + DELIMITER + history;
        }
    }


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
    public Shift(DayOfWeek dayOfWeek, Slot slot, LocalDate startDate, List<Shift> history) {
        this.dayOfWeek = dayOfWeek;
        this.slot = slot;
        this.startDate = startDate;
        this.history.addAll(history);
        isWorking = true;
    }

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
     * Returns a String representation of the shift that is suitable for json.
     */
    public String toSaveString() {
        return WORKING_SHIFT_IDENTIFIER + DELIMITER + dayOfWeek.toString() + DELIMITER
                + slot.getValue() + DELIMITER + this.startDate + DELIMITER + history;
    }

    /**
     * Removes this shift by creating an {@code EmptyShift} in the place of it.
     */
    public Shift remove(LocalDate endDate) {
        assert endDate.isAfter(startDate);
        this.period = new Period(startDate, endDate);
        this.history.add(this);
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
        if (!stringSplit[0].equals(WORKING_SHIFT_IDENTIFIER)) {
            return false;
        }
        if (stringSplit.length != NUM_OF_VALUES) {
            return false;
        }
        String dayString = stringSplit[1];
        String slotString = stringSplit[2];
        return isValidDayOfWeek(dayString)
                && Slot.isValidSlot(slotString);

    }

    /**
     * Returns if a given string is a valid empty shift.
     */
    public static boolean isValidEmpty(String test) {
        requireNonNull(test);
        if (test.equals("")) {
            return false;
        }
        String[] stringSplit = test.split(DELIMITER);
        if (!stringSplit[0].equals(EMPTY_SHIFT_IDENTIFIER)) {
            return false;
        }
        if (stringSplit.length != (NUM_OF_VALUES - 1)) {
            return false;
        }
        String slotString = stringSplit[1];
        return Slot.isValidSlot(slotString);
    }


    /**
     * Translates the {@code input} into a shift.
     */
    public static Shift translateStringToShift(String input) {
        requireNonNull(input);
        assert isValidShift(input);
        String[] splitString = input.split(DELIMITER);
        DayOfWeek day = DayOfWeek.valueOf(splitString[1]);
        Slot slot = Slot.translateStringToSlot(splitString[2]);

    }

    public static Shift translateStringToEmpty(String string) {

    }

    private static List<Shift> getHistory(String input) {

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
