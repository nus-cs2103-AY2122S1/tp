package tutoraid.model.lesson;

import static java.util.Objects.requireNonNull;
import static tutoraid.commons.util.AppUtil.checkArgument;

/**
 * Represents a Lesson's name in TutorAid.
 * Guarantees: immutable; is valid as declared in {@link #isValidLessonName(String)}
 */
public class LessonName {

    public static final String MESSAGE_CONSTRAINTS =
            "Lesson names should only contain alphanumeric characters and spaces, and it should not be blank";

    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String lessonName;

    /**
     * Constructs a {@code LessonName}.
     *
     * @param lessonName A valid lesson name.
     */
    public LessonName(String lessonName) {
        requireNonNull(lessonName);
        checkArgument(isValidLessonName(lessonName), MESSAGE_CONSTRAINTS);
        this.lessonName = lessonName;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidLessonName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return lessonName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LessonName // instanceof handles nulls
                && lessonName.equals(((LessonName) other).lessonName)); // state check
    }

    @Override
    public int hashCode() {
        return lessonName.hashCode();
    }
}
