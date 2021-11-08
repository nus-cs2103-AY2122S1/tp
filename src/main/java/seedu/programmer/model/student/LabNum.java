package seedu.programmer.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.programmer.commons.util.AppUtil.checkArgument;

/**
 * Represents a Lab Number in the ProgrammerError.
 */
public class LabNum {
    private final Integer labNum;

    /**
     * Class constructor for {@code LabNum}.
     *
     * @param labNum A valid lab number.
     */
    public LabNum(Integer labNum) {
        requireNonNull(labNum);
        checkArgument(isValidLabNum(labNum), Lab.MESSAGE_LAB_NUMBER_CONSTRAINT);
        this.labNum = labNum;
    }

    public Integer getLabNum() {
        return this.labNum;
    }

    /**
     * Returns true if a given string is a valid labNum.
     *
     * @param test The Integer to be tested against.
     * @return true if the lab number is valid.
     */
    public static boolean isValidLabNum (Integer test) {
        return test.compareTo(0) > 0 && test.compareTo(14) < 0;
    }

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
