package seedu.intern.model.applicant;

import static java.util.Objects.requireNonNull;
import static seedu.intern.commons.util.AppUtil.checkArgument;

/**
 * Represents an applicant's course in Intern Watcher.
 * Guarantees: immutable; is valid as declared in {@link #isValidCourse(String)}
 */
public class Course {

    public static final String MESSAGE_CONSTRAINTS =
            "Course Names should only contain alphabet characters and spaces, and it should not be blank";

    /*
     * The first character of the course must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[a-zA-Z][a-zA-Z ]*";

    public final String value;

    /**
     * Constructs a {@code Course}.
     *
     * @param course A valid Course name.
     */
    public Course(String course) {
        requireNonNull(course);
        checkArgument(isValidCourse(course), MESSAGE_CONSTRAINTS);
        value = course;
    }

    /**
     * Returns true if a given string is a valid course name.
     */
    public static boolean isValidCourse(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Course // instanceof handles nulls
                && value.equals(((Course) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
