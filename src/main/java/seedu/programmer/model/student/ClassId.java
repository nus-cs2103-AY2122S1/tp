package seedu.programmer.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.programmer.commons.util.AppUtil.checkArgument;

/**
 * Represents a student's classId in the ProgrammerError.
 * Guarantees: immutable; is valid as declared in {@link #isValidClassId(String)}
 */
public class ClassId {
    public static final String MESSAGE_CONSTRAINTS =
            "Class ID should only contain 3 alphanumeric characters that begins with B followed by 2 digits from 0 to 9 "
                    + "(eg. B01 or B11), and it should not be blank";

    // A class ID must start with an alphabet followed by two numbers.
    private static final String VALIDATION_REGEX = "[B][0-9]{2}";

    private final String classId;

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
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidClassId(String classId) {

        return classId.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return classId;
    }

    /**
     * Gets the class number from the ClassId.
     *
     * @return the class number
     */
    public int getClassNum() {
        int startIdx = 1;
        char firstDigit = classId.charAt(startIdx);
        String classNum = firstDigit == '0' ? classId.substring(startIdx + 1) : classId.substring(startIdx);
        return Integer.parseInt(classNum);
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

