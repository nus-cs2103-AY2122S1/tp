package seedu.tracker.model.calendar;

import static java.util.Objects.requireNonNull;
import static seedu.tracker.commons.util.AppUtil.checkArgument;

import java.util.Objects;

public class Semester implements Comparable<Semester> {

    public static final String MESSAGE_CONSTRAINTS =
            "Semester should only contain numbers from 1 to 4, and it should not be blank";

    public final int value;

    /**
     * Constructs a dummy object only used by JsonUserInfoStorage.
     * This default constructor shouldn't be used anywhere else.
     */
    public Semester() {
        this.value = 0;
    }

    /**
     * Constructs a semester.
     * @param value A valid semester.
     */
    public Semester(int value) {
        requireNonNull(value);
        checkArgument(isValidSemester(value), MESSAGE_CONSTRAINTS);
        this.value = value;
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

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public int compareTo(Semester semester) {
        return this.value - semester.value;
    }
}
