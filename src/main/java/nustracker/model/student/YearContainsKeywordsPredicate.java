package nustracker.model.student;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Student}'s {@code Year} matches any of the keywords given.
 */
public class YearContainsKeywordsPredicate implements Predicate<Student> {
    private final List<Year> keywords;

    /**
     * Constructs an {@code YearContainsKeywordsPredicate}.
     *
     * @param keywords A list of years as keywords.
     */
    public YearContainsKeywordsPredicate(List<Year> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Student student) {
        return keywords.stream()
                .anyMatch(keyword -> student.getYear().equals(keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof YearContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((YearContainsKeywordsPredicate) other).keywords)); // state check
    }

}
