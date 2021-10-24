package seedu.address.model.person.predicates;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.person.Person;

public class ShnPeriodStartContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public ShnPeriodStartContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> person.getShnPeriod()
                .map(sh -> sh.startDate.toString().contains(keyword)).orElse(false));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ShnPeriodStartContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ShnPeriodStartContainsKeywordsPredicate) other).keywords)); // state check
    }
}
