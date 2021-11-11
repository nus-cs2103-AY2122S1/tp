package seedu.intern.model.applicant;

import static java.util.Objects.requireNonNull;
import static seedu.intern.commons.util.AppUtil.checkArgument;

/**
 * Represents an applicant's grade in the Intern Watcher.
 * Guarantees: immutable; is valid as declared in {@link #isValidGrade(String)}
 */
public class Grade {

    public static final String MESSAGE_CONSTRAINTS =
            "Grade should be 2dp, from 0.00 to 5.00 inclusive";

    public static final String VALIDATION_REGEX = "[0-5]+(\\.[0-9][0-9])";

    public final String value;

    /**
     * Constructs a {@code Grade}.
     *
     * @param grade A valid grade.
     */
    public Grade(String grade) {
        requireNonNull(grade);
        checkArgument(isValidGrade(grade), MESSAGE_CONSTRAINTS);
        value = grade;
    }

    /**
     * Returns true if a given string is a valid grade name.
     */
    public static boolean isValidGrade(String test) {
        return test.matches(VALIDATION_REGEX) && isValidGradeRange(test);
    }

    public static boolean isValidGradeRange(String test) {
        return Float.parseFloat(test) <= 5.00 && Float.parseFloat(test) >= 0;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Grade // instanceof handles nulls
                && value.equals(((Grade) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
