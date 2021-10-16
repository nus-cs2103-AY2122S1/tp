package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's nationality in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidNationality(String)}
 */
public class Nationality {

    public static final String MESSAGE_CONSTRAINTS = "Nationality can take any values, and it should not be blank";

    /*
     * The first character of the nationality must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code Nationality}.
     *
     * @param nationality A valid nationality.
     */
    public Nationality(String nationality) {
        requireNonNull(nationality);
        checkArgument(isValidNationality(nationality), MESSAGE_CONSTRAINTS);
        value = nationality;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidNationality(String test) {
        if (test.isEmpty()) {
            return true;
        }
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Nationality // instanceof handles nulls
                && value.equals(((Nationality) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
