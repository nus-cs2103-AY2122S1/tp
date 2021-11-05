package seedu.address.model.lessoncode;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a LessonCode in contHACKS.
 * Guarantees: immutable; name is valid as declared in {@link #isValidLessonCode(String)}
 */
public class LessonCode {

    public static final String MESSAGE_CONSTRAINTS = "Lesson codes should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String lessonCode;

    /**
     * Constructs a {@code LessonCode}.
     *
     * @param lessonCode A valid lesson code.
     */
    public LessonCode(String lessonCode) {
        requireNonNull(lessonCode);
        checkArgument(isValidLessonCode(lessonCode), MESSAGE_CONSTRAINTS);
        this.lessonCode = lessonCode;
    }

    /**
     * Returns true if a given string is a valid lesson code.
     */
    public static boolean isValidLessonCode(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LessonCode // instanceof handles nulls
                && lessonCode.equals(((LessonCode) other).lessonCode)); // state check
    }

    @Override
    public int hashCode() {
        return lessonCode.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return lessonCode;
    }

}
