package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class StudentId {

    public static final String MESSAGE_CONSTRAINTS =
            "Student ID should start with A, have 7 numbers and end with a letter";
    public static final String VALIDATION_REGEX = "^[aA]\\d{7}[a-zA-z]$";
    public final String value;


    /**
     * Constructs an {@code Student ID}.
     *
     * @param studentId A valid student ID.
     */
    public StudentId(String studentId) {
        requireNonNull(studentId);
        checkArgument(isValidId(studentId), MESSAGE_CONSTRAINTS);
        value = studentId.toUpperCase();
    }

    /**
     * Returns true if a given string is a valid student ID.
     */
    public static boolean isValidId(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentId // instanceof handles nulls
                && value.equals(((StudentId) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
