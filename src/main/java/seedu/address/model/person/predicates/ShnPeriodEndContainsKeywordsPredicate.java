package seedu.address.model.person.predicates;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.person.Person;

public class ShnPeriodEndContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public ShnPeriodEndContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> person.getShnPeriod()
                .map(sh -> sh.endDate.toString().contains(keyword)).orElse(false));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ShnPeriodEndContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ShnPeriodEndContainsKeywordsPredicate) other).keywords)); // state check
    }
}
