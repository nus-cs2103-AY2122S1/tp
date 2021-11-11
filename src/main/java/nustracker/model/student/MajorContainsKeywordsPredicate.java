package nustracker.model.student;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Student}'s {@code Major} matches any of the keywords given.
 */
public class MajorContainsKeywordsPredicate implements Predicate<Student> {
    private final List<Major> keywords;

    /**
     * Constructs an {@code MajorContainsKeywordsPredicate}.
     *
     * @param keywords A list of Majors as keywords.
     */
    public MajorContainsKeywordsPredicate(List<Major> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Student student) {
        return keywords.stream()
                .anyMatch(keyword -> student.getMajor().equals(keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MajorContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((MajorContainsKeywordsPredicate) other).keywords)); // state check
    }

}
