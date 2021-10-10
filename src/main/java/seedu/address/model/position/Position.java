package seedu.address.model.position;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a Position in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Position {

    // Identity fields
    private final Title title;

    private final Description description;

    // Data fields
    private int rejectionRate;

    // Total number of applicants for this position.
    private int noOfApplicants;

    // Total number of rejected applicants for this position.
    private int noOfRejectedApplicants;

    /**
     * Every field must be present and not null.
     */
    public Position(Title title, Description description) {
        requireAllNonNull(title);
        this.title = title;
        this.description = description;
    }

    public Title getTitle() {
        return title;
    }

    public Description getDescription() {
        return description;
    }

    /**
     * Returns true if both positions have the same title.
     * This defines a weaker notion of equality between two positions.
     */
    public boolean isSamePosition(Position otherPosition) {
        if (otherPosition == this) {
            return true;
        }

        return otherPosition != null
                && otherPosition.getTitle().equals(getTitle());
    }

    /**
     * Returns true if both positions have the same identity and data fields.
     * This defines a stronger notion of equality between two positions.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Position)) {
            return false;
        }

        Position otherPosition = (Position) other;
        return otherPosition.getTitle().equals(getTitle())
                && otherPosition.getDescription().equals(getDescription());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing
        return Objects.hash(title, description);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTitle())
                .append("; Description: ")
                .append(getDescription());

        return builder.toString();
    }

    /* Data calculations and others for Rejection rates */
    public int getNoOfApplicants() {
        return this.noOfApplicants;
    }

    public int getNoOfRejectedApplicants() {
        return this.noOfRejectedApplicants;
    }

    public void updateNoOfApplicants(int newTotal) {
        this.noOfApplicants = newTotal;
    }

    public void updateNoOfRejectedApplicants(int newTotal) {
        this.noOfRejectedApplicants = newTotal;
    }

    /**
     * Returns the rejection rate of a current position.
     *
     * @return The rejection rate of the position.
     */
    public int calculateRejectionRate() {
        int newRate;
        try {
            newRate = Math.round(this.noOfRejectedApplicants / this.noOfApplicants);
        } catch (ArithmeticException e) {
            newRate = 0;
        }
        return newRate;
    }

    /**
     * Updates the rejection rate of current position.
     *
     * @param total Total number of applicants in current position.
     * @param count Total number of accepted applicants in current position.
     */
    public void updateRejectionRate(int total, int count) {
        updateNoOfRejectedApplicants(count);
        updateNoOfApplicants(total);
        this.rejectionRate = calculateRejectionRate();
    }

    /**
     * Returns the rejection rate of the current position.
     *
     * @return Rejection rate of current position.
     */
    public int getRejectionRate() {
        return this.rejectionRate;
    }
}
