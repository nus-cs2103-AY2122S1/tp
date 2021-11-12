package seedu.anilist.model.anime;

import static java.util.Objects.requireNonNull;
import static seedu.anilist.commons.util.AppUtil.checkArgument;

/**
 * Represents an Anime's name in the anime list.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Name {

    public static final String MESSAGE_CONSTRAINTS =
        "Names should only contain ASCII characters and spaces, and it should have a length "
        + "between 1 to 140 characters (not including trailing whitespaces)";
    public static final int MAX_LEN = 140;
    /*
     * The first character of the anime name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s][\\p{ASCII}]*";

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

    private static boolean hasValidLength(String test) {
        return test.length() <= MAX_LEN;
    }

    /**
     * Returns true if a given string has a valid format to be a Name.
     */
    public static boolean isValidName(String test) {
        return hasValidLength(test) && test.matches(VALIDATION_REGEX);
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
