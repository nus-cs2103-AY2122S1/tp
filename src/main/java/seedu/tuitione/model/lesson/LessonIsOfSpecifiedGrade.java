package seedu.tuitione.model.lesson;

import java.util.function.Predicate;

import seedu.tuitione.model.student.Grade;

/**
 * Tests that a {@code Lesson}'s {@code Grade} matches the given grade.
 */
public class LessonIsOfSpecifiedGrade implements Predicate<Lesson> {

    private final Grade grade;

    public LessonIsOfSpecifiedGrade(Grade grade) {
        this.grade = grade;
    }

    @Override
    public boolean test(Lesson lesson) {
        return lesson.getGrade().equals(grade);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LessonIsOfSpecifiedGrade // instanceof handles nulls
                && grade.equals(((LessonIsOfSpecifiedGrade) other).grade)); // state check
    }
}
