package seedu.address.model.person;

/**
 * Represents possible working slot for a staff.
 */
public enum Period {
    MORNING("morning", 0), AFTERNOON("afternoon", 1);

    private String period;
    private int order;

    private Period(String period, int order) {
        this.period = period;
        this.order = order;
    }

    /**
     * Gets the value of a period.
     * @return The value of a period.
     */
    public String getValue() {
        return period;
    }

    /**
     * Gets the order of a period.
     * @return The order of a period
     */
    public int getOrder() {
        return order;
    }
}
