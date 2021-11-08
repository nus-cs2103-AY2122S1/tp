package seedu.edrecord.model.group;

import static java.util.Objects.requireNonNull;

/**
 * Represents a student's class group in EdRecord.
 * Guarantees: immutable; is always valid
 */
public class Group {

    public static final String MESSAGE_CONSTRAINTS = "Class code must be alphanumeric. Only 1-8 characters allowed.";
    public static final String MESSAGE_DOES_NOT_EXIST = "Class with that code has yet to be created.";

    /*
     * The class code must not have any whitespace characters.
     */
    public static final String VALIDATION_REGEX = "^\\p{Alnum}{1,8}$";

    public final String code;

    /**
     * Constructs a class {@code Group}.
     *
     * @param code A valid class group code.
     */
    public Group(String code) {
        requireNonNull(code);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    /**
     * Returns true if a given string is a valid group code.
     */
    public static boolean isValidGroup(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if group given has the same group code.
     */
    public boolean isSameGroup(Group toCheck) {
        return this.equals(toCheck);
    }

    @Override
    public String toString() {
        return code;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Group // instanceof handles nulls
                && code.equals(((Group) other).code)); // state check
    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }
}
