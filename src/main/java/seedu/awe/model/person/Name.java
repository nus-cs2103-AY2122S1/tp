package seedu.awe.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.awe.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's name in the awe book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Name {

    public static final String MESSAGE_CONSTRAINTS =
            "Names should be 50 characters or lesser, only contain alphanumeric "
                    + "characters and spaces, and should not be blank";

    /*
     * The first character of the awe must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public static final int MAX_LENGTH = 50;

    public final String fullName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public Name(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        checkArgument(name.length() <= MAX_LENGTH, MESSAGE_CONSTRAINTS);
        fullName = name;
    }

    /**
     * Gets the name String of name object.
     * @return String name
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX) && test.length() <= MAX_LENGTH;
    }


    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Name)) { // instanceof handles nulls
            return false;
        }

        return fullName.equals(((Name) other).fullName); // state check
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }

}
