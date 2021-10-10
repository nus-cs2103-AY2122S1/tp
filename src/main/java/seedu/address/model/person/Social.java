package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's social handle in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidSocial(String)}
 */
public class Social {

    public static final String MESSAGE_CONSTRAINTS = "Social handle (Telegram handle) can take any values, and it should not be blank";

    /*
     * The first character of the social handle must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code Social}.
     *
     * @param handle A valid Telegram handle.
     */
    public Social(String handle) {
        requireNonNull(handle);
        checkArgument(isValidSocial(handle), MESSAGE_CONSTRAINTS);
        value = handle;
    }

    /**
     * Returns true if a given string is a valid Social handle.
     */
    public static boolean isValidSocial(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Social // instanceof handles nulls
                && value.equals(((Social) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
