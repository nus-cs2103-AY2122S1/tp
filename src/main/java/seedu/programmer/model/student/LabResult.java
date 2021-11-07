package seedu.programmer.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.programmer.commons.util.AppUtil.checkArgument;

/**
 * Represents a student's name in the ProgrammerError.
 */
public class LabResult {
    private static final Integer UNMARKED_ACTUAL_SCORE_PLACEHOLDER = -1;
    private final Integer labResult;
    public static final String MESSAGE_CONSTRAINT =
            " Lab result should be a non-negative number or a unmarked placeholder(-1)";

    /**
     * Constructs a {@code Name}.
     *
     * @param labResult A valid name.
     */
    public LabResult(Integer labResult) {
        requireNonNull(labResult);
        checkArgument(isValidLabResult(labResult), MESSAGE_CONSTRAINT);
        if (labResult == -1) {
            this.labResult = UNMARKED_ACTUAL_SCORE_PLACEHOLDER;
        } else {
            this.labResult = labResult;
        }
    }

    /**
     * Returns true if a given string is a valid labNum.
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
