package seedu.tuitione.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.tuitione.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's tuitione in the tuitione book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Address {

    public static final int MAX_LENGTH = 150;
    public static final String MESSAGE_CONSTRAINTS =
            String.format("Addresses can take any values (apart from '/'), it should not be blank and should not "
                    + "contain more than %1$d characters (whitespace included)", MAX_LENGTH);

    /*
     * The first character of the tuitione must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code Address}.
     *
     * @param address A valid tuitione.
     */
    public Address(String address) {
        requireNonNull(address);
        checkArgument(isValidAddress(address), MESSAGE_CONSTRAINTS);
        value = address;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidAddress(String test) {
        return test.matches(VALIDATION_REGEX)
                && !test.isEmpty()
                && test.length() <= MAX_LENGTH
                && !test.contains("/");
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
