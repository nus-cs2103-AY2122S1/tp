package nustracker.model.student;

import java.util.List;
import java.util.function.Predicate;


/**
 * Tests that a {@code Student}'s {@code StudentId} matches any of the keywords given.
 */
public class StudentIdContainsKeywordsPredicate implements Predicate<Student> {
    private final List<StudentId> keywords;

    /**
     * Constructs an {@code StudentIdContainsKeywordsPredicate}.
     *
     * @param keywords A list of StudentIds as keywords.
     */
    public StudentIdContainsKeywordsPredicate(List<StudentId> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Student student) {
        return keywords.stream()
                .anyMatch(keyword -> student.getStudentId().equals(keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentIdContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((StudentIdContainsKeywordsPredicate) other).keywords)); // state check
    }

}
