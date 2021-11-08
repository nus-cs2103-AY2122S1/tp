package seedu.address.model.person.predicates;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Phone} matches the keyword given.
 */
public class PhoneContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public PhoneContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> (person.getPhone().value.contains(keyword)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PhoneContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PhoneContainsKeywordsPredicate) other).keywords)); // state check
    }

}
