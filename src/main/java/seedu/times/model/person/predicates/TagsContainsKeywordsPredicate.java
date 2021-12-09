package seedu.times.model.person.predicates;

import java.util.List;
import java.util.function.Predicate;

import seedu.times.model.person.Student;

/**
 * Tests if a {@code Person}'s list of {@code Tag} matches any of the keywords given.
 */
public class TagsContainsKeywordsPredicate implements Predicate<Student> {
    private final List<String> keywords;

    public TagsContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Student student) {
        return keywords.stream()
                .anyMatch(keyword -> student.isAnyTagsMatching(keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof TagsContainsKeywordsPredicate
                && keywords.equals(((TagsContainsKeywordsPredicate) other).keywords));
    }
}
