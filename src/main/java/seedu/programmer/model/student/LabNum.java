package seedu.programmer.model.student;

import static java.util.Objects.requireNonNull;

/**
 * Represents a student's name in the ProgrammerError.
 */
public class LabNum {

    private final Integer labNum;

    /**
     * Constructs a {@code Name}.
     *
     * @param labNum A valid name.
     */
    public LabNum(Integer labNum) {
        requireNonNull(labNum);
        //checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        this.labNum = labNum;
    }

    public Integer getLabNum() { return this.labNum; }

    @Override
    public String toString() {
        return labNum.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LabNum // instanceof handles nulls
                && labNum.equals(((LabNum) other).labNum)); // state check
    }

    @Override
    public int hashCode() {
        return labNum.hashCode();
    }
}
