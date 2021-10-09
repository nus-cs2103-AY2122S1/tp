package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's role in the address book.
 */
public class Faculty {

    public static final String MESSAGE_CONSTRAINTS = "Faculty can take a values of current faculties in NUS";
    public static final String SCHOOL_OF_COMPUTING = "computing";
    public static final String FACULTY_OF_ARTS_AND_SOCIAL_SCIENCES = "fass";
    public static final String FACULTY_OF_ENGINEERING = "engineering";
    public static final String FACULTY_OF_SCIENCE = "science";
    // TODO: Add on more faculties in the future

    public final String value;

    /**
     * Constructs a {@code Faculty}.
     *
     * @param faculty A valid faculty.
     */
    public Faculty(String faculty) {
        requireNonNull(faculty);
        checkArgument(isValidFaculty(faculty), MESSAGE_CONSTRAINTS);
        value = faculty;
    }

    /**
     * Returns true if a given string is a valid role.
     */
    public static boolean isValidFaculty(String test) {
        // Change to VALIDATION_REGEX in the future.
        return test.equals(SCHOOL_OF_COMPUTING)
                || test.equals(FACULTY_OF_ARTS_AND_SOCIAL_SCIENCES)
                || test.equals(FACULTY_OF_ENGINEERING)
                || test.equals(FACULTY_OF_SCIENCE);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Faculty // instanceof handles nulls
                && value.equals(((Faculty) other).value)); // state check
    }
}
