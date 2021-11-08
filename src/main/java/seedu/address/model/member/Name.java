package seedu.address.model.member;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Member's name in the ezFoodie.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}.
 */
public class Name {

    /**
     * Stands for message constraints of name.
     */
    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters and spaces, and it should not be blank";

    /**
     * Stands for the first character of the name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    /**
     * Stands for full name value.
     */
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
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Overrides the toString method.
     *
     * @return String of a valid name.
     */
    @Override
    public String toString() {
        return fullName;
    }

    /**
     * Overrides equals method.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Name // instanceof handles nulls
                && fullName.equals(((Name) other).fullName)); // state check
    }

    /**
     * Overrides hashCode method.
     */
    @Override
    public int hashCode() {
        return fullName.hashCode();
    }

}
