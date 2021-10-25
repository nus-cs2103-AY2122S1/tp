package seedu.tuitione.model.student;

import java.util.function.Predicate;

import seedu.tuitione.model.lesson.LessonCode;

/**
 * Tests that a {@code Student}'s {@code LessonCode} matches the given lessonCode.
 */
public class StudentsIsOfSpecifiedLessonCode implements Predicate<Student> {
    private final LessonCode lessonCode;

    public StudentsIsOfSpecifiedLessonCode(LessonCode lessonCode) {
        this.lessonCode = lessonCode;
    }

    @Override
    public boolean test(Student student) {
        return student.getLessonCodes().contains(lessonCode);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentsIsOfSpecifiedLessonCode // instanceof handles nulls
                && lessonCode.equals(((StudentsIsOfSpecifiedLessonCode) other).lessonCode)); // state check
    }

}
