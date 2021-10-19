package seedu.address.model.module.student;

import java.util.function.Predicate;

/**
 * Tests that a {@code Student}'s {@code StudentId} matches any of the keywords given.
 */
public class StudentIdEqualsKeywordsPredicate implements Predicate<Student> {
    private final String keyword;

    public StudentIdEqualsKeywordsPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Student student) {
        return keyword.equals(student.getStudentId().value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentIdEqualsKeywordsPredicate // instanceof handles nulls
                && keyword.equals(((StudentIdEqualsKeywordsPredicate) other).keyword)); // state check
    }

}
