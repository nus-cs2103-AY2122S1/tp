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
     * A class ID must start with an alphabet followed by two numbers.
     */
    public static final String VALIDATION_REGEX = "[A-Z][0-9]{2}";

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
        return test.matches(VALIDATION_REGEX) && test.contains("B");
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

