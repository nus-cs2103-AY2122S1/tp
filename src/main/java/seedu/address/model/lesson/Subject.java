package seedu.address.model.lesson;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represent the subject or topic of a lesson
 * Guarantees: immutable, is properly formatted as declared in {@link #isValidSubject(String)}
 */
public class Subject {

    public static final String MESSAGE_CONSTRAINTS =
            "Names should have at least one alphanumeric character, and it should not be blank";

    /*
     * The subject name should have at least one alphanumeric character
     */
    public static final String VALIDATION_REGEX = "^.*[\\p{Alnum}]+.*$";

    private final String subjectName;

    /**
     * Constructs a {@code Subject}.
     *
     * @param subjectName A valid string representation of what the subject is
     */
    public Subject(String subjectName) {
        requireNonNull(subjectName);
        checkArgument(isValidSubject(subjectName), MESSAGE_CONSTRAINTS);
        this.subjectName = subjectName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidSubject(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return subjectName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Subject // instanceof handles nulls
                && subjectName.equals(((Subject) other).subjectName)); // state check
    }

    @Override
    public int hashCode() {
        return subjectName.hashCode();
    }
}
