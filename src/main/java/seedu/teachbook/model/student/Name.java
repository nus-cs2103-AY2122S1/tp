package seedu.teachbook.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.teachbook.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's name in the TeachBook.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}.
 */
public class Name {

    public static final String MESSAGE_CONSTRAINTS = "Names should only contain alphanumeric characters and spaces,"
            + " have less than 70 characters (including spaces), and it should not be blank";

    // The first character of a name must not be a whitespace, otherwise " " (a blank string) becomes a valid input
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String fullName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name a valid name.
     */
    public Name(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        fullName = name;
    }

    /**
     * Checks if the given string is a valid name.
     *
     * @param test name string to be tested.
     * @return {@code true} is the given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX) && test.length() < 70;
    }

    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Name // instanceof handles nulls
                && fullName.equals(((Name) other).fullName)); // state check
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }
}
