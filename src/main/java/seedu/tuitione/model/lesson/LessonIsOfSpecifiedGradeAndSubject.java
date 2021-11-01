package seedu.tuitione.model.lesson;

import java.util.function.Predicate;

import seedu.tuitione.model.student.Grade;

public class LessonIsOfSpecifiedGradeAndSubject implements Predicate<Lesson> {

    private final Grade grade;
    private final Subject subject;

    /**
     * Every field must be present and not null.
     */
    public LessonIsOfSpecifiedGradeAndSubject(Grade grade, Subject subject) {
        this.grade = grade;
        this.subject = subject;
    }

    @Override
    public boolean test(Lesson lesson) {
        return lesson.getSubject().equals(subject) && lesson.getGrade().equals(grade);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LessonIsOfSpecifiedGradeAndSubject // instanceof handles nulls
                && subject.equals(((LessonIsOfSpecifiedGradeAndSubject) other).subject)
                && grade.equals(((LessonIsOfSpecifiedGradeAndSubject) other).grade)); // state check
    }
}
