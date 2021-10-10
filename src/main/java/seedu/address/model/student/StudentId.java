package seedu.address.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's ID in the ProgrammerError.
 * Guarantees: immutable; is valid as declared in {@link #isValidStudentId(String)}
 */
public class StudentId {

    public static final String MESSAGE_CONSTRAINTS =
            "Student ID should only contain 9 alphanumeric characters, and it should not be blank";

    /*
     * A student ID must start and end with an alphabet, and has exactly 7 numbers between the two letters
     */
    public static final String VALIDATION_REGEX = "[A-Z][0-9]{7}[A-Z]";

    public final String studentId;

    /**
     * Constructs a {@code StudentId}.
     *
     * @param studentId A valid studentId.
     */
    public StudentId(String studentId) {
        requireNonNull(studentId);
        checkArgument(isValidStudentId(studentId), MESSAGE_CONSTRAINTS);
        this.studentId = studentId;
    }

    /**
     * Returns true if a given string is a valid StudentId.
     */
    public static boolean isValidStudentId(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return studentId;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentId // instanceof handles nulls
                && studentId.equals(((StudentId) other).studentId)); // state check
    }

    @Override
    public int hashCode() {
        return studentId.hashCode();
    }

}
