package seedu.track2gather.model.person.predicates;

import java.util.List;
import java.util.function.Predicate;

import seedu.track2gather.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code ShnPeriod} end date equals any of the keywords given.
 */
public class ShnPeriodEndEqualsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public ShnPeriodEndEqualsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> person.getShnPeriod()
                        .value
                        .map(sh -> sh.getEndDate().toString().equals(keyword))
                        .orElse(false));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ShnPeriodEndEqualsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ShnPeriodEndEqualsKeywordsPredicate) other).keywords)); // state check
    }
}
