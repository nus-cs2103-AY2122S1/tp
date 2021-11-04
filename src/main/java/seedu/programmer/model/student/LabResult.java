package seedu.programmer.model.student;

import static java.util.Objects.requireNonNull;

/**
 * Represents a student's name in the ProgrammerError.
 */
public class LabResult {

    private final Integer labResult;

    /**
     * Constructs a {@code Name}.
     *
     * @param labResult A valid name.
     */
    public LabResult(Integer labResult) {
        requireNonNull(labResult);
        //checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        this.labResult = labResult;
    }

    public int getLabResult() {
        return this.labResult;
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
