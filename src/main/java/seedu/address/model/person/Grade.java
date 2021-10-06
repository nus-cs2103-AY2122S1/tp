package seedu.address.model.person;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents the grade of the student
 */
public class Grade {
    public static final String MESSAGE_CONSTRAINTS = "Prefix should either be S (to denote secondary) "
            + "or P (to denote Primary)."
            + "Level number should be from 1 to 6 for primary and 1 to 4 for secondary.";

    public static final String[] VALID_GRADES = {"P1", "P2", "P3", "P4", "P5", "P6", "S1", "S2", "S3", "S4"};
    public final String grade;

    /**
     * Constructs a student grade
     *
     * @param grade The grade of the student
     */
    public Grade(String grade) {
        requireAllNonNull(grade);
        checkArgument(isValidGrade(grade), MESSAGE_CONSTRAINTS);
        this.grade = grade;
    }

    /**
     * Returns true if given grade is valid
     * @param grade The grade of the student
     * @return Whether the grade is a valid input.
     */
    public static boolean isValidGrade(String grade) {
        for (String validGrade : VALID_GRADES) {
            if (grade.equals(validGrade)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Grade // instanceof handles nulls
                && grade.equals(((Grade) other).grade)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(grade);
    }

    @Override
    public String toString() {
        return "[" + grade + "]";
    }
}
