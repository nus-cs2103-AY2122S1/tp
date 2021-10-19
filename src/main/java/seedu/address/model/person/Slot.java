package seedu.address.model.person;

/**
 * Represents possible working slot for a staff.
 */
public enum Slot {
    MORNING("morning", 0), AFTERNOON("afternoon", 1);

    public static final String MESSAGE_CONSTRAINTS =
            "List of valid slots: morning, afternoon.";

    private String period;
    private int order;

    private Slot(String period, int order) {
        this.period = period;
        this.order = order;
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
}
