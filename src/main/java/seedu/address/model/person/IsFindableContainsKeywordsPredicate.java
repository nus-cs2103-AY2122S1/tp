package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class IsFindableContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public IsFindableContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsAndIgnoreCase(person.getName().fullName, keyword)
                        || StringUtil.containsAndIgnoreCase(person.getAddress().value, keyword)
                        || StringUtil.containsAndIgnoreCase(person.getEmail().value, keyword)
                        || StringUtil.containsAndIgnoreCase(person.getPhone().value, keyword)
                        || StringUtil.containsAndIgnoreCase(person.getReview().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IsFindableContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((IsFindableContainsKeywordsPredicate) other).keywords)); // state check
    }

}
