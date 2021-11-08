package seedu.address.model.reservation;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Reservation's id in the ezFoodie.
 * Guarantees: immutable, is valid as declared in {@link #isValidId(String)}.
 */
public class Id {

    /**
     * Stands for the max reservation id number.
     */
    public static final int MAX = 999999;

    /**
     * Stands for message constraints of reservation member id.
     */
    public static final String MESSAGE_CONSTRAINTS =
            "Reservation IDs should only contain 6 digits and it should not be blank, and max ID is " + MAX;

    /**
     * Stands for trim leading zero regex.
     */
    public static final String TRIM_LEADING_ZERO_REGEX = "^0+(?!$)";

    /**
     * Stands for validation regex of reservation id.
     */
    public static final String VALIDATION_REGEX = "[\\p{Digit}]*";

    /**
     * Stands for reservation Id pattern.
     */
    public static final String PATTERN = "%06d";

    /**
     * Stands for reservation id max length.
     */
    public static final int LENGTH = 6; // Max ID is 999999

    /**
     * Stands for reservation id value.
     */
    public final String value;

    /**
     * Constructs a {@code Id}.
     *
     * @param id a valid id.
     */
    public Id(String id) {
        requireNonNull(id);
        checkArgument(isValidId(id), MESSAGE_CONSTRAINTS);
        value = String.format(PATTERN, Long.parseLong(id));
    }

    /**
     * Returns whether is valid id.
     *
     * @param test input string test.
     * @return boolean true if a given string is a valid id.
     */
    public static boolean isValidId(String test) {
        test = test.replaceFirst(TRIM_LEADING_ZERO_REGEX, "");
        try {
            return test.length() <= LENGTH && Long.parseLong(test) <= MAX && test.matches(VALIDATION_REGEX);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Returns long value of id.
     */
    public Long getLongValue() {
        return Long.parseLong(value);
    }

    /**
     * Overrides the toString method.
     *
     * @return String print reservation id value.
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
                || (other instanceof Id // instanceof handles nulls
                && value.equals(((Id) other).value)); // state check
    }

    /**
     * Overrides the hashCode method.
     */
    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
