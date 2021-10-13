package safeforhall.model.person;

import static java.util.Objects.requireNonNull;
import static safeforhall.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's faculty in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidFaculty(String)} (String)}
 */
public class Faculty {

    public static final String MESSAGE_CONSTRAINTS = "Faculty is a single word made up of alphabets "
            + "and it should not be blank";

    /*
     * The first character of the faculty must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[A-Za-z]{2,}$";

    public final String faculty;

    /**
     * Constructs an {@code Faculty}.
     *
     * @param faculty A valid faculty.
     */
    public Faculty(String faculty) {
        requireNonNull(faculty);
        checkArgument(isValidFaculty(faculty), MESSAGE_CONSTRAINTS);
        this.faculty = faculty.toUpperCase();
    }

    /**
     * Returns true if a given string is a valid faculty.
     */
    public static boolean isValidFaculty(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return faculty;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Faculty // instanceof handles nulls
                && faculty.equals(((Faculty) other).faculty)); // state check
    }

    @Override
    public int hashCode() {
        return faculty.hashCode();
    }

}
