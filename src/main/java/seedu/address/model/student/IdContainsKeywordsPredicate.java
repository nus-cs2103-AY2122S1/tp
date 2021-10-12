package seedu.address.model.student;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Student}'s {@code ID} matches any of the keywords given.
 * Supports partial searching of IDs.
 */
public class IdContainsKeywordsPredicate implements Predicate<Student> {
    private final List<String> keywords;

    public IdContainsKeywordsPredicate(List<String> keyword) {
        this.keywords = keyword;
    }

    @Override
    public boolean test(Student student) {
        return keywords.stream()
                .anyMatch(keyword -> student.getId().value.toLowerCase().contains(keyword.toLowerCase()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IdContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((IdContainsKeywordsPredicate) other).keywords)); // state check
    }

}
