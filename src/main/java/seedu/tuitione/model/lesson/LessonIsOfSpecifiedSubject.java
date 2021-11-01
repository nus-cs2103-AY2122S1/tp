package seedu.tuitione.model.lesson;

import java.util.function.Predicate;

/**
 * Tests that a {@code Lesson}'s {@code Subject} matches the given subject.
 */
public class LessonIsOfSpecifiedSubject implements Predicate<Lesson> {

    private final Subject subject;

    public LessonIsOfSpecifiedSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public boolean test(Lesson lesson) {
        return lesson.getSubject().equals(subject);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LessonIsOfSpecifiedSubject // instanceof handles nulls
                && subject.equals(((LessonIsOfSpecifiedSubject) other).subject)); // state check
    }
}
