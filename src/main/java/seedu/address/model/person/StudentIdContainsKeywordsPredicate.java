package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code StudentId} matches any of the keywords given.
 */
public class StudentIdContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public StudentIdContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getStudentId().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentIdContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((StudentIdContainsKeywordsPredicate) other).keywords)); // state check
    }

}
