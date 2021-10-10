package seedu.address.model.person;

import java.time.DayOfWeek;

/**
 * Represents a piece of work for a staff.
 */
public class Shift {
    private Slot slot;
    private DayOfWeek dayOfWeek;

    /**
     * Constructor of Task given its weekday, time, and name.
     *
     * @param dayOfWeek Weekday of the shift.
     * @param slot The slot when the shift located.
     */
    public Shift(DayOfWeek dayOfWeek, Slot slot) {
        this.dayOfWeek = dayOfWeek;
        this.slot = slot;
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


    @Override
    public String toString() {
        return dayOfWeek.toString() + "-" + slot.getValue();
    }

    /**
     * Returns a String representation of the shift that is suitable for json.
     */
    public String toSaveString() {
        return dayOfWeek.toString() + "-" + slot.getValue() + " ";
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
        boolean resultBoolean = false;
        if (test.equals("")) {
            return false;
        }
        String[] stringSplit = test.split("-");
        if (stringSplit.length == 2) {
            String dayString = stringSplit[0];
            String slotString = stringSplit[1];
            resultBoolean = isValidDayOfWeek(dayString);
            resultBoolean &= Slot.isValidSlot(slotString);
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
        return slot.equals(otherShift.slot) && dayOfWeek.equals(otherShift.dayOfWeek);
    }
}
