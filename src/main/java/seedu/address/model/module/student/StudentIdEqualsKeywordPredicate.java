package seedu.address.model.module.student;

import java.util.function.Predicate;

/**
 * Tests that a {@code Student}'s {@code StudentId} matches the keyword given.
 */
public class StudentIdEqualsKeywordPredicate implements Predicate<Student> {
    private final String keyword;

    public StudentIdEqualsKeywordPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Student student) {
        return keyword.equals(student.getStudentId().value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentIdEqualsKeywordPredicate // instanceof handles nulls
                && keyword.equals(((StudentIdEqualsKeywordPredicate) other).keyword)); // state check
    }

}
