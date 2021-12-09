package seedu.times.model.tuitionclass;

import static java.util.Objects.requireNonNull;
import static seedu.times.commons.util.AppUtil.checkArgument;
import static seedu.times.model.util.Commons.MAX_CHAR_COUNT;

/**
 * Represents a {@code TuitionClass}'s class location in the Timestable.
 * Guarantees: immutable; is valid as declared in {@link #isValidLocation(String)}
 */
public class Location {

    public static final String MESSAGE_CONSTRAINTS = "Class location should be between 1 and "
            + MAX_CHAR_COUNT + " characters long!";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].{0,84}";

    public final String value;

    /**
     * Constructs an {@code Location}.
     *
     * @param location A valid class address.
     */
    public Location(String location) {
        requireNonNull(location);
        checkArgument(isValidLocation(location), MESSAGE_CONSTRAINTS);
        value = location;
    }

    /**
     * Returns true if a given string is a valid class location.
     */
    public static boolean isValidLocation(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Location // instanceof handles nulls
                && value.equals(((Location) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
