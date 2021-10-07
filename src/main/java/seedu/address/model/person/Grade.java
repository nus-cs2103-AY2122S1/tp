package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's Grade in the ProgrammerError.
 * Guarantees: immutable; is valid as declared in {@link #isValidGrade(String)}
 */
public class Grade {

    public static final String MESSAGE_CONSTRAINTS =
            "Grade should only an alphabet or with a sign, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

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
