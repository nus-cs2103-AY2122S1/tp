package seedu.teachbook.model.gradeobject;

public class Grade {

    public static final String MESSAGE_CONSTRAINTS =
            "Grades should be unique, not blank, and it should not contain \">\"";

    public final String value;

    public Grade(String grade) {
        this.value = grade;
    }

    /**
     * Returns true if a given string is a valid grade.
     */
    public static boolean isValidGrade(String test) {
        return true;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Grade // instanceof handles nulls
                && value.equals(((Grade) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
