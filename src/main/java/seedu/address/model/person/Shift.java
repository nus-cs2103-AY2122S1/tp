package seedu.address.model.person;

import java.time.DayOfWeek;

/**
 * Represents a piece of work for a staff.
 */
public class Shift {
    private String shiftName;
    private Slot slot;
    private DayOfWeek dayOfWeek;

    /**
     * Constructor of Shift given its weekday, time, and name.
     * @param dayOfWeek Weekday of the shift.
     * @param slot The slot when the task located.
     * @param shiftName Name of the task.
     */
    public Shift(DayOfWeek dayOfWeek, Slot slot, String shiftName) {
        this.dayOfWeek = dayOfWeek;
        this.slot = slot;
        this.shiftName = shiftName;
    }

    /**
     * Constructor of Task given its weekday, time, and name.
     * @param dayOfWeek Weekday of the shift.
     * @param slot The slot when the shift located.
     */
    public Shift(DayOfWeek dayOfWeek, Slot slot) {
        this.dayOfWeek = dayOfWeek;
        this.slot = slot;
    }


    /**
     * Sets name of the shift.
     * @param shiftName Name of the shift.
     */
    public void setShiftName(String shiftName) {
        this.shiftName = shiftName;
    }

    /**
     * Returns whether the shift is happening in the morning.
     * @return whether the shift is happening in the morning.
     */
    public boolean isInMorning() {
        return this.slot.getValue().equals("morning");
    }

    /**
     * Returns whether the shift is happening in the afternoon.
     * @return whether the shift is happening in the afternoon.
     */
    public boolean isInAfternoon() {
        return this.slot.getValue().equals("afternoon");
    }


    @Override
    public String toString() {
        return shiftName + ": in " + dayOfWeek.toString() + " " + slot.getValue();
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
        return toString().equals(otherShift.toString());
    }
}
