package seedu.tuitione.model.student;

import java.util.function.Predicate;

/**
 * Tests that a {@code Student}'s {@code grade} matches the given grade.
 */
public class IsSpecifiedGrade implements Predicate<Student> {
    private final Grade grade;

    public IsSpecifiedGrade(Grade grade) {
        this.grade = grade;
    }

    @Override
    public boolean test(Student student) {
        return student.getGrade().equals(grade);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IsSpecifiedGrade // instanceof handles nulls
                && grade.equals(((IsSpecifiedGrade) other).grade)); // state check
    }
}
