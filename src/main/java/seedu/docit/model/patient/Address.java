package seedu.docit.model.patient;

import static java.util.Objects.requireNonNull;
import static seedu.docit.commons.util.AppUtil.checkArgument;

/**
 * Represents a Patient's docit in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Address {

    public static final String MESSAGE_CONSTRAINTS = "Address should only contain alphanumeric characters, hash, "
        + "dash, commas, and spaces, "
        + "should not be numerical only, "
        + "and should not be blank";

    /*
     * The first character of the docit must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} #\\-,]*";

    public final String value;

    /**
     * Constructs an {@code Address}.
     *
     * @param address A valid docit.
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
        return test.matches(VALIDATION_REGEX);
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
