package seedu.address.model.person;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.math.BigInteger;

/**
 * Represents a Visit's occurrence.
 */
public class Occurrence {
    public static final String MESSAGE_CONSTRAINTS =
            "Occurrence should be positive integer and less than or equals to 999.";
    public static final Occurrence EMPTY_OCCURRENCE = new Occurrence(1);

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
    public Occurrence getNext() {
        return new Occurrence(value - 1);
    }

    /**
     * Returns true if a given int is a valid occurrence.
     */
    public static boolean isValidOccurrence(int occurrence) {
        return occurrence > 0 && occurrence <= 999;
    }

    /**
     * Returns true if a given string is a valid occurrence.
     */
    public static boolean isValidOccurrence(String occurrence) {
        try {
            new BigInteger(occurrence);
        } catch (NumberFormatException nfe) {
            return false;
        }
        BigInteger bigIntOccurrence = new BigInteger(occurrence);
        if (bigIntOccurrence.compareTo(BigInteger.valueOf(1000)) == 1) {
            return false;
        }
        return Occurrence.isValidOccurrence(bigIntOccurrence.intValue());
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
