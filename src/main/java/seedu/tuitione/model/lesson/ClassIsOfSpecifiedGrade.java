package seedu.tuitione.model.lesson;

import seedu.tuitione.model.student.Grade;

import java.util.function.Predicate;

/**
 * Tests that a {@code Lesson}'s {@code Grade} matches the given grade.
 */
public class ClassIsOfSpecifiedGrade implements Predicate<Lesson> {
    private final Grade grade;

    public ClassIsOfSpecifiedGrade(Grade grade) {
        this.grade = grade;
    }

    @Override
    public boolean test(Lesson lesson) {
        return lesson.getGrade().equals(grade);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClassIsOfSpecifiedGrade // instanceof handles nulls
                && grade.equals(((ClassIsOfSpecifiedGrade) other).grade)); // state check
    }
}
