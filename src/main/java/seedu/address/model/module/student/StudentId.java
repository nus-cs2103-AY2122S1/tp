package seedu.address.model.module.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's student id in TAB.
 * Guarantees: immutable; is valid as declared in {@link #isValidStudentId(String)}
 */
public class StudentId {

    public static final String MESSAGE_CONSTRAINTS = "Must be a valid NUS student Id (starts with A, followed by 7 "
            + "numbers and ends \n"
            + "with a capital letter)";

    /*
     *  Student Id must start and end with a capitalised letter, with 7 digits in between
     *
     */
    public static final String VALIDATION_REGEX = "A\\d{7}[A-Z]";

    public final String value;

    /**
     * Constructs an {@code StudentId}.
     *
     * @param studentId A valid studentId.
     */
    public StudentId(String studentId) {
        requireNonNull(studentId);
        checkArgument(isValidStudentId(studentId), MESSAGE_CONSTRAINTS);
        value = studentId;
    }

    /**
     * Returns true if a given string is a valid student ID.
     *
     * @param test The string that may potentially be a student ID.
     * @return A boolean stating whether the student ID is valid.
     */
    public static boolean isValidStudentId(String test) {
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
