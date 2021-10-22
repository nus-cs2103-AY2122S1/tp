package seedu.address.model.student;

import java.util.Locale;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Student's classcode in the ClassMATE.
 * Guarantees: immutable.
 */

public class ClassCode {

    public static final String MESSAGE_CONSTRAINTS = "ClassCode must start with G/g, "
            + "followed by a 2-digit number and it should not be blank";

    public static final String VALIDATION_REGEX = "[G]\\d{2}";
    public final String value;

    /**
     * Constructs an {@code ClassCode}.
     *
     * @param classCode A valid classcode.
     */
    public ClassCode (String classCode) {
        requireNonNull(classCode);
        value = classCode.toUpperCase(Locale.ROOT);
    }

    /**
     * Returns true if a given string is a valid classCode.
     */
    public static boolean isValidClassCode(String test) {
        return test.toUpperCase(Locale.ROOT).matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClassCode // instanceof handles nulls
                && value.equals(((ClassCode) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
