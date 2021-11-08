package seedu.track2gather.model.person.predicates;

import java.util.List;
import java.util.function.Predicate;

import seedu.track2gather.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code ShnPeriod} start date equals any of the keywords given.
 */
public class ShnPeriodStartEqualsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public ShnPeriodStartEqualsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> person.getShnPeriod()
                        .value
                        .map(sh -> sh.getStartDate().toString().equals(keyword))
                        .orElse(false));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ShnPeriodStartEqualsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ShnPeriodStartEqualsKeywordsPredicate) other).keywords)); // state check
    }
}
