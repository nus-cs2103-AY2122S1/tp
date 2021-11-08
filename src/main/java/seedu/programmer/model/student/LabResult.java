package seedu.programmer.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.programmer.commons.util.AppUtil.checkArgument;

/**
 * Represents a lab result in the ProgrammerError.
 */
public class LabResult {
    private static final Integer UNMARKED_ACTUAL_SCORE_PLACEHOLDER = -1;
    private final Integer labResult;

    /**
     * Class constructor for {@code LabResult}.
     *
     * @param labResult The lab score obtained by a student.
     */
    public LabResult(Integer labResult) {
        requireNonNull(labResult);
        checkArgument(isValidLabResult(labResult), Lab.MESSAGE_LAB_SCORE_CONSTRAINT);
        if (labResult == -1) {
            this.labResult = UNMARKED_ACTUAL_SCORE_PLACEHOLDER;
        } else {
            this.labResult = labResult;
        }
    }

    /**
     * Returns true if a given string is a valid lab result.
     *
     * @param test The Integer to be tested against.
     * @return true if the lab result is valid.
     */
    public static boolean isValidLabResult (Integer test) {
        return test.compareTo(-1) >= 0;
    }

    public Integer getLabResult() {
        return this.labResult;
    }

    public static Integer getPlaceholder() {
        return UNMARKED_ACTUAL_SCORE_PLACEHOLDER;
    }

    @Override
    public String toString() {
        return labResult.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LabResult // instanceof handles nulls
                && labResult.equals(((LabResult) other).labResult)); // state check
    }

    @Override
    public int hashCode() {
        return labResult.hashCode();
    }
}
