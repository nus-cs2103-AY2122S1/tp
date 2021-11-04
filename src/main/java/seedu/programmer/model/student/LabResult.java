package seedu.programmer.model.student;

import static java.util.Objects.requireNonNull;

/**
 * Represents a student's name in the ProgrammerError.
 */
public class LabResult {
    private static final Integer UNMARKED_ACTUAL_SCORE_PLACEHOLDER = -1;
    private final Integer labResult;

    /**
     * Constructs a {@code Name}.
     *
     * @param labResult A valid name.
     */
    public LabResult(Integer labResult) {
        requireNonNull(labResult);
        if (labResult == -1) {
            this.labResult = UNMARKED_ACTUAL_SCORE_PLACEHOLDER;
        } else {
            this.labResult = labResult;
        }
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
