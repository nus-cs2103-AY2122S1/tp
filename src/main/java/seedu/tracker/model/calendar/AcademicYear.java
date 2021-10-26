package seedu.tracker.model.calendar;

import static java.util.Objects.requireNonNull;
import static seedu.tracker.commons.util.AppUtil.checkArgument;

import java.util.Objects;

public class AcademicYear implements Comparable<AcademicYear> {
    public static final String MESSAGE_CONSTRAINTS =
            "Academic Year should only contain numbers from 1 to 6, and it should not be blank";

    public final int value;

    /**
     * Constructs a dummy object only used by JsonUserInfoStorage.
     * This default constructor shouldn't be used anywhere else.
     */
    public AcademicYear() {
        this.value = 0;
    }

    /**
     * Constructs an academic year.
     * @param value A valid academic year.
     */
    public AcademicYear(int value) {
        requireNonNull(value);
        checkArgument(isValidAcademicYear(value), MESSAGE_CONSTRAINTS);
        this.value = value;
    }

    /**
     * Returns true if a given int is a valid Academic year.
     */
    public static boolean isValidAcademicYear(int test) {
        /* Academic year should be between year 1 and year 6*/
        return test >= 1 && test <= 6;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AcademicYear) // instanceof handles nulls
                && value == (((AcademicYear) other).value); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public int compareTo(AcademicYear academicYear) {
        return this.value - academicYear.value;
    }
}
