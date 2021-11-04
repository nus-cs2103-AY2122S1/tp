package seedu.programmer.model.student;

import static java.util.Objects.requireNonNull;

/**
 * Represents a student's name in the ProgrammerError.
 */
public class LabTotal {

    private final Integer labTotal;

    /**
     * Constructs a {@code Name}.
     *
     * @param labTotal A valid name.
     */
    public LabTotal(Integer labTotal) {
        requireNonNull(labTotal);
        //checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        this.labTotal = labTotal;
    }

    public int getLabTotal() { return this.labTotal; }

    @Override
    public String toString() {
        return labTotal.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LabTotal // instanceof handles nulls
                && labTotal.equals(((LabTotal) other).labTotal)); // state check
    }

    @Override
    public int hashCode() {
        return labTotal.hashCode();
    }
}
