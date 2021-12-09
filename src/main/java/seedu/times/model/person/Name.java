package seedu.times.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.times.commons.util.AppUtil.checkArgument;
import static seedu.times.commons.util.AppUtil.isWithinLength;

/**
 * Represents a Person's name in the Timestable.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Name implements Comparable<Name> {

    public static final int MAX_CHAR_COUNT = 120;
    public static final String MESSAGE_CONSTRAINTS =
            "Names contains characters that is not alphanumeric/space, or the name is not between 1 and 120 "
            + "characters long.";

    /*
     * The first character of the name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String fullName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public Name(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        fullName = name;
    }

    public String getFullName() {
        return fullName;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX) && isWithinLength(test, MAX_CHAR_COUNT);
    }


    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Name // instanceof handles nulls
                && fullName.compareToIgnoreCase(((Name) other).fullName) == 0); // compares names, ignores case
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }

    @Override
    public int compareTo(Name otherName) {
        return fullName.compareTo(otherName.fullName);
    }

}
