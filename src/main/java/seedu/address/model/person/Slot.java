package seedu.address.model.person;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents possible working slot for a staff.
 */
public enum Slot {
    MORNING("morning", 0), AFTERNOON("afternoon", 1);

    public static final String MESSAGE_CONSTRAINTS =
            "List of valid slots: morning, afternoon.";

    private String period;
    private int order;
    private LocalTime startTime;
    private LocalTime endTime;
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    private Slot(String period, int order) {
        this.period = period;
        this.order = order;
        setTimings(order);
    }

    public void setTimings(int order) {
        // TODO make this more flexible to change the timings if necessary
        // Currently, assumed that morning shift is 10 - 4.00pm, night shift is 4 - 10
        // Can consider having a settings page or smt pop out so they can set this?
        if (order == 0) {
            this.startTime = LocalTime.parse("10:00", timeFormatter);
            this.endTime = LocalTime.parse("16:00", timeFormatter);
        } else if (order == 1) {
            this.startTime = LocalTime.parse("16:00", timeFormatter);
            this.endTime = LocalTime.parse("22:00", timeFormatter);
        } else {
            System.out.println("Check the slot class"); //TODO make sure the order is checked before a slot is created
        }
    }

    /**
     * Gets the value of a slot.
     *
     * @return The value of a slot.
     */
    public String getValue() {
        return period;
    }

    /**
     * Gets the order of a slot.
     *
     * @return The order of a slot.
     */
    public int getOrder() {
        return order;
    }

    /**
     * Gets the start time of a slot.
     *
     * @return The start time of a slot.
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * Gets the end time of a slot.
     *
     * @return The end time of a slot.
     */
    public LocalTime getEndTime() {
        return endTime;
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
     * Translate a string into a Slot enum if the string matches any Slot values. Trims string.
     *
     * @param string String to be translated.
     * @return The translated Slot if the string is valid, null object otherwise.
     */
    public static Slot translateStringToSlot(String string) {
        String trimmedString = string.trim();
        Slot resultSlot = null;
        for (Slot s : Slot.values()) {
            if (s.getValue().equalsIgnoreCase(trimmedString)) {
                resultSlot = s;
                break;
            }
        }
        return resultSlot;
    }

    public static Slot getSlotByOrder(String string) {
        String trimmedString = string.trim();
        Slot resultSlot = null;
        for (Slot s : Slot.values()) {
            if (String.valueOf(s.getOrder()).equals(trimmedString)) {
                resultSlot = s;
                break;
            }
        }
        return resultSlot;
    }

    /**
     * Checks if the string provided matches with any Slot enum strings.
     *
     * @param test String to be checked.
     * @return boolean true if valid, false otherwise
     */
    public static boolean isValidSlot(String test) {
        String trimmedTest = test.trim();
        for (Slot s : Slot.values()) {
            String sString = String.valueOf(s.getValue());
            if (sString.equals(trimmedTest)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return this.startTime.toString() + "-" + this.endTime.toString();
    }
}
