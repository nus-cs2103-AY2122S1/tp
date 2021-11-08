package seedu.track2gather.model.person.predicates;

import java.util.List;
import java.util.function.Predicate;

import seedu.track2gather.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Phone} starts with any of the keywords given.
 */
public class PhoneStartsWithKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public PhoneStartsWithKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(key -> person.getPhone().value.startsWith(key));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PhoneStartsWithKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PhoneStartsWithKeywordsPredicate) other).keywords)); // state check
    }
}
