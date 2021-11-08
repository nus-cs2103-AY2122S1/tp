package seedu.address.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's Student Number in the tApp.
 * Guarantees: immutable; is valid as declared in {@link #isValidNumber(String)}
 */
public class StudentNumber {

    public static final String MESSAGE_CONSTRAINTS =
            "Student number should start and end with a letter and contain 7 numbers";

    /*
     * The first and last character of the number must be an alphabet,
     * and it must contain 7 numbers in between.
     */
    public static final String VALIDATION_REGEX = "[a-zA-Z]\\d{7}[a-zA-Z]";

    public final String studentNumber;

    /**
     * Constructs a {@code Name}.
     *
     * @param studentNumber A valid name.
     */
    public StudentNumber(String studentNumber) {
        requireNonNull(studentNumber);
        checkArgument(isValidNumber(studentNumber), MESSAGE_CONSTRAINTS);
        this.studentNumber = studentNumber.toUpperCase();
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidNumber(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return studentNumber;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentNumber // instanceof handles nulls
                && studentNumber.equals(((StudentNumber) other).studentNumber)); // state check
    }

    @Override
    public int hashCode() {
        return studentNumber.hashCode();
    }
}
