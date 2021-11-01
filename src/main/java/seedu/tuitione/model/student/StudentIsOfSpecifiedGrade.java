package seedu.tuitione.model.student;

import java.util.function.Predicate;

/**
 * Tests that a {@code Student}'s {@code Grade} matches the given grade.
 */
public class StudentIsOfSpecifiedGrade implements Predicate<Student> {

    private final Grade grade;

    public StudentIsOfSpecifiedGrade(Grade grade) {
        this.grade = grade;
    }

    @Override
    public boolean test(Student student) {
        return student.getGrade().equals(grade);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentIsOfSpecifiedGrade // instanceof handles nulls
                && grade.equals(((StudentIsOfSpecifiedGrade) other).grade)); // state check
    }
}
