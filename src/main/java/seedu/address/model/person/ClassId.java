package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's classId in the ProgrammerError.
 * Guarantees: immutable; is valid as declared in {@link #isValidClassId(String)}
 */
public class ClassId {
    public static final String MESSAGE_CONSTRAINTS =
            "Class ID should only contain 3 alphanumeric characters, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String classId;

    /**
     * Constructs a {@code classID}.
     *
     * @param classId A valid classID.
     */
    public ClassId(String classId) {
        requireNonNull(classId);
        checkArgument(isValidClassId(classId), MESSAGE_CONSTRAINTS);
        this.classId = classId;
    }

    /**
     * Returns true if a given string is a valid grade.
     */
    public static boolean isValidClassId(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return classId;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClassId // instanceof handles nulls
                && classId.equals(((ClassId) other).classId)); // state check
    }

    @Override
    public int hashCode() {
        return classId.hashCode();
    }

}

