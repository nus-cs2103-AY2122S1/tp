package seedu.programmer.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.programmer.commons.util.AppUtil.checkArgument;

/**
 * Represents a student's Grade in the ProgrammerError.
 * Guarantees: immutable; is valid as declared in {@link #isValidGrade(String)}
 */
public class Grade {

    public static final String MESSAGE_CONSTRAINTS =
            "Grade should only an alphabet or with a sign, and it should not be blank";

    /*
     * A valid expression can be either a single alphabet (eg. A) or an alphabet followed by a sign (eg. A+)
     * Grade A and B can be followed by either a plus, minus or no sign.
     * Grade C and D can be followed by either a plus or no sign.
     * Grade F cannot be followed by any sign.
     * "*" indicates that sign is optional.
     */
    public static final String VALIDATION_REGEX = "([AB]((\\+)|-)*)|([CD](\\+)*)|(F)";

    public final String grade;

    /**
     * Constructs a {@code Grade}.
     *
     * @param grade A valid Grade.
     */
    public Grade(String grade) {
        requireNonNull(grade);
        checkArgument(isValidGrade(grade), MESSAGE_CONSTRAINTS);
        this.grade = grade;
    }

    /**
     * Returns true if a given string is a valid grade.
     */
    public static boolean isValidGrade(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return grade;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Grade // instanceof handles nulls
                && grade.equals(((Grade) other).grade)); // state check
    }

    @Override
    public int hashCode() {
        return grade.hashCode();
    }

}
