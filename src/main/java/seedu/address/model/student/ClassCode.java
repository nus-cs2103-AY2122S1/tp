package seedu.address.model.student;

import static java.lang.Integer.parseInt;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;


/**
 * Represents a Student's classcode in the ClassMATE.
 * Guarantees: immutable.
 */

public class ClassCode implements Comparable<ClassCode> {

    public static final String MESSAGE_CONSTRAINTS = "ClassCode must start with G, "
            + "followed by a 2-digit non-zero number and it should not be blank";

    public static final String MESSAGE_EMPTY_CLASS = "The classcode is invalid. Choose a classcode between G01 and G99";

    public static final String VALIDATION_REGEX = "[G]\\d{2}";

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

    public static boolean isDefaultClassCode(String test) {
        return test.equals("G00");
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
