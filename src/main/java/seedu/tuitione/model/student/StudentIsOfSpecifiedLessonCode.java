package seedu.tuitione.model.student;

import java.util.function.Predicate;

import seedu.tuitione.model.lesson.LessonCode;

/**
 * Tests that a {@code Student}'s {@code LessonCode} matches the given lessonCode.
 */
public class StudentIsOfSpecifiedLessonCode implements Predicate<Student> {

    private final LessonCode lessonCode;

    public StudentIsOfSpecifiedLessonCode(LessonCode lessonCode) {
        this.lessonCode = lessonCode;
    }

    @Override
    public boolean test(Student student) {
        return student.getLessonCodes().contains(lessonCode);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentIsOfSpecifiedLessonCode // instanceof handles nulls
                && lessonCode.equals(((StudentIsOfSpecifiedLessonCode) other).lessonCode)); // state check
    }

}
