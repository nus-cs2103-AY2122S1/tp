package tutoraid.model.lesson;

import static java.util.Objects.requireNonNull;
import static tutoraid.commons.util.AppUtil.checkArgument;

/**
 * Represents a Lesson's students in TutorAid.
 * Guarantees: immutable; is valid as declared in {@link #isValidStudents(String)}
 */
public class Students {

    public static final String MESSAGE_CONSTRAINTS =
            "Students should only contain numbers";
    public static final String VALIDATION_REGEX = "\\d+";
    public final String value;

    /**
     * Constructs a {@code Students}.
     *
     * @param students Valid students' indexes.
     */
    public Students(String students) {
        requireNonNull(students);
        checkArgument(isValidStudents(students), MESSAGE_CONSTRAINTS);
        value = students;
    }

    /**
     * Returns true if a given string are valid students.
     */
    public static boolean isValidStudents(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Students // instanceof handles nulls
                && value.equals(((Students) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
