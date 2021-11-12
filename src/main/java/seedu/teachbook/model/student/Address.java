package seedu.teachbook.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.teachbook.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's address in the TeachBook.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}.
 */
public class Address {

    public static final String MESSAGE_CONSTRAINTS = "Addresses can take any values, and it should not be blank";

    // The first character of an address must not be a whitespace, otherwise " " (a blank string) becomes a valid input
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code Address}.
     *
     * @param address a valid address.
     */
    public Address(String address) {
        requireNonNull(address);
        checkArgument(isValidAddress(address), MESSAGE_CONSTRAINTS);
        value = address;
    }

    /**
     * Checks if the given string is a valid address.
     *
     * @param test address string to be tested.
     * @return {@code true} is the given string is a valid address.
     */
    public static boolean isValidAddress(String test) {
        return test.equals("") || test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Address // instanceof handles nulls
                && value.equals(((Address) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
