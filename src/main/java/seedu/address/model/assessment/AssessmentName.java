package seedu.address.model.assessment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Assessment's name in a student's assessment list.
 * Guarantees: immutable; is valid as declared in {@link #isValidAssessmentName(String)}
 */
public class AssessmentName {

    public static final String MESSAGE_CONSTRAINTS =
            "Assessment names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String assessmentName;

    /**
     * Constructs a {@code AssessmentName}.
     *
     * @param assessmentName A valid assessmentName.
     */
    public AssessmentName(String assessmentName) {
        requireNonNull(assessmentName);
        checkArgument(isValidAssessmentName(assessmentName), MESSAGE_CONSTRAINTS);
        this.assessmentName = assessmentName;
    }

    /**
     * Returns true if a given string is a valid assessment name.
     */
    public static boolean isValidAssessmentName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return assessmentName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AssessmentName // instanceof handles nulls
                && assessmentName.equals(((AssessmentName) other).assessmentName)); // state check
    }

    @Override
    public int hashCode() {
        return assessmentName.hashCode();
    }
}
