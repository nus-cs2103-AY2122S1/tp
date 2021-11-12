package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Arrays;

/**
 * Represents a Person's name in contHACKS.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Name {

    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphabets and spaces, and it should not be blank";

    public static final String VALIDATION_REGEX = "[\\p{Alpha}][\\p{Alpha} ]*";

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

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if the other full name is similar to this full name.
     */
    public boolean isSimilarTo(Name other) {
        if (other == null) {
            return false;
        }

        return this.contains(other) && other.contains(this);
    }

    private boolean contains(Name other) {
        String[] fullNameArr = fullName.split(" ");
        return Arrays
                .stream(fullNameArr)
                .map(String::trim)
                .map(String::toLowerCase)
                .allMatch(s -> other.fullName.toLowerCase().contains(s));
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

    public int compareTo(Name n) {
        return this.fullName.toLowerCase().compareTo(n.fullName.toLowerCase());
    }
}
