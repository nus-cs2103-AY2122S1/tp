package seedu.address.model.person;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Visit's occurrence.
 */
public class Occurrence {
    public static final String MESSAGE_CONSTRAINTS =
            "Occurrence should be positive number.";

    public final int value;

    /**
     * Constructs a {@code Occurrence}.
     *
     * @param occurrence A valid occurrence.
     */
    public Occurrence(int occurrence) {
        checkArgument(isValidOccurrence(occurrence), MESSAGE_CONSTRAINTS);
        value = occurrence;
    }

    /**
     * Get the next number of occurrence for the visit.
     * @return The next occurrence for the visit.
     */
    public Occurrence getNext() { return new Occurrence(value - 1); }

    /**
     * Returns true if a given int is a valid occurrence.
     */
    public static boolean isValidOccurrence(int occurrence) { return occurrence > 0; }

    /**
     * Returns true if a given string is a valid occurrence.
     */
    public static boolean isValidOccurrence(String occurrence) {
        try {
            Integer.parseInt(occurrence);
        } catch (NumberFormatException nfe) {
            return false;
        }
        int intOccurrence = Integer.parseInt(occurrence);
        return Occurrence.isValidOccurrence(intOccurrence);
    }

    /**
     * Checks if the occurrence is more than a certain value.
     * @param floor The floor value.
     * @return true if occurrence is more than the given value, otherwise false.
     */
    public boolean isMoreThan(int floor) {
        return value > floor;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Occurrence
                && value == (((Occurrence) other).value)); // state check
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
