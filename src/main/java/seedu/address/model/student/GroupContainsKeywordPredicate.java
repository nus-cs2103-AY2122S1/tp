package seedu.address.model.student;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Student}'s {@code Group} matches any of the keywords given.
 * Supports partial searching of group names.
 */
public class GroupContainsKeywordPredicate implements Predicate<Student> {
    private final List<String> keywords;

    public GroupContainsKeywordPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Student student) {
        return student.getGroups().stream()
                .anyMatch(group -> keywords.stream()
                        .anyMatch(keyword -> group.name.toLowerCase().contains(keyword.toLowerCase())));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GroupContainsKeywordPredicate // instanceof handles nulls
                && keywords.equals(((GroupContainsKeywordPredicate) other).keywords)); // state check
    }

}
