package nustracker.model.student;

import static java.util.Objects.requireNonNull;

import nustracker.commons.util.AppUtil;

/**
 * Represents a Student's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidYear(String)}
 */
public class Year {

    public static final String MESSAGE_CONSTRAINTS =
            "Year must be a single digit number, that is between 1 and 6!";

    /*
     * Year must be a number that is between 1 and 6, inclusive
     */
    public static final String VALIDATION_REGEX = "^[1-6]";

    public final String value;

    /**
     * Constructs a {@code Name}.
     *
     * @param year A valid year.
     */
    public Year(String year) {
        requireNonNull(year);
        AppUtil.checkArgument(isValidYear(year), MESSAGE_CONSTRAINTS);
        value = year;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidYear(String year) {
        return year.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Year // instanceof handles nulls
                && value.equals(((Year) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
