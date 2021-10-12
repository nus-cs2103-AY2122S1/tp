package seedu.tracker.model.calendar;


import static java.util.Objects.requireNonNull;
import static seedu.tracker.commons.util.AppUtil.checkArgument;

public class Semester {

    public static final String MESSAGE_CONSTRAINTS =
            "Semester should only contain numbers from 1 to 4, and it should not be blank";

    public final int value;

    /**
     * Constructs a semester.
     * @param semester A valid semester.
     */
    public Semester(int semester) {
        requireNonNull(semester);
        checkArgument(isValidSemester(semester), MESSAGE_CONSTRAINTS);
        this.value = semester;
    }

    /**
     * Returns true if a given int is a valid Semester.
     */
    public static boolean isValidSemester(int test) {
        return test >= 1 && test <= 4;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Semester) // instanceof handles nulls
                && value == (((Semester) other).value); // state check
    }

}
