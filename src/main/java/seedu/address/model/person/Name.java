package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Locale;

/**
 * Represents a Person's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Name implements Comparable<Name> {

    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphabetical characters, and should not be blank.";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alpha}][\\p{Alpha}'. ]*";

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
     *
     * @param test String of name to be tested against the validation regex.
     * @return Boolean representation of validity of String of name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns the String representation of Name.
     *
     * @return String representation of Name.
     */
    @Override
    public String toString() {
        return fullName;
    }

    /**
     * Method to compare two Name objects.
     *
     * @param other is the object that is going to be compared
     *              to the Name object that called this method.
     * @return boolean representation of whether the Name
     * object is equal to the other object passed as parameter.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Name // instanceof handles nulls
                && fullName.toLowerCase(Locale.ROOT).equals((
                        (Name) other).fullName.toLowerCase(Locale.ROOT))); // state check
    }

    /**
     * Returns the {@code hashCode} of Name.
     *
     * @return hashCode of Name.
     */
    @Override
    public int hashCode() {
        return fullName.hashCode();
    }

    @Override
    public int compareTo(Name n) {
        return fullName.toLowerCase().compareTo(n.fullName.toLowerCase());
    }
}

