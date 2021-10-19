package seedu.address.model.module.student;

import java.util.function.Predicate;

/**
 * Tests that a {@code Student}'s {@code StudentId} matches any of the keywords given.
 */
public class StudentIdContainsKeywordsPredicate implements Predicate<Student> {
    private final String keyword;

    public StudentIdContainsKeywordsPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Student student) {
        return keyword.equals(student.getStudentId().value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentIdContainsKeywordsPredicate // instanceof handles nulls
                && keyword.equals(((StudentIdContainsKeywordsPredicate) other).keyword)); // state check
    }

}
