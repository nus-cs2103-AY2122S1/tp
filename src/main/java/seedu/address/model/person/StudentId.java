package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's Student ID in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidStudentId(String)}
 */
public class StudentId {

    public static final String MESSAGE_CONSTRAINTS = "Student ID must be valid ie starting with a or A followed by"
            + " 7 digits and a single alphabet, and it should not be blank\nExample: a0123456m, A1234567N";

    /*
     * Regex for the Student ID.
     */
    public static final String VALIDATION_REGEX = "([aA]([0-9]{7})([A-Za-z]{1}))";

    public final String value;

    /**
     * Constructs an {@code StudentId}.
     *
     * @param studentId A valid Student ID.
     */
    public StudentId(String studentId) {
        requireNonNull(studentId);
        checkArgument(isValidStudentId(studentId), MESSAGE_CONSTRAINTS);
        value = studentId.toUpperCase();
    }

    /**
     * Returns true if a given string is a valid Student ID.
     */
    public static boolean isValidStudentId(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Compares this value with another StudentId's value using Java's compareTo function
     */
    public int compareTo(StudentId s) {
        return value.compareTo(s.value);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof StudentId)) {
            return false;
        }

        StudentId otherStudentId = (StudentId) other;

        return value.equals(otherStudentId.value); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
