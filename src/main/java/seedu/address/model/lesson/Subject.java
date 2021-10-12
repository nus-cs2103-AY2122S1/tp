package seedu.address.model.lesson;

import static java.util.Objects.hash;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Subject for a Lesson in tuitiONE book.
 */
public class Subject {

    public static final String SUBJECT_VALIDATION_REGEX = "\\p{Alnum}+";
    public static final int MAXIMUM_SUBJECT_LENGTH = 20;
    public static final String SUBJECT_MESSAGE_CONSTRAINTS = String.format("Subject names should be alphanumeric and"
            + "within %1$d characters", MAXIMUM_SUBJECT_LENGTH);

    public final String value;

    /**
     * Constructs a {@code Subject}.
     *
     * @param subject A valid subject name.
     */
    public Subject(String subject) {
        requireNonNull(subject);
        checkArgument(isValidSubject(subject), SUBJECT_MESSAGE_CONSTRAINTS);

        this.value = subject;
    }

    /**
     * Returns true if a given string is a valid subject name for a lesson.
     */
    public static boolean isValidSubject(String testSubject) {
        if (testSubject == null) {
            return false;
        }
        return testSubject.matches(SUBJECT_VALIDATION_REGEX)
                && (testSubject.length() <= MAXIMUM_SUBJECT_LENGTH)
                && (!testSubject.isEmpty());
    }

    @Override
    public int hashCode() {
        return hash(value);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Subject)) {
            return false;
        }
        Subject otherSubject = (Subject) other;
        return value.equals(otherSubject.value);
    }

    @Override
    public String toString() {
        return value;
    }
}
