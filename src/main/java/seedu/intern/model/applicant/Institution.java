package seedu.intern.model.applicant;

import static java.util.Objects.requireNonNull;
import static seedu.intern.commons.util.AppUtil.checkArgument;

/**
 * Represents an applicant's institution in Intern Watcher.
 * Guarantees: immutable; is valid as declared in {@link #isValidInstitution(String)}
 */
public class Institution {

    public static final String MESSAGE_CONSTRAINTS =
            "Institution Names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the intern must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String value;

    /**
     * Constructs a {@code Institute}.
     *
     * @param institute A valid institution name.
     */
    public Institution(String institute) {
        requireNonNull(institute);
        checkArgument(isValidInstitution(institute), MESSAGE_CONSTRAINTS);
        value = institute;
    }

    /**
     * Returns true if a given string is a valid institution name.
     */
    public static boolean isValidInstitution(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Institution // instanceof handles nulls
                && value.equals(((Institution) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
