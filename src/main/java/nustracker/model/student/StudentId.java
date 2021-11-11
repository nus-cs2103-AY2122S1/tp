package nustracker.model.student;

import static java.util.Objects.requireNonNull;

import nustracker.commons.util.AppUtil;

/**
 * Represents a student ID in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidStudentId(String)}
 */
public class StudentId {

    public static final String MESSAGE_CONSTRAINTS =
            "Student ID should start with 'e', should be followed by 7 digits, and it should not be blank!";

    /*
     * The first character of the address must a lowercase 'e', in line with
     * current NUS guidelines for ids, followed by 7 numerical digits
     */
    public static final String VALIDATION_REGEX = "e[\\p{Nd}]{7}";

    public final String value;

    /**
     * Constructs a {@code StudentId}.
     *
     * @param studentId A valid student ID
     */
    public StudentId(String studentId) {
        requireNonNull(studentId);
        AppUtil.checkArgument(isValidStudentId(studentId), MESSAGE_CONSTRAINTS);
        value = studentId.trim();
    }

    /**
     * Returns true if a given string is a valid student Id.
     */
    public static boolean isValidStudentId(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Gets the student Id in String form.
     * @return the student Id in String form.
     */
    public String getStudentIdString() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentId // instanceof handles nulls
                && value.equals(((StudentId) other).value)); //Check internal attributes
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
