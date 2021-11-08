package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's role in the address book.
 */
public class Faculty {

    public static final String MESSAGE_CONSTRAINTS = "The Faculty field can take a value of the "
            + "current faculties in NUS \n"
            + "Valid faculties: fass, business, computing, dentistry, sde, engineering, medicine, science, law";
    public static final String FACULTY_OF_ARTS_AND_SOCIAL_SCIENCES = "fass";
    public static final String FACULTY_OF_BUSINESS = "business";
    public static final String SCHOOL_OF_COMPUTING = "computing";
    public static final String FACULTY_OF_DENTISTRY = "dentistry";
    public static final String SCHOOL_OF_DESIGN_AND_ENVIRONMENT = "sde";
    public static final String FACULTY_OF_ENGINEERING = "engineering";
    public static final String SCHOOL_OF_MEDICINE = "medicine";
    public static final String FACULTY_OF_SCIENCE = "science";
    public static final String FACULTY_OF_LAW = "law";
    // More faculties to be updated in the future.

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
        // Change to VALIDATION_REGEX in the future versions.
        return test.equals(FACULTY_OF_ARTS_AND_SOCIAL_SCIENCES)
                || test.equals(FACULTY_OF_BUSINESS)
                || test.equals(SCHOOL_OF_COMPUTING)
                || test.equals(FACULTY_OF_DENTISTRY)
                || test.equals(SCHOOL_OF_DESIGN_AND_ENVIRONMENT)
                || test.equals(FACULTY_OF_ENGINEERING)
                || test.equals(SCHOOL_OF_MEDICINE)
                || test.equals(FACULTY_OF_SCIENCE)
                || test.equals(FACULTY_OF_LAW);
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
