package seedu.tuitione.model.lesson;

import java.util.function.Predicate;

/**
 * Tests that a {@code Lesson}'s {@code LessonCode} matches the given lessonCode
 */
public class LessonIsOfSpecifiedLessonCode implements Predicate<Lesson> {

    private final LessonCode lessonCode;

    public LessonIsOfSpecifiedLessonCode(LessonCode lessonCode) {
        this.lessonCode = lessonCode;
    }

    @Override
    public boolean test(Lesson lesson) {
        return lesson.getLessonCode().equals(lessonCode);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LessonIsOfSpecifiedLessonCode // instanceof handles nulls
                && lessonCode.equals(((LessonIsOfSpecifiedLessonCode) other).lessonCode)); // state check
    }

}
