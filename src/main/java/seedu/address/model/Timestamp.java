package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a timestamp in the ezFoodie.
 * Guarantees: immutable; is valid as declared in {@link #isValidTimestamp(String)}.
 */
public class Timestamp {

    /**
     * Stands for timestamp message constraints.
     */
    public static final String MESSAGE_CONSTRAINTS =
            "Timestamps should only contain digits and can be parsed to long, and it should not be blank";

    /**
     * Stands for validation regex of timestamp.
     */
    public static final String VALIDATION_REGEX = "[\\p{Digit}]*";

    /**
     * Stands for the timestamp value.
     */
    public final String value;

    /**
     * Constructs a {@code Timestamp} with input {@code timestamp}.
     */
    public Timestamp(String timestamp) {
        requireNonNull(timestamp);
        checkArgument(isValidTimestamp(timestamp), MESSAGE_CONSTRAINTS);
        value = timestamp;
    }

    /**
     * Returns whether is valid timestamp.
     *
     * @param test input string test.
     * @return boolean true if a given string is a valid timestamp.
     */
    public static boolean isValidTimestamp(String test) {
        try {
            Long.parseLong(test);
            return test.matches(VALIDATION_REGEX);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Returns long value of timestamp.
     */
    public Long getLongValue() {
        return Long.parseLong(value);
    }

    /**
     * Overrides the toString method.
     *
     * @return String of the timestamp value.
     */
    @Override
    public String toString() {
        return value;
    }

    /**
     * Overrides the equals method.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Timestamp // instanceof handles nulls
                && value.equals(((Timestamp) other).value)); // state check
    }

    /**
     * Overrides the hashCode method.
     */
    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
