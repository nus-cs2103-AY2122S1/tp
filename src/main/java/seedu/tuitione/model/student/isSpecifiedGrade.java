package seedu.tuitione.model.student;

import java.util.function.Predicate;

/**
 * Tests that a {@code Student}'s {@code grade} matches the given grade.
 */
public class isSpecifiedGrade implements Predicate<Student> {
    private final Grade grade;

    public isSpecifiedGrade(Grade grade) {
        this.grade = grade;
    }

    @Override
    public boolean test(Student student) {
        return student.getGrade().equals(grade);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof isSpecifiedGrade // instanceof handles nulls
                && grade.equals(((isSpecifiedGrade) other).grade)); // state check
    }
}
