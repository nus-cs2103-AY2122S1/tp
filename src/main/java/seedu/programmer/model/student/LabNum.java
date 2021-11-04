package seedu.programmer.model.student;

import static java.util.Objects.requireNonNull;

/**
 * Represents a student's name in the ProgrammerError.
 */
public class LabNum {

    private final Integer labnum;

    /**
     * Constructs a {@code Name}.
     *
     * @param labnum A valid name.
     */
    public LabNum(Integer labnum) {
        requireNonNull(labnum);
        //checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        this.labnum = labnum;
    }

    public int getLabNum() { return this.labnum; }

    @Override
    public String toString() {
        return labnum.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LabNum // instanceof handles nulls
                && labnum.equals(((LabNum) other).labnum)); // state check
    }

    @Override
    public int hashCode() {
        return labnum.hashCode();
    }
}
