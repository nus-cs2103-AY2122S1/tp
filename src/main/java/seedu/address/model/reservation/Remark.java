package seedu.address.model.reservation;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Reservation's remark in the Reservation.
 * Guarantees: immutable; is valid as declared in {@link #isValidRemark(String)}.
 */
public class Remark {

    public static final String MESSAGE_CONSTRAINTS = "Remarks can take any values, and it should not be blank";

    /**
     * Stands for the first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    /**
     * Stands for reservation remark value.
     */
    public final String value;

    /**
     * Constructs a {@code Remark}.
     *
     * @param remark a valid remark.
     */
    public Remark(String remark) {
        requireNonNull(remark);
        checkArgument(isValidRemark(remark), MESSAGE_CONSTRAINTS);
        value = remark;
    }

    /**
     * Returns whether is valid remark.
     *
     * @param test the string input test.
     * @return boolean true if a given string is a valid remark.
     */
    public static boolean isValidRemark(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Gets double class from a string.
     *
     * @return double value of remark.
     */
    public double getDoubleValue() {
        return Double.parseDouble(value);
    }

    /**
     * Overrides the toString method.
     *
     * @return String reservation remark value。
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
                || (other instanceof Remark // instanceof handles nulls
                && value.equals(((Remark) other).value)); // state check
    }

    /**
     * Overrides the hashCode method.
     */
    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
