package seedu.address.model.person;

/**
 * Represents possible working slot for a staff.
 */
public enum Slot {
    MORNING("morning", 0), AFTERNOON("afternoon", 1);

    private String period;
    private int order;

    private Slot(String period, int order) {
        this.period = period;
        this.order = order;
    }

    /**
     * Gets the value of a slot.
     * @return The value of a slot.
     */
    public String getValue() {
        return period;
    }

    /**
     * Gets the order of a slot.
     * @return The order of a slot.
     */
    public int getOrder() {
        return order;
    }
}
