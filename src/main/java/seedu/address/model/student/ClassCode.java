package seedu.address.model.student;

import static java.lang.Integer.parseInt;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;


/**
 * Represents a Student's classcode in the ClassMATE.
 * Guarantees: immutable.
 */

public class ClassCode implements Comparable<ClassCode> {

    public static final String MESSAGE_CONSTRAINTS = "ClassCode must start with G/g, "
            + "followed by a 2-digit number and it should not be blank";
    public static final String MESSAGE_CONSTRAINTS_DEFAULT_CLASSCODE = "G00 is not a valid class code";

    public static final String VALIDATION_REGEX = "[G|g]\\d{2}";
    public static final String DEFAULT_CLASSCODE_REGEX = "[G|g]00";

    public final String value;

    /**
     * Constructs an {@code ClassCode}.
     *
     * @param classCode A valid classcode.
     */
    public ClassCode (String classCode) {
        requireNonNull(classCode);
        checkArgument(isValidClassCode(classCode), MESSAGE_CONSTRAINTS);
        value = classCode;
    }

    /**
     * Returns true if a given string is a valid classCode.
     */
    public static boolean isValidClassCode(String test) {
        boolean value = test.matches(VALIDATION_REGEX);
        return value;
    }

    /**
     * Returns true if a given string is a default classCode.
     */
    public static boolean isDefaultClassCode(String test) {
        boolean value = test.matches(DEFAULT_CLASSCODE_REGEX);
        return value;
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

    @Override
    public int compareTo(ClassCode classCode) {
        return Integer.compare(parseInt(this.value.substring(1)), parseInt(classCode.value.substring(1)));
    }
}
