package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's school name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidSchName(String)}
 */
public class School {
    public static final String MESSAGE_CONSTRAINTS = "School names should only contain alphanumeric characters, "
            + "hyphens, parentheses, period, single quotation marks and spaces.";

    /*
     * The first character of the school name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     * School name can be any alphabetic combination with space and/or single quotation mark.
     */
    public static final String VALIDATION_REGEX = "[-a-zA-Z0-9()'.][-a-zA-Z0-9()'. ]*";

    public final String value;

    /**
     * Constructs a {@code School}.
     *
     * @param schoolName A valid school name.
     */
    public School(String schoolName) {
        requireNonNull(schoolName);
        checkArgument(isValidSchName(schoolName), MESSAGE_CONSTRAINTS);
        value = schoolName;
    }

    /**
     * Returns true if a given string is a valid school name.
     */
    public static boolean isValidSchName(String test) {
        return test.isEmpty() || test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if school name is empty.
     *
     * @return True if school name is empty.
     */
    public boolean isEmpty() {
        return value.isEmpty();
    }


    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof School // instanceof handles nulls
                && value.equals(((School) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
