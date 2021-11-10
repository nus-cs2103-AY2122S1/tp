package seedu.address.model;

/**
 * Represents an element within the report arraylist.
 */
public class ReportElement {
    private String currentTag;
    private int confirmedCount = 0;
    private int pendingCount = 0;
    private int declinedCount = 0;

    /**
     * Creates a report element with tag and status count.
     */
    public ReportElement(String currentTag, int confirmedCount, int pendingCount, int declinedCount) {
        this.currentTag = currentTag;
        this.confirmedCount = confirmedCount;
        this.pendingCount = pendingCount;
        this.declinedCount = declinedCount;
    }

    /**
     * Returns true if tags have the identical names.
     */
    public boolean hasSameTag(String tag) {
        return currentTag.equals(tag);
    }

    /**
     * Increases confirmed count based on status.
     */
    public void incrementConfirmed() {
        confirmedCount++;
    }

    /**
     * Increases pending count based on status.
     */
    public void incrementPending() {
        pendingCount++;
    }

    /**
     * Increases declined count based on status.
     */
    public void incrementDeclined() {
        declinedCount++;
    }

    @Override
    public String toString() {
        return currentTag + ": "
                + confirmedCount + " confirmed, "
                + pendingCount + " pending, "
                + declinedCount + " declined";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReportElement // instanceof handles nulls
                && this.equals(((ReportElement) other))); // state check
    }

    @Override
    public int hashCode() {
        return this.hashCode();
    }

}
